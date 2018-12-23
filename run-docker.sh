docker run -v $PWD:/project -w /project maven:3.5.2-alpine mvn package && 
docker-compose -f docker/docker-compose.yml up
