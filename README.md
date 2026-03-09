# Backend Annuaire

API REST pour la gestion d'un annuaire d'entreprise avec gestion des salariés, sites et services.

## Description du projet

Ce backend fournit une API REST complète pour la gestion d'un annuaire d'entreprise. Il permet de gérer les informations des salariés, des sites et des services de l'entreprise.

### Fonctionnalités principales

**API Publique** (`/api`) :
- Consultation de la fiche détaillée d'un salarié par son ID
- Recherche de salariés par nom ou prénom (recherche insensible à la casse)
- Filtrage des salariés par site
- Filtrage des salariés par service
- Récupération de la liste complète des sites (pour les filtres de recherche)
- Récupération de la liste complète des services (pour les filtres de recherche)

**API Administration** (`/admin`) :
- Gestion CRUD complète des salariés
- Gestion CRUD complète des sites
- Gestion CRUD complète des services
- Contrôles de sécurité avec authentification JWT et rôle ADMIN

**Sécurité** :
- Authentification JWT (JSON Web Tokens)
- Gestion des rôles (USER, ADMIN)
- Endpoints publics accessibles sans authentification
- Endpoints administratifs protégés par rôle ADMIN

### Tests

Le projet inclut une couverture de tests complète :

**Tests Unitaires** (`src/test/java/.../unit_tests`) :
- Tests des services avec mocks pour isoler la logique métier
- Utilisation de `MockSalarieDao` pour simuler les accès aux données
- Validation du comportement des contrôleurs sans dépendances externes
- Exemple : `PublicControllerTest` teste la recherche de salariés avec des données simulées

**Tests d'Intégration** (`src/test/java/.../integration_tests`) :
- Tests end-to-end avec `MockMvc` et contexte Spring complet
- Validation des endpoints HTTP (GET, POST, DELETE)
- Tests de sécurité avec `@WithMockUser` et `@WithUserDetails`
- Vérification des codes de statut HTTP et du format JSON des réponses
- Tests des cas d'erreur (404, 409, etc.)
- Exemple : `AnnuaireApplicationTests` teste toutes les routes publiques et administratives

## Technologies utilisées

- **Spring Boot 4.0.3**
- **Java 17**
- **Spring Data JPA**
- **Spring Security**
- **JWT (jjwt 0.12.6)**
- **MySQL**
- **Lombok**
- **Maven**
- **JUnit 5**
- **Docker & Docker Compose**

## Installation et démarrage

### Prérequis

- Java 17 ou supérieur
- Maven 3.6+
- Docker et Docker Compose

### 1. Initialiser la base de données avec Docker Compose

Le projet inclut un fichier `compose.yml` pour démarrer facilement un conteneur MySQL avec la base de données configurée.

```bash
# Démarrer le conteneur MySQL
docker-compose up -d
```

Cela créera :
- Un conteneur MySQL 8.0 nommé `mysql_server_annuaire`
- Une base de données `annuaire`
- Port exposé : `3306`
- Identifiants : root / qwerty

### 2. Démarrer le backend

```bash
# Compiler et lancer l'application
mvnw spring-boot:run

# Ou sur Windows
mvnw.cmd spring-boot:run
```

L'application démarre sur le port **8080** par défaut.

### 3. Données de test

Le projet inclut un fichier `data-init.sql` qui initialise automatiquement la base de données avec des données aléatoires pour faciliter les tests :

- **1 utilisateur administrateur** : `admin@agro.com` / `admin` (rôle ADMIN)
- **5 sites** : Paris (siège), Nantes, Toulouse, Nice, Lille
- **6 services** : Comptabilité, Production, Accueil, Informatique, Commercial, Service Test
- **100 salariés** répartis sur les différents sites et services

Ces données sont insérées automatiquement au démarrage de l'application grâce à la configuration Spring Boot.

### 4. Tester l'API

Exemples de requêtes :

```bash
# Obtenir la liste des sites (endpoint public)
curl http://localhost:8080/api/sites

# Rechercher un salarié par nom
curl http://localhost:8080/api/salaries/search?nom=Dupont

# S'authentifier comme admin
# Note : Dans l'interface web, taper "admin" (combinaison de touches secrète) pour afficher le formulaire de connexion

```

