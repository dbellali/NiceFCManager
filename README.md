### Énoncé :

On aimerait pouvoir créer une API pour gérer l’équipe de football de Nice en ligue 1. Le directeur sportif du club voudrait répertorier en base de données la liste de ses joueurs et le budget de l’équipe afin de gérer au mieux le marché de transfert à venir.

### Consigne :

Faire une API Rest qui aura 2 méthodes (l’ajout d’autres méthodes n’est pas de refus et sera considéré comme un bonus) : 
-	Une qui retournera une liste d’équipes contenant chacune une liste de joueurs. 
La liste sera paginée et pourra être triée côté serveur (tri sur nom d’équipe, acronyme et budget).
-	Une autre qui permettra d’ajouter une équipe avec ou sans joueurs associés (tous les autres champs sont obligatoires).




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
