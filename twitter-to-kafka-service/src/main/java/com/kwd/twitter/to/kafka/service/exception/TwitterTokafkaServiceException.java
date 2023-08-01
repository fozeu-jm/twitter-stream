package com.kwd.twitter.to.kafka.service.exception;

public class TwitterTokafkaServiceException extends RuntimeException{

    public TwitterTokafkaServiceException() {
        super();
    }

    public TwitterTokafkaServiceException(String message) {
        super(message);
    }

    public TwitterTokafkaServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
