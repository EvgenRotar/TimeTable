docker run -p 3307:3306 --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql
docker build -t timetable .
docker run -p 8080:8080 timetable