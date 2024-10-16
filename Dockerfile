FROM openjdk:8-jdk-slim
ADD build/libs/loadLab-*.jar loadLab.jar
RUN echo 'Asia/Shanghai' > /etc/timezone

EXPOSE 8080

ENTRYPOINT ["java","-jar","/loadLab.jar"]