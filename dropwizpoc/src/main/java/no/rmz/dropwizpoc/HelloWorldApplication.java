package no.rmz.dropwizpoc;

import static com.google.common.base.Preconditions.checkNotNull;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import no.rmz.websockets.*;

public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(final String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(final  Bootstrap<HelloWorldConfiguration> bootstrap) {
        checkNotNull(bootstrap);
        // nothing to do yet
    }

    @Override
    public void run(
            final HelloWorldConfiguration configuration,
            final Environment environment) {
        checkNotNull(configuration);
        checkNotNull(environment);

        final HelloWorldResource resource = new HelloWorldResource(
                configuration.getTemplate(),
                configuration.getDefaultName()
        );

        final EchoServer echoServer = new EchoServer();

        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck(configuration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
        // environment.jersey().register(echoServer);
        environment.jersey().register(resource);
    }
}
