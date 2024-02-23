FROM eclipse-temurin:21.0.2_13-jre-jammy
LABEL maintainer="Alex Nikol <ales911.nikol@gmail.com>"
LABEL version="0.0.1-SNAPSHOT"
LABEL description="jcache simple example"
COPY /target/jcache-simple-example-0.0.1-SNAPSHOT.jar jcache-simple-example-0.0.1-SNAPSHOT.jar
HEALTHCHECK --interval=5s \
            --timeout=3s \
            CMD curl -f http://localhost:8080/actuator/health || exit 1
EXPOSE 8080
ENTRYPOINT ["java","-jar","jcache-simple-example-0.0.1-SNAPSHOT.jar"]