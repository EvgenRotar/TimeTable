package com.evgen.timetable.builders

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

import com.evgen.timetable.clients.HttpClient

@Component
class EndPointRequestBuilder {

  @Autowired
  HttpClient baseUrlHttpClient

  HttpClient teacherHttpClient(String teacherId) {
    HttpClient client = baseUrlHttpClient.clone()
    client.hostUrl = "${baseUrlHttpClient.hostUrl}/teachers/${teacherId}"
    client
  }

  HttpClient loginHttpClient() {
    HttpClient client = baseUrlHttpClient.clone()
    client.hostUrl = "${baseUrlHttpClient.hostUrl}/login"
    client
  }
}
