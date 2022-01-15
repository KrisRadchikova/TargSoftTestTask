mvn clean install
docker build -t targsofttesttask .
docker-compose -f docker-compose.yml up