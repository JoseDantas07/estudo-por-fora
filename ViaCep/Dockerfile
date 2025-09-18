FROM openjdk:21
LABEL maintainer="jose"
WORKDIR /app
COPY build/libs/ViaCep-0.0.1-SNAPSHOT-plain.jar /app/viaCep.jar
ENTRYPOINT ["java","-jar","viaCep.jar"]