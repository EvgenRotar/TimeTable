package com.evgen.timetable.teacher


import org.springframework.http.HttpMethod

import com.evgen.timetable.BaseTest

class TeacherTest extends BaseTest {
  def "getTeacher"() {
    setup:
    def teacherResponseFromFile = "teacher/teacherResponse.json"
    DEFAULT_REST_HEADERS."Authorization" = getToken()

    when:
    def httpClient = endPointRequestBuilder.teacherHttpClient("7")
    def teacherResponse = httpClient.sendAndGetResponseEntity(
        REQUEST_METHOD: HttpMethod.GET,
        REQUEST_HEADERS: DEFAULT_REST_HEADERS
    )

    then:
    assert getBodyFromFile(teacherResponseFromFile) == (teacherResponse.getBody() as String)

  }
}
