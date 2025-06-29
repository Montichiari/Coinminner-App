package com.coinminner.myapp;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import jakarta.servlet.DispatcherType;
import jakarta.servlet.FilterRegistration;

import java.util.EnumSet;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.coinminner.myapp.resources.CoinResource;

public class coinminnerApplication extends Application<coinminnerConfiguration> {

    public static void main(final String[] args) throws Exception {
        new coinminnerApplication().run(args);
    }

    @Override
    public String getName() {
        return "coinminner";
    }

    @Override
    public void initialize(final Bootstrap<coinminnerConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final coinminnerConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*"); // Or set specific origin
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "OPTIONS,GET,PUT,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    	
    	environment.jersey().register(new CoinResource());
    }

}
