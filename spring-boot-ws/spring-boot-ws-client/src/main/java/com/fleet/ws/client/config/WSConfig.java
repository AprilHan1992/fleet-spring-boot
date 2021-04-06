package com.fleet.ws.client.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

/**
 * @author April Han
 */
@Configuration
public class WSConfig {

    @Bean("jaxb2Marshaller")
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller jaxb2Marshaller = new Jaxb2Marshaller();
        jaxb2Marshaller.setContextPath("com.fleet.ws.client.entity");
        return jaxb2Marshaller;
    }

    @Bean
    public WsClient wsClient(@Qualifier("jaxb2Marshaller") Jaxb2Marshaller jaxb2Marshaller) {
        WsClient wsClient = new WsClient();
        wsClient.setDefaultUri("http://127.0.0.1:8002/services/userService?wsdl");
        wsClient.setMarshaller(jaxb2Marshaller);
        wsClient.setUnmarshaller(jaxb2Marshaller);
        return wsClient;
    }
}
