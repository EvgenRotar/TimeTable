package com.evgen.timetable.clients

import static org.springframework.http.HttpMethod.POST

import java.nio.charset.StandardCharsets

import org.codehaus.groovy.runtime.GStringImpl
import org.spockframework.util.Assert
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.ByteArrayHttpMessageConverter
import org.springframework.http.converter.StringHttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.UnknownHttpStatusCodeException

import groovy.transform.AutoClone
import groovy.util.logging.Log4j

@Log4j
@AutoClone
class HttpClient {

  final static String EMPTY_STRING = ""
  final static String QUESTION_MARK = "?"

  String hostUrl

  @Autowired
  RestTemplate template

  HttpClient(String hostUrl) {
    this.hostUrl = hostUrl
  }

  String send(params) {
    sendAndGetResponseEntity(params).body
  }

  String sendAndAssertResponseStatus(params){
    def expectedStatus = params?.EXPECTED_STATUS ?: 200
    def actualStatus
    def response
    try {
      def responseEntity = sendAndGetResponseEntity(params)
      actualStatus = responseEntity.getStatusCode().value()
      response = responseEntity.body
    } catch (UnknownHttpStatusCodeException e){
      response = e.responseBodyAsString
      actualStatus = e.rawStatusCode
      log.debug("Response body:\n" + response)
    }
    Assert.that(actualStatus == expectedStatus, "Expected response status: ${expectedStatus}. Actual response status: ${actualStatus}")
    response
  }

  ResponseEntity sendAndGetResponseEntity(params) {

    Map requestParamsVariables = params?.REQUEST_PARAMS_VARIABLES ?: Collections.<String, Object>emptyMap()
    Map<String, String> requestHeaders = params?.REQUEST_HEADERS ?: Collections.<String, String>emptyMap()
    HttpMethod requestMethod = params?.REQUEST_METHOD ?: POST
    def requestBody = params?.REQUEST_BODY ?: EMPTY_STRING
    def requestParamsString = params?.REQUEST_PARAMS_STRING  == null ? hostUrl : hostUrl + QUESTION_MARK + params.REQUEST_PARAMS_STRING

    if (requestBody.class == GStringImpl) {
      requestBody = requestBody.toString()
    }

    def headers = new HttpHeaders()
    requestHeaders.each{key,value -> headers.add(key, value)}

    log.debug "Request headers:\n${headers}"

    def requestEntity = new HttpEntity<String>(requestBody, headers)

    def messageConverter = new StringHttpMessageConverter(StandardCharsets.UTF_8)
    def byteArrayConverter = new ByteArrayHttpMessageConverter()
    messageConverter.writeAcceptCharset = false
    template.messageConverters.clear()
    template.messageConverters.add(messageConverter)
    template.messageConverters.add(byteArrayConverter)

    def response = template.exchange(requestParamsString, requestMethod, requestEntity, String.class, requestParamsVariables)

    log.debug "Response from ${requestParamsString} is :\n${response.body}"

    response
  }
}
