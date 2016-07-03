package com.screwfix.claim.statistics.core;

import com.google.common.collect.ImmutableMap.Builder;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.screwfix.claim.statistics.StatisticsConfiguration;
import com.screwfix.claim.statistics.core.service.GuiceJobFactory;
import com.screwfix.claim.statistics.services.DataTransformer;
import com.screwfix.claim.statistics.services.dao.ClaimDao;
import com.screwfix.claim.statistics.services.dao.ClaimDaoImpl;
import com.screwfix.claim.statistics.services.health.QuartzHealthCheck;
import com.screwfix.claim.statistics.resources.ClaimsResource;
import com.screwfix.claim.statistics.resources.HomeResource;
import com.screwfix.claim.statistics.services.JobConfigurator;
import com.screwfix.claim.statistics.services.QuartzManager;
import com.screwfix.claim.statistics.services.XmlLoader;
import io.dropwizard.db.DataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.log4j.Logger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.Properties;

import static org.apache.ibatis.io.Resources.getResourceAsStream;

public class CoreModule extends AbstractModule {
    private static final Logger LOGGER = Logger.getLogger(CoreModule.class);
    private StatisticsConfiguration configuration;

    public CoreModule(StatisticsConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    protected void configure() {
        bind(XmlLoader.class).in(Singleton.class);
        bind(DataTransformer.class).in(Singleton.class);
        bind(QuartzManager.class).in(Singleton.class);
        bind(GuiceJobFactory.class).in(Singleton.class);
        bind(QuartzHealthCheck.class).in(Singleton.class);
        bind(JobConfigurator.class).in(Singleton.class);
        bind(ClaimsResource.class).in(Singleton.class);
        bind(HomeResource.class).in(Singleton.class);  // TODO: remove
        bind(ClaimDao.class).to(ClaimDaoImpl.class);
    }

    @Provides @Singleton
    public Scheduler provideScheduler(Provider<GuiceJobFactory> provider) {
        GuiceJobFactory guiceJobFactory = provider.get();
        try {
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.setJobFactory(guiceJobFactory);
            return scheduler;
        } catch (SchedulerException e) {
            LOGGER.error("Error occurred during guice DI initialization");
            throw new RuntimeException("Error occurred during guice DI initialization", e);
        }
    }

    @Provides @Singleton
    public SqlSessionFactory provideSqlSessionFactory() throws URISyntaxException, IOException {
        DataSourceFactory ds = configuration.getDataSourceFactory();
        Properties prop = new Properties();
        Map<String, String> properties = new Builder<String, String>()
                .put("user", ds.getUser()).put("password", ds.getPassword())
                .put("url", ds.getUrl()).put("driverClass", ds.getDriverClass()).build();
        prop.putAll(properties);
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        return sqlSessionFactoryBuilder.build(getResourceAsStream("mybatis-config.xml"), prop);
    }

    @Provides @Singleton
    public StatisticsConfiguration provideConfiguration() {
        return configuration;
    }
}
