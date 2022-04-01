package com.bo.springbootkafkaconsumer.config;

import com.bo.springbootkafkaconsumer.models.MyModel;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value(value = "${spring.kafka.properties.security_protocol}")
    private String protocol;

    @Value(value = "${spring.kafka.properties.password_generic}")
    private String password;

    @Value(value = "${spring.kafka.properties.truststore_location}")
    private String jksPath;

    @Value(value = "${spring.kafka.properties.keystore_location}")
    private String keystorePath;

    @Bean
    public ProducerFactory<String, MyModel> myProducerFactory() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());


        configProps.put("security.protocol", "SSL");
        configProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, jksPath);
        configProps.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, "PKCS12");
        configProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, password);
        configProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keystorePath);
        configProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, password);

        configProps.put(SslConfigs.SSL_ENABLED_PROTOCOLS_CONFIG, SslConfigs.DEFAULT_SSL_ENABLED_PROTOCOLS);


//        if (protocol.equalsIgnoreCase("SSL")) {
//            configProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, jksPath);
////            configProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, password);
//            configProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, jksPath);
////            configProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, password);
////            configProps.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, password);
//            configProps.put(SslConfigs.SSL_ENABLED_PROTOCOLS_CONFIG, SslConfigs.DEFAULT_SSL_ENABLED_PROTOCOLS);
//        }

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, MyModel> userKafkaTemplate() {
        return new KafkaTemplate<>(myProducerFactory());
    }
}
