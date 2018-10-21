package mum.edu.cs523;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Sender {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.topic.greetingtopic}")
    private String topic;

    public void send(String message){
    	System.out.println("sending message="+message+" to topic="+ topic);
        kafkaTemplate.send(topic, message);
    }
}
