package ma.tnbmaroc.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopic {

    @Bean
    public NewTopic topic(){
        return TopicBuilder.name("authentication-service").build();
    }
/*
    @Bean
    public NewTopic logintopic(){
        return TopicBuilder.name("login-service").build();
    }

    @Bean
    public NewTopic loginResponsetopic(){
        return TopicBuilder.name("tnb-authentication-result").build();
    }

 */
}