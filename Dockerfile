FROM eclipse-temurin:17-jdk

RUN mkdir -p /tmp/project

WORKDIR /tmp/project

COPY . /tmp/project/

RUN /tmp/project/gradlew clean build

FROM eclipse-temurin:17-jre

COPY --from=0 /tmp/project/build/libs/*.jar /root/

EXPOSE $PORT

ENTRYPOINT ["java", "-jar", "/root/jettyheroku.jar"]
