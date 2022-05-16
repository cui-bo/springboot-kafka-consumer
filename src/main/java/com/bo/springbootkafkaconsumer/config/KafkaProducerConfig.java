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

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProducerConfig {

    @Value("${spring.kafka.producer.bootstrap-servers}")
    private String bootstrapServers;

    @Value(value = "${spring.kafka.producer.properties.security.protocol}")
    private String protocol;

    @Value(value = "${spring.kafka.producer.properties.ssl.truststore.location}")
    private String truststoreLocation;

    @Value(value = "${spring.kafka.producer.properties.ssl.truststore.password}")
    private String truststorePassword;

    @Value(value = "${spring.kafka.producer.properties.ssl.truststore.type}")
    private String truststoreType;

    @Value(value = "${spring.kafka.producer.properties.ssl.keystore.location}")
    private String keystoreLocation;

    @Value(value = "${spring.kafka.producer.properties.ssl.keystore.password}")
    private String keystorePassword;

    @Value(value = "${spring.kafka.producer.properties.ssl.keystore.type}")
    private String keystoreType;

    @Bean
    public ProducerFactory<String, MyModel> myProducerFactory() throws IOException {

        Map<String, Object> configProps = new HashMap<>();
        configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class.getName());

        configProps.put("security.protocol", protocol);
        configProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, truststoreLocation);
        configProps.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, truststoreType);
        configProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, truststorePassword);
        configProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keystoreLocation);
        configProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, keystorePassword);
        configProps.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, keystoreType);
        configProps.put(SslConfigs.SSL_ENABLED_PROTOCOLS_CONFIG, SslConfigs.DEFAULT_SSL_ENABLED_PROTOCOLS);

        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, MyModel> userKafkaTemplate() throws IOException {
        return new KafkaTemplate<>(myProducerFactory());
    }
}
