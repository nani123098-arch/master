package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import pack.Employee;

import java.util.concurrent.CompletableFuture;

@Service
public class ProducerClass {
    
    @Autowired
    private KafkaTemplate<String, Employee> kafkaTemplate;
    
    @Value("${topic.name}")
    private String topicName;
    
    public void sendEvent(Employee employee)
    {
        CompletableFuture<SendResult<String, Employee>> send = kafkaTemplate.send(topicName, employee);

        send.whenComplete((r,e)->
        {
            if(e==null)
            {
                System.out.println("Message sent "+ r.getProducerRecord()+"meta data"+r.getRecordMetadata().offset());
            }
            else {
                System.out.println("Unable to send an message");
            }
        });


    }
}
