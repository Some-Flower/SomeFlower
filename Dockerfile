
FROM openjdk:17-ea-33-jdk-buster

WORKDIR /usr/src/app

ARG JAR_PATH=./build/libs

COPY ${JAR_PATH}/SomeFlower-0.0.1-SNAPSHOT.jar ${JAR_PATH}/SomeFlower-0.0.1-SNAPSHOT.jar

# 시스템 진입점 정의
CMD ["java","-jar","./build/libs/SomeFlower-0.0.1-SNAPSHOT.jar"]
