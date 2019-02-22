docker run --name mysql -e MYSQL_ROOT_PASSWORD=root -d mysql
docker inspect --format '{{ .NetworkSettings.IPAddress }}' mysql