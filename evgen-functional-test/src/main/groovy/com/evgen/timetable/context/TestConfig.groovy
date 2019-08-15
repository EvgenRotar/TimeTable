package com.evgen.timetable.context

import org.springframework.beans.factory.annotation.Value
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.core.io.ClassPathResource
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

import com.evgen.timetable.clients.HttpClient
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.PropertyAccessor
import com.fasterxml.jackson.databind.ObjectMapper

@ComponentScan("com.evgen.timetable")
class TestConfig {

  @Bean
  PropertyPlaceholderConfigurer propertyPlaceholderConfigurer() {
    def propertyPlaceholderConfigurer = new PropertyPlaceholderConfigurer()
    def resources = [
        new ClassPathResource("test.properties"),
        new ClassPathResource("ports.properties")
    ]
    propertyPlaceholderConfigurer.locations = resources
    propertyPlaceholderConfigurer.ignoreResourceNotFound = true
    propertyPlaceholderConfigurer.systemPropertiesModeName = "SYSTEM_PROPERTIES_MODE_OVERRIDE"
    propertyPlaceholderConfigurer
  }

  @Bean
  RestTemplate restTemplate() {
    def restTemplate = new RestTemplate()
    restTemplate.setRequestFactory(new HttpComponentsClientHttpRequestFactory())
    restTemplate
  }

  @Bean
  @Value('${app.port}')
  HttpClient baseUrlHttpClient(String port) {
    new HttpClient("http://localhost:" + port + "/api")
  }

  @Bean
  ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper()
    objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.NONE);
    objectMapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
  }

}
