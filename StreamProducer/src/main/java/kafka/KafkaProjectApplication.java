package kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
public class KafkaProjectApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(KafkaProjectApplication.class, args);
    }

    @Autowired
    private Sender sender;

    @Override
    public void run(String... strings) throws Exception {
    	/*
        sender.send("Spring Kafka: Producer1 (1)");
        sender.send("Spring Kafka: Producer1 (2)");
        sender.send("Spring Kafka: Producer1 (3)");
        sender.send("Spring Kafka: Producer1 (4)");
        */
    }
}