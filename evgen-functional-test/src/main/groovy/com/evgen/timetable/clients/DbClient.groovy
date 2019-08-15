package com.evgen.timetable.clients

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component

import groovy.sql.GroovyRowResult
import groovy.sql.Sql

@Component
class DbClient {

  @Value('${db.url}')
  String dbUrl
  @Value('${db.username}')
  String dbUser
  @Value('${db.password}')
  String dbPassword

  String dbDriverClass = "com.mysql.cj.jdbc.Driver"

  GroovyRowResult executeQuery(String query) {
    def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriverClass)
    def rowResult = sql.firstRow(query)
    sql.close()
    rowResult
  }

  def executeQueryWithoutResult(String query) {
    def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriverClass)
    sql.execute(query)
    sql.close()
  }

  String executeQuery(String query, String nameOfRow) {
    def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriverClass)
    def sql1 = sql.firstRow(query).get(nameOfRow)
    def str = sql1.getSubString(1, (int)sql1.length())
    sql.close()
    str
  }


  List<GroovyRowResult> executeQueryWithParam(final def query, final def params) {
    def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriverClass)
    def rowResult = sql.rows(query, params)
    sql.close()
    rowResult
  }

  List<GroovyRowResult> executeQueryAndReturnAllRows(String query) {
    def sql = Sql.newInstance(dbUrl, dbUser, dbPassword, dbDriverClass)
    def rows = sql.rows(query)
    sql.close()
    rows
  }

}