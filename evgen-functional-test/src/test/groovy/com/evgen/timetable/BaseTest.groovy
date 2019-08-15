package com.evgen.timetable


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration

import com.evgen.timetable.builders.EndPointRequestBuilder
import com.evgen.timetable.clients.DbClient
import com.evgen.timetable.clients.HttpClient
import com.evgen.timetable.context.TestConfig

import spock.lang.Specification

@ContextConfiguration(classes = TestConfig.class)
class BaseTest extends Specification {

  @Autowired
  DbClient dbClient

  @Autowired
  EndPointRequestBuilder endPointRequestBuilder

  public def DEFAULT_REST_HEADERS = [
      "Content-Type": MediaType.APPLICATION_JSON_VALUE,
      "Authorization": "token"
  ]

  def getToken() {
    HttpClient loginEndpoint = endPointRequestBuilder.loginHttpClient()
    def loginResponse = loginEndpoint.sendAndGetResponseEntity(
        REQUEST_METHOD: HttpMethod.POST,
        REQUEST_HEADERS: ["Content-Type": MediaType.APPLICATION_FORM_URLENCODED_VALUE],
        REQUEST_BODY: "username=maryaivana&password=testtesttest"
    )
    def token = "Bearer " + loginResponse.getHeaders().get("X-AUTH-TOKEN-VALUE").get(0).toString()
    token
  }

  def getBodyFromFile(String filePath) {
    getClass().getClassLoader().getResourceAsStream(filePath).text
  }

}
