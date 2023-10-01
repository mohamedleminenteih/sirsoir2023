# Utilisation d'une image de base plus légère
FROM adoptopenjdk:17-jre-hotspot

LABEL maintainer="ucadsir@gmail.com"
VOLUME /sir-data

# Copie du fichier JAR dans l'image
COPY target/sir2023-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8082

# Commande d'entrée pour exécuter l'application
ENTRYPOINT ["java", "-Dspring.profiles.active=local", "-jar", "/app.jar"]
