package com.vgalloy.server;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.CollectionFactory;

/**
 * @author Vincent Galloy
 *         Created by Vincent Galloy on 09/12/15.
 */

@Configuration
@ComponentScan
@EnableAspectJAutoProxy(proxyTargetClass=true)
@EnableAutoConfiguration(exclude = {EmbeddedMongoAutoConfiguration.class, MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
public class StartServer {
    public static void main(String[] args) throws Exception {
        new SpringApplicationBuilder(StartServer.class)
                .properties("server.ssl.key-store:classpath:keystore-default.p12") // Ces propriétés peuvent être override par un application.yml
                .properties("server.ssl.key-store-password:password")
                .properties("server.ssl.keyStoreType:PKCS12")
                .properties("server.ssl.keyAlias:tomcat")
                .properties("server.port=8081") //Cette option n'est pas override par l'option -Dserver.port
                .run(args);
    }
}
