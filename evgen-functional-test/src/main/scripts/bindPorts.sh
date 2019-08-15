if [ -z "${DOCKER_PROJECT_NAME}" ]; then
   DOCKER_PROJECT_NAME="evgen-functional-test";
fi

APP_CONTAINER=$(docker ps -l --filter "name=${DOCKER_PROJECT_NAME}_APP_1[a-zA-Z0-9_.-]{0,20}" --format "{{.Names}}")
DB_CONTAINER=$(docker ps -l --filter "name=${DOCKER_PROJECT_NAME}_DB_1[a-zA-Z0-9_.-]{0,20}" --format "{{.Names}}")
SCC_CONTAINER=$(docker ps -l --filter "name=${DOCKER_PROJECT_NAME}_SCC_1[a-zA-Z0-9_.-]{0,20}" --format "{{.Names}}")

DIR="target/test-classes"
mkdir -p $DIR
(echo -n app.port= ; docker inspect --format='{{(index (index .NetworkSettings.Ports "8080/tcp") 0).HostPort}}' ${APP_CONTAINER} ) > "$DIR/ports.properties"
(echo -n app.debug.port= ; docker inspect --format='{{(index (index .NetworkSettings.Ports "5005/tcp") 0).HostPort}}' ${APP_CONTAINER} ) >> "$DIR/ports.properties"
(echo -n db.port= ; docker inspect --format='{{(index (index .NetworkSettings.Ports "3306/tcp") 0).HostPort}}' ${DB_CONTAINER} ) >>  "$DIR/ports.properties"
(echo -n scc.port= ; docker inspect --format='{{(index (index .NetworkSettings.Ports "8888/tcp") 0).HostPort}}' ${SCC_CONTAINER} ) >> "$DIR/ports.properties"

cat $DIR/ports.properties