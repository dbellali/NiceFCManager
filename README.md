## Prérequis

Assurez-vous d'avoir installé les éléments suivants avant de démarrer le projet :

- Java 17
- Docker
- Docker Compose

## Configuration de la Base de Données

Nous utilisons Docker Compose pour lancer la base de données. Exécutez la commande suivante pour démarrer la base de données en arrière-plan :

```bash
docker-compose up -d
```

Pour le lancement de l'application un wrapper maven est embarqué dans le projet.

```bash
./mvnw spring-boot:run
```

Pour le lancement des tests.

```bash
./mvnw test
```
