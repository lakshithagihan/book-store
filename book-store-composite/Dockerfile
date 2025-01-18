FROM openjdk:11

EXPOSE 8091

RUN mkdir app

ADD target/book-store-composite-1.0.0.RELEASE.jar /app/book-store-composite.jar

ADD config/application.yml /app/application.yml

ENTRYPOINT ["java","-jar","/app/book-store-composite.jar","--spring.config.location=/app/application.yml"]