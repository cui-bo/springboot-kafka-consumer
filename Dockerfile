FROM openjdk:11
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ARG CA_P12=src/main/resources/jks/ca.p12
ARG USER_P12=src/main/resources/jks/ca.p12
COPY ${CA_P12} /tmp/ca.p12
COPY ${USER_P12} /tmp/user.p12
ENTRYPOINT ["java","-jar","/app.jar"]