FROM eclipse-temurin:17-jre-alpine
LABEL authors="vigol"

WORKDIR /app

COPY ./target/forum-service-0.0.1-SNAPSHOT.jar ./forum-service.jar

ENV MONGODB_URI=mongodb+srv://just_vik:kdHyRqyEJhpPOF4f@cohort34.an1qxyl.mongodb.net/cohort34db?retryWrites=true&w=majority

CMD ["java", "-jar", "/app/forum-service.jar"]



#docker build -t vigol678/forum-service:0.1.0 -t vigol678/forum-service:latest .

#docker run --name forum -d -p 8080:8080 vigol678/forum-service

#docker push vigol678/forum-service:0.1.0
#docker push vigol678/forum-service:latest