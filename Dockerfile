FROM adoptopenjdk/openjdk11:latest
EXPOSE 9000
COPY target/myapp.jar .
WORKDIR .
CMD ["java","-jar","/myapp.jar"]
