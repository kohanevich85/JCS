package com.screwfix.claim.statistics.dao;

import com.google.common.collect.ImmutableMap.Builder;
import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.*;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
import static org.apache.ibatis.io.Resources.getResourceAsStream;

/**
 * Integration Test TODO:
 */
public class ClaimDaoImplTest {

    SqlSessionFactory sessionFactory;
    ClaimDaoImpl claimDao;

    @BeforeMethod
    public void setUp() throws Exception {
        Properties prop = new Properties();
        Map<String, String> properties = new Builder<String, String>()
                .put("user", "user")
                .put("password", "pwd")
                .put("url", "jdbc:h2:file:d:/projects/db/h2_2/jenkins_claim_statistics")
                .put("driverClass", "org.h2.Driver")
                .build();
        prop.putAll(properties);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        sessionFactory = sqlSessionFactoryBuilder.build(getResourceAsStream("mybatis-config.xml"), prop);
        claimDao = new ClaimDaoImpl();
        claimDao.setSessionFactory(sessionFactory);
    }

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][] {
                {
                 new FilterParams().setJobName("JOB_1").setPage("1"),
                 singletonList(new Claim().setUser("USER_1").setJobName("JOB_1").setReason("REASON_1").setStartClaim(new Date()))
                },
        };
    }

    @Test(dataProvider = "data")
    public void testClaimDao(FilterParams params, List<Claim> expectedClaims) throws Exception {
        List<Claim> claims = claimDao.findClaims(params);
        for (Claim claim : claims) {
            System.out.println(claim);
        }
    }
}