FROM openjdk:21

RUN cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' > /etc/timezone

WORKDIR /app

COPY app.jar /app/app.jar

CMD ["java", "-Xmx1024m", "-Xms512m", "-jar", "app.jar"]
