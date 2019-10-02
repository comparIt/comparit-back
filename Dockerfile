FROM anapsix/alpine-java

RUN mkdir -p /opt/compare-it-back
COPY ./target/compare-it-back-0.0.1-SNAPSHOT.jar /opt/compare-it-back/app.jar
EXPOSE 8080

CMD ["java", "-jar", "/opt/compare-it-back/app.jar"]
