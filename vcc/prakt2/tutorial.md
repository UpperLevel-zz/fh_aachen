# Trainingszweck

Diese virtuelle Maschine soll Sie dabei unterstützen containerisierbare Microservices mit JAVA zu entwickeln. Als
Hilfsmittel haben Sie hier für bereits folgende Tools installiert bekommen:

- git
- jdk-11
- eclipse IDE SE
- docker
- curl (Commandline only)

Am Ende des Trainings sollten Sie folgende Szenarien erlernt haben:

- Anlegen eines Java-Projects und das Arbeiten mit selbigem in einem git-Repository
- Anlegen eines einfachen REST-Services
- Anlegen einer Containerisierung des REST-Services unter zu Hilfenahme eines dockerfiles

### Mein Java-Projekt

1. Öffnen Sie die installierte Eclipse IDE und bestätigen Sie die Workspace Abfrage
2. Legen Sie ein neues Java Project in Ihrer Workspace an
   1. Hilfestellung/Tutorial unter: https://www.vogella.com/tutorials/Eclipse/article.html
3. Legen Sie über die Console per "git create repository" ein neues Repository an
   1. Hilfestellung/Tutorial unter: https://kbroman.org/github_tutorial/pages/init.html
   2. (optional): Pushen Sie es auf ein Remoteverwaltungssystem 

### Mein REST-Service

1. Stellen Sie sicher, dass Sie in Ihrem angelegten Java-Project das Java-JDK referenziert haben
2. Folgen Sie den Anweisungen unter: https://spring.io/guides/tutorials/rest/
3. Testen Sie Ihren Webservice in dem Sie ihn über die Console mittels "curl" ansprechen
4. Sichern/Commiten Sie ihre Änderungen

### Mein Docker-Container

1. Legen Sie in Ihrem angelegten Java-Project eine Datei "dockerfile" an
2. Arbeiten Sie sich durch das Tutorial unter: https://spring.io/guides/gs/spring-boot-docker/
3. Testen Sie Ihren containerisierten Webservice in dem Sie ihn über die Console mittels "curl" ansprechen
4. Sichern/Commiten Sie ihre Änderungen
5. (optional): Sichern/Pushen Sie Ihr erstelltes Dockerimages auf ein Remote-Repository