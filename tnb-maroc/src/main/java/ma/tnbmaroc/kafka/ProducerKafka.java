package ma.tnbmaroc.kafka;
import ma.tnbmaroc.security.AuthenticationRequest;
import org.apache.kafka.clients.producer.ProducerConfig;
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
public class ProducerKafka {

    @Value("${spring.kafka.bootstrap-servers}")
    private String server;

    public Map<String, Object> producerConfig() {
        HashMap<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, Boolean.class); // Use JsonSerializer for value serialization
        return props;
    }

    @Bean
    public ProducerFactory<String, Boolean> authenticationRequestProducerFactory() {
        Map<String, Object> configProps = producerConfig();
        return new DefaultKafkaProducerFactory<>(configProps);
    }

    @Bean
    public KafkaTemplate<String, Boolean> authenticationRequestKafkaTemplate() {
        return new KafkaTemplate<>(authenticationRequestProducerFactory());
    }
}
