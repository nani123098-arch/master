package com.wikimediadata.consumer;

import com.wikimediadata.entity.Wikimedia;
import com.wikimediadata.repository.WikimediaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class WikimediaConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaConsumer.class);

    private WikimediaRepository wikimediaRepository;

    public WikimediaConsumer(WikimediaRepository wikimediaRepository) {
        this.wikimediaRepository = wikimediaRepository;
    }

    @KafkaListener(topics = "wikimedia", groupId = "myGroup")
    public void wikimediaReceive(String eventMessage)
    {
        LOGGER.info(String.format("Message Received Successfully -> %s ", eventMessage ));

        Wikimedia wikimedia = new Wikimedia();
        wikimedia.setData(eventMessage);
        wikimediaRepository.save(wikimedia);
    }

}
