FROM openjdk:13-alpine

WORKDIR /

COPY build/libs/jettyheroku.jar /root/jettyheroku.jar

EXPOSE 8888

ENTRYPOINT ["java", "-jar", "/root/jettyheroku.jar"]
