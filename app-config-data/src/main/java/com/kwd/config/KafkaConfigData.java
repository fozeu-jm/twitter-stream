package com.kwd.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "kafka-config")
public class KafkaConfigData {
    private String bootstrapServers;
    private String schemaRegistryKey;
    private String topicName;
    private List<String> topicNamesToCreate;
    private Integer numOfPartitions;
    private Short replicationFactor;

    public String getBootstrapServers() {
        return bootstrapServers;
    }

    public void setBootstrapServers(String bootstrapServers) {
        this.bootstrapServers = bootstrapServers;
    }

    public String getSchemaRegistryKey() {
        return schemaRegistryKey;
    }

    public void setSchemaRegistryKey(String schemaRegistryKey) {
        this.schemaRegistryKey = schemaRegistryKey;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public List<String> getTopicNamesToCreate() {
        return topicNamesToCreate;
    }

    public void setTopicNamesToCreate(List<String> topicNamesToCreate) {
        this.topicNamesToCreate = topicNamesToCreate;
    }

    public Integer getNumOfPartitions() {
        return numOfPartitions;
    }

    public void setNumOfPartitions(Integer numOfPartitions) {
        this.numOfPartitions = numOfPartitions;
    }

    public Short getReplicationFactor() {
        return replicationFactor;
    }

    public void setReplicationFactor(Short replicationFactor) {
        this.replicationFactor = replicationFactor;
    }
}
