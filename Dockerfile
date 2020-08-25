FROM openjdk:14
WORKDIR /usr/src/app
EXPOSE 8080
COPY ./build/libs/drontaxi-web-0.0.1-SNAPSHOT.jar .
CMD ["java", "-jar", "drontaxi-web-0.0.1-SNAPSHOT.jar"]