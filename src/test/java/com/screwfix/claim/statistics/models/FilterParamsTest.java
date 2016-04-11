package com.screwfix.claim.statistics.models;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FilterParamsTest {

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][] {
                { new FilterParams().setPage("1").setItems("10"), 11, 0},
                { new FilterParams().setPage("1").setItems("20"), 21, 0},
                { new FilterParams().setPage("2").setItems("20"), 21, 20}
        };
    }

    @Test(dataProvider = "data")
    public void test(FilterParams params, int expectedLimit, int expectedOffset) {
        assertEquals(params.getLimit(), expectedLimit);
        assertEquals(params.getOffset(), expectedOffset);
    }
}
