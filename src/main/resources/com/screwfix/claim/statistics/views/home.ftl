<!DOCTYPE html>
<html>
<head>
    <script src="/js/jquery-1.11.3.js"></script>
    <script src="/js/home.js"></script>
    <link rel="stylesheet" href="/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/bootstrap-theme.min.css" >
    <link rel="stylesheet" href="/css/custom.css" >
    <script src="/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="/css/font-awesome.min.css">
    <link rel="stylesheet" href="/css/jquery-ui.min.css">
    <script src="/js/jquery-ui.min.js"></script>
</head>
<body>
<div class="row">
    <div class="headline"><img id="screwFIX-logo" class="custom-img" src="/images/screwfix.jpg"/>Jenkins Claim Statistics</div>
</div>
<br/>
<div id="error_placement" class="alert alert-danger" hidden></div>
<ul class="nav nav-pills">
    <li><a href="#" id="home_tab" class="tab-fonts">Login</a></li>
    <li><a href="#" id="filter_options_tab" class="tab-fonts">Filter</a></li>
</ul>
<form class="form-horizontal" role="form" id="filter_form" hidden>
    <div class="form-group">
        <label class="control-label col-sm-2" >User Name:</label>
        <div class="col-sm-2">
            <input class="form-control" type="text" name="user" placeholder="claimed by">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" >Job Name:</label>
        <div class="col-sm-2">
            <input class="form-control" type="text" name="jobName" placeholder="job name">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" >Claim Reason:</label>
        <div class="col-sm-2">
            <input class="form-control" type="text" name="reason" placeholder="reason">
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" for="claim-type-id">Is Active Claim:</label>
        <div class="col-sm-2" id="claim-type-id">
            <select id="claimOption" class="form-control" name="isActiveClaim">
                <option value="any">ANY</option>
                <option value="active">Active</option>
                <option value="not_active">Not active</option>
            </select>
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" >At Least Days:</label>
        <div class="col-sm-2">
            <input class="form-control" type="number" name="duration" placeholder="1">
        </div>
    </div>
    <div id="dateFrom" class="form-group">
        <label class="control-label col-sm-2" >Search From:</label>
        <div class="col-sm-2">
            <input id="dateFromInput" class="form-control" type="text" name="dateFrom" placeholder="mm/dd/yyyy">
        </div>
        <div id="dateFromMessage" class="col-sm-3 alert alert-danger" hidden>
            Please fill the filed
        </div>
    </div>
    <div class="form-group">
        <label class="control-label col-sm-2" >Search To:</label>
        <div class="col-sm-2">
            <input id="dateToInput" class="form-control" type="text" name="dateTo" placeholder="mm/dd/yyyy">
        </div>
        <div id="dateToMessage" class="col-sm-3 alert alert-info" hidden>
            Available only for claim <strong>Not Active</strong> or <strong>ANY</strong>
        </div>
    </div>
    <div class="form-group">
        <div class="col-sm-offset-2 col-sm-10">
            <input type="submit" value="Apply Filter" class="btn btn-default" id="apply_filter">
            <input type="button" value="Reset" class="btn btn-default" id="reset_button">
        </div>
    </div>
</form>
<br/>
<table id="entities_table" class="table custom-table table-bordered table-hover">
    <thead id="claim_table_header">
    <tr class="info" >
        <th id="num" class="width-5per">#</th>
        <th id="job_name" class="sorting width-15per">Job Name</th>
        <th id="claimed_by" class="sorting width-10per">User Name</th>
        <th id="reason" >Reason</th>
        <th id="start_date_claim" class="sorting width-10per">Start Date Claim</th>
        <th id="end_date_claim" class="sorting width-10per">End Date Claim</th>
    </tr>
    </thead>
</table>
<ul class="pager">
    <li class="previous" id="button_previous"><a id="previous-message" href="#entities_table"></a></li>
    <li class="next" id="button_next"><a id="next-message" href="#entities_table"></a></li>
    <div id="items_" class="col-sm-2 col-md-offset-5">
        <select class="form-control" id="items_per_page">
            <option value="10">10 per page</option>
            <option value="20">20 per page</option>
            <option value="30">30 per page</option>
        </select>
    </div>
</ul>
</body>
</html>