package com.screwfix.claim.statistics;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.screwfix.claim.statistics.health.QuartzHealthCheck;
import com.screwfix.claim.statistics.resources.ClaimsResource;
import com.screwfix.claim.statistics.resources.HomeResource;
import com.screwfix.claim.statistics.services.QuartzManager;
import com.screwfix.claim.statistics.tasks.TestTask;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;


public class StatisticsApplication extends Application<StatisticsConfiguration> {
    public static void main(String[] args) throws Exception {
        new StatisticsApplication().run(args);
    }

    @Override
    public String getName() {
        return "jenkins-claim-statistics";
    }

    @Override
    public void initialize(Bootstrap<StatisticsConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<>());
        bootstrap.addBundle(new AssetsBundle("/assets/css", "/css", null, "css"));
        bootstrap.addBundle(new AssetsBundle("/assets/js", "/js", null, "js"));
        bootstrap.addBundle(new AssetsBundle("/assets/images", "/images", null, "images"));
    }

    @Override
    public void run(StatisticsConfiguration configuration, Environment environment) {
        Injector injector = Guice.createInjector(new CoreModule(configuration, environment));
        QuartzManager quartz = injector.getInstance(QuartzManager.class);
        QuartzHealthCheck quartzHealthCheck = injector.getInstance(QuartzHealthCheck.class);
        ClaimsResource claimsResource = injector.getInstance(ClaimsResource.class);
        HomeResource homeResource = injector.getInstance(HomeResource.class);

        environment.healthChecks().register("quartz", quartzHealthCheck);
        environment.jersey().register(claimsResource);
        environment.jersey().register(homeResource);
        environment.admin().addTask(new TestTask()); // TODO: test
        environment.lifecycle().manage(quartz);
    }
}