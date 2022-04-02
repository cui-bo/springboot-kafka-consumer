package com.bo.springbootkafkaconsumer.config;

import com.bo.springbootkafkaconsumer.models.MyModel;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.config.SslConfigs;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

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

    @Value("${kafka.consumer.auto-offset-reset}")
    private String autoOffsetResetConfig;

    @Bean
    public ConsumerFactory<String, MyModel> consumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put("security.protocol", protocol);
        props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, truststoreLocation);
        props.put(SslConfigs.SSL_TRUSTSTORE_TYPE_CONFIG, truststoreType);
        props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, truststorePassword);
        props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keystoreLocation);
        props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, keystorePassword);
        props.put(SslConfigs.SSL_KEYSTORE_TYPE_CONFIG, keystoreType);
        props.put(SslConfigs.SSL_ENABLED_PROTOCOLS_CONFIG, SslConfigs.DEFAULT_SSL_ENABLED_PROTOCOLS);
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, "socle-group");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetResetConfig);
        return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), new JsonDeserializer<>(MyModel.class));
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MyModel> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, MyModel>
                factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }

}