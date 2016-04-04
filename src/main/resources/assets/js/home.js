var claimState = {
    page: 1,
    list_entities: 0,
    maxRow: 10,
    sortFiled: '',
    sortType: ''
};

$(window).load(function () {
    init();
});

var init = function () {

    var filter_form = $('#filter_form');
    $('#button_next').on('click', function () {
        claimState.page += 1;
        $("#filter_form").submit();
    });

    $('#button_previous').on('click', function () {
        claimState.page -= 1;
        $("#filter_form").submit();
    });

    $('#apply_filter').on('click', function (e) {
        e.preventDefault();
        claimState.page = 1;
        $("#filter_form").submit();
    });

    bindFormFilter();

    $("#claimOption").change(function () {
        if (this.value != 'active') {
            $('#dateToInput').prop('disabled', false);
            $('#dateToMessage').hide();
        }
        if (this.value == 'active') {
            $('#dateToInput').prop('disabled', true);
            $('#dateToMessage').show();
        }
    });

    $('#reset_button').click(function () {
        resetForm();
        hideAlerts();
        filter_form.submit();
    });

    $('#filter_options_tab').click(function () {
        filter_form.slideToggle();
    });

    $('#update_options_tab').click(function () {
        $.post("/ajax/execute", function () {
            $("#filter_form").submit();
            console.log('done');
        });
    });

    $('#screwFIX-logo').click(function () {
        filter_form.slideUp();
        resetForm();
        filter_form.submit();
    });

    $('#items_per_page').on('change', function () {
        claimState.maxRow = parseInt(this.value);
        filter_form.submit();
    });

    $('#dateFromMessage').change(function () {
        $('#dateFromMessage').hide();
    });

    $(function () {
        $("#dateFromInput").datepicker();
    });

    $(function () {
        $("#dateToInput").datepicker();
    });
    filter_form.submit();
};

var refreshSort = function(headerTable, elementOuter) {
    headerTable.find('th').each(function (index, elementInner) {
        if ($(elementInner).attr('id') != $(elementOuter).attr('id')) {
            if ($(elementInner).hasClass('sorting-asc')) {
                $(elementInner).removeClass('sorting-asc');
                $(elementInner).addClass('sorting');
            } else if ($(elementInner).hasClass('sorting-desc')) {
                $(elementInner).removeClass('sorting-desc');
                $(elementInner).addClass('sorting');
            }
        }
    });
};

var callbackInit = function () {
    var headerTable = $('#claim_table_header');
    var filterForm = $("#filter_form");
    headerTable.find('th').each(function (index, elementOuter) {
        $(elementOuter).on('click', function () {
            if ($(elementOuter).hasClass('sorting')) {
                $(elementOuter).removeClass('sorting');
                $(elementOuter).addClass('sorting-asc');
                claimState.sortFiled = $(this).attr('id');
                claimState.sortType = 'ASC';
                refreshSort(headerTable, elementOuter);
                filterForm.submit();
            } else if ($(elementOuter).hasClass('sorting-asc')) {
                $(elementOuter).removeClass('sorting-asc');
                $(elementOuter).addClass('sorting-desc');
                claimState.sortFiled = $(this).attr('id');
                claimState.sortType = 'DESC';
                refreshSort(headerTable, elementOuter);
                filterForm.submit();
            } else if ($(elementOuter).hasClass('sorting-desc')) {
                $(elementOuter).removeClass('sorting-desc');
                $(elementOuter).addClass('sorting');
                claimState.sortFiled = '';
                claimState.sortType = '';
                refreshSort(headerTable, elementOuter);
                filterForm.submit();
            }
        })
    });
};

var hideAlerts = function () {
    $('#dateFromMessage').hide();
    $('#dateToMessage').hide();
    $('#dateFrom').removeClass("has-error");
};

var resetForm = function () {
    $('#filter_form')[0].reset();
    claimState.page = 1;
};

var bindFormFilter = function () {
    $("#filter_form").on('submit', submitForm);
};

var submitForm = function (e) {
    e.preventDefault();
    if ($('#dateToInput').val().length > 0 && $('#dateFromInput').val().length == 0) {
        $('#dateFrom').addClass("has-error");
        $('#dateFromMessage').show();
        return;
    }
    var postData = $(this).serializeArray();
    postData[postData.length] = {name: "page", value: claimState.page};
    postData[postData.length] = {name: "items", value: claimState.maxRow};
    postData[postData.length] = {name: "sortField", value: claimState.sortFiled};
    postData[postData.length] = {name: "sortType", value: claimState.sortType};
    var formURL = "/api/claims";
    $.ajax({
        url: formURL,
        type: "POST",
        data: postData,
        success: function (json) {
            hideAlerts();
            showButtons(json.length > claimState.maxRow);
            drawTable(json);
            callbackInit()
        },
        error: function () {
            console.log('error occurred')
        }
    });
};

var showButtons = function (nextExist) {
    if (nextExist) showElement('#button_next', '#next-message', 'Next ' + claimState.maxRow + ' items');
    else $('#button_next').hide();
    if (claimState.page > 1) showElement('#button_previous', '#previous-message', 'Previous ' + claimState.maxRow + ' items');
    else $('#button_previous').hide();
};

var showElement = function (elemId, elemMessId, message) {
    $(elemId).show();
    $(elemMessId).text(message);
};

var drawTable = function (data) {
    var tableHeader = $('#claim_table_header');
    var entitiesTable = $('#entities_table');
    entitiesTable.html(tableHeader);
    var limit = data.length > claimState.maxRow ? claimState.maxRow : data.length;
    var tbody = $('<tbody/>');
    entitiesTable.append(tbody);
    for (var i = 0; i < limit; i++) {
        drawRow(data[i], i, tbody);
    }
};

var drawRow = function (rowData, pos, tbody) {
    var ciTestEnv = 'http://vuxdev2081.ondc.screwfix.local:8190';  // TODO: redesign on server side eval from property
    var testUrl = ciTestEnv + '/job/' + rowData.jobName;

    if (typeof rowData.endClaim == 'undefined') rowData.endClaim = 'active';
    var row = $("<tr />");
    $(row).on('click', function () {
        window.open(testUrl, '_blank')
    });

    tbody.append(row);
    var itemPos = claimState.page > 1 ? ++pos + ((claimState.page - 1) * claimState.maxRow) : ++pos;
    row.append($("<td>" + itemPos + "</td>").addClass('width-5per'));
    row.append($("<td>" + rowData.jobName + "</td>").addClass('width-15per').attr('title', rowData.jobName));
    row.append($("<td>" + rowData.user + "</td>").addClass('width-10per').attr('title', rowData.user));
    row.append($("<td>" + rowData.reason + "</td>").attr('title', rowData.reason));
    row.append($("<td>" + rowData.startClaim + "</td>").addClass('width-10per').attr('title', rowData.startClaim));
    row.append($("<td>" + rowData.endClaim + "</td>").addClass('width-10per').attr('title', rowData.endClaim));
};

