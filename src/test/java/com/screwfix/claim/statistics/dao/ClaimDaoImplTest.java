package com.screwfix.claim.statistics.dao;

import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Date;
import java.util.List;
import java.util.Properties;

import static java.util.Collections.singletonList;
import static org.apache.ibatis.io.Resources.getResourceAsStream;

/**
 * Integration Test TODO:
 */

public class ClaimDaoImplTest {
    private static final String MAPPING_CONFIG_XML = "mybatis-config.xml";
    private static final String TEST_DB = "config/test-db.properties";
    private ClaimDaoImpl claimDao;

    @BeforeClass
    public void setUp() throws Exception {
        Properties prop = new Properties();
        prop.load(getResourceAsStream(TEST_DB));
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sessionFactory = sqlSessionFactoryBuilder.build(getResourceAsStream(MAPPING_CONFIG_XML), prop);
        claimDao = new ClaimDaoImpl();
        claimDao.setSessionFactory(sessionFactory);
    }

    @DataProvider(name = "data")
    public Object[][] data() {
        return new Object[][]{
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
        System.out.println("done");
    }
}
