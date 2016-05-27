package com.screwfix.claim.statistics.services.dao;

import com.screwfix.claim.statistics.models.Claim;
import com.screwfix.claim.statistics.models.FilterParams;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Properties;

import static com.screwfix.claim.statistics.data.provider.ClaimDaoProvider.provideData;
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
        return provideData();
    }

    @Test(dataProvider = "data")
    public void testClaimDao(FilterParams params, List<Claim> expected) throws Exception {
        List<Claim> actual = claimDao.findClaims(params);
        Assert.assertEquals(actual, expected);
    }
}
