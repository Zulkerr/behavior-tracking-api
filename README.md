# Behavior Tracking API

Diese REST-API dient dem Erfassen und Speichern von Benutzerverhalten (Tracking-Events). Das Projekt wurde als Backend-System mit modernem Java-Stack konzipiert und beinhaltet eine vollständige lokale Entwicklungsumgebung sowie eine CI/CD-Pipeline.

## Verwendete Technologien

* Java 17
* Spring Boot (Web, Data JPA, Validation)
* PostgreSQL (Produktion und lokale Entwicklung)
* Docker & Docker Compose
* JUnit 5 & Mockito (Unit- und Web-Schicht-Tests)
* GitHub Actions (Continuous Integration)
* Gradle

## Lokale Entwicklung

Um das Projekt lokal auszuführen, müssen Docker und Java 17 installiert sein.

### 1. Datenbank starten
Das Projekt verwendet eine PostgreSQL-Datenbank. Diese kann bequem über Docker Compose gestartet werden. Führe dazu im Hauptverzeichnis folgenden Befehl aus:

`docker-compose up -d`

Die Datenbank ist nun auf Port 5432 erreichbar. Die Zugangsdaten sind in der `docker-compose.yml` sowie der `application.properties` konfiguriert.

### 2. Anwendung starten
Starte die Spring Boot Anwendung entweder über deine IDE (IntelliJ) oder über das Terminal mit dem Gradle-Wrapper:

`./gradlew bootRun`

Die API ist standardmäßig unter `http://localhost:8080` erreichbar.

### 3. Tests ausführen
Das Projekt enthält Unit-Tests sowie isolierte Controller-Tests (@WebMvcTest). Um alle Tests auszuführen:

`./gradlew test`

## CI/CD Pipeline

Das Projekt verfügt über eine automatisierte GitHub Actions Pipeline (`.github/workflows/build.yml`).
Bei jedem Push auf den `main`-Branch wird:
1. Eine temporäre PostgreSQL-Datenbank als Docker-Service gestartet.
2. Das Projekt mit Gradle kompiliert.
3. Alle Tests gegen die echte Datenbank-Umgebung ausgeführt.
4. Das fertige `.jar`-Artefakt für spätere Deployments gespeichert.

## Zukünftige Erweiterungen

Dieses Projekt dient als solides Fundament und kann in Zukunft um folgende professionelle Features erweitert werden:

* Cloud Deployment: Veröffentlichung der gebauten `.jar`-Datei oder als Docker-Image auf Cloud-Plattformen wie Render, Heroku oder AWS.
* API Security: Integration von Spring Security zur Absicherung der Endpunkte, sodass nur authentifizierte Clients (z. B. via JWT - JSON Web Tokens) Tracking-Daten senden können.
* API Dokumentation: Einbau von Swagger / OpenAPI, um eine interaktive und nutzerfreundliche Dokumentation der verfügbaren REST-Endpunkte für das Frontend-Team bereitzustellen.