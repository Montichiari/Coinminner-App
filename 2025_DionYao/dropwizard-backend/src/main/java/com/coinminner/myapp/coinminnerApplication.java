package com.coinminner.myapp;

import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import com.coinminner.myapp.api.*;

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
    	environment.jersey().register(new CoinResource());
    }

}
