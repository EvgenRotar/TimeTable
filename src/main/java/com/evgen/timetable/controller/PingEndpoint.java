package com.evgen.timetable.controller;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

@Component
@Endpoint(id = "ping")
public class PingEndpoint {

  @ReadOperation
  public String ping() {
    return "Server Up";
  }

}