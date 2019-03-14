FROM java:8
EXPOSE 8080
ADD target/timetable-0.0.1-SNAPSHOT.jar timetable-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","timetable-0.0.1-SNAPSHOT.jar"]