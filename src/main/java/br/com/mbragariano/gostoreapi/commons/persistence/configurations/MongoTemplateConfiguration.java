package br.com.mbragariano.gostoreapi.commons.persistence.configurations;

import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@RequiredArgsConstructor
public class MongoTemplateConfiguration {

  private final MongoProperties mongoProperties;

  @Bean
  public MongoClient mongoClient() {
    final var username = this.mongoProperties.getUsername();
    final var database = this.mongoProperties.getDatabase();
    final var password = this.mongoProperties.getPassword();

    final var credential = MongoCredential.createScramSha1Credential(username, database, password);
    final var mongoClientSettings = MongoClientSettings.builder().credential(credential).build();

    return MongoClients.create(mongoClientSettings);
  }

  @Bean
  public MongoTemplate mongoTemplate() {
    return new MongoTemplate(this.mongoClient(), this.mongoProperties.getDatabase());
  }

}
