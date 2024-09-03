package consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import pack.Employee;

@Service
public class ConsumerClass {

    @Value("${topic.name}")
    private String name;

    private static  final Logger LOGGER = LoggerFactory.getLogger(ConsumerClass.class);

    @KafkaListener(topics ="${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consumeEmployee(Employee employee)
    {

        LOGGER.info("Message received-->" + employee.toString());
    }
}
