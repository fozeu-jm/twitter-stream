package com.kwd.kafka.producer.service.impl;

import com.kwd.kafka.avro.model.TwitterAvroModel;
import com.kwd.kafka.producer.service.KafkaProducer;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import javax.annotation.PreDestroy;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

@Service
public class TwitterKafkaProducer implements KafkaProducer<Long, TwitterAvroModel> {
    private static final Logger LOG = LoggerFactory.getLogger(TwitterKafkaProducer.class);

    private final KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate;

    public TwitterKafkaProducer(KafkaTemplate<Long, TwitterAvroModel> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @PreDestroy
    public void close() {
        if (Objects.nonNull(kafkaTemplate)) {
            LOG.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }

    @Override
    public void send(String topicName, Long key, TwitterAvroModel message) {
        CompletableFuture<SendResult<Long, TwitterAvroModel>> kafkaResultFuture = kafkaTemplate.send(topicName, key, message);
        kafkaResultFuture.whenComplete((sendResult, throwable) -> {
            if (Objects.nonNull(throwable)) {
                LOG.error("Error while sending message {} to topic {}", message.toString(), topicName, throwable);
            }
            RecordMetadata metadata = sendResult.getRecordMetadata();
            LOG.debug("Received new metadata. Topic: {}; Partition {}; Offset{}; Timestamp {}; at time {}",
                    metadata.topic(),
                    metadata.partition(),
                    metadata.offset(),
                    metadata.timestamp(),
                    System.nanoTime());
        });
    }
}
