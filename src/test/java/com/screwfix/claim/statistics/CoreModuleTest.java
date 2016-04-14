package com.screwfix.claim.statistics;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.screwfix.claim.statistics.dao.ClaimDAO;
import com.screwfix.claim.statistics.health.QuartzHealthCheck;
import com.screwfix.claim.statistics.resources.ClaimsResource;
import com.screwfix.claim.statistics.core.service.GuiceJobFactory;
import com.screwfix.claim.statistics.services.JobConfigurator;
import com.screwfix.claim.statistics.services.QuartzManager;
import com.screwfix.claim.statistics.services.XmlLoader;
import io.dropwizard.db.DataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.quartz.Scheduler;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

/**
 * Integration Test TODO:
 */
public class CoreModuleTest {

    StatisticsConfiguration conf;

    @BeforeMethod
    public void setUp() throws Exception {
        conf = new StatisticsConfiguration();
        DataSourceFactory ds = new DataSourceFactory();
        ds.setUser("user");
        ds.setPassword("pwd");
        ds.setUrl("url");
        ds.setDriverClass("driver");
        conf.setDataSourceFactory(ds);
    }

    @Test
    public void testCoreModuleContext() {
        Injector injector = Guice.createInjector(new CoreModule(conf));
        assertNotNull(injector.getInstance(QuartzManager.class));
        assertNotNull(injector.getInstance(XmlLoader.class));
        assertNotNull(injector.getInstance(GuiceJobFactory.class));
        assertNotNull(injector.getInstance(QuartzHealthCheck.class));
        assertNotNull(injector.getInstance(JobConfigurator.class));
        assertNotNull(injector.getInstance(ClaimsResource.class));
        assertNotNull(injector.getInstance(ClaimDAO.class));
        assertNotNull(injector.getInstance(Scheduler.class));
        assertNotNull(injector.getInstance(SqlSessionFactory.class));
        assertNotNull(injector.getInstance(StatisticsConfiguration.class));
    }
}