# Backend Annuaire

API REST pour la gestion d'un annuaire d'entreprise avec gestion des salariés, sites et services.

## Technologies utilisées

- **Spring Boot 4.0.3**
- **Java 17**
- **Spring Data JPA**
- **Spring Security**
- **JWT (jjwt 0.12.6)**
- **MySQL**
- **Lombok**
- **Maven**

## Configuration

### Base de données

Configurez votre base de données MySQL dans `application.properties` :

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/annuaire
spring.datasource.username=votre_utilisateur
spring.datasource.password=votre_mot_de_passe
jwt.secret=votre_secret_jwt
```

### Démarrage avec Docker

```bash
docker compose up -d
```

### Compilation et démarrage

```bash
./mvnw.cmd clean install
./mvnw.cmd spring-boot:run
```

## Architecture

Le projet suit une architecture en couches :

- **Model** : Entités JPA (Salarie, Site, Service, Utilisateur)
- **DAO** : Repositories Spring Data JPA
- **Controller** : Endpoints REST
- **Security** : Configuration Spring Security + JWT
- **Exception** : Gestion centralisée des exceptions

## Documentation des API

### Authentification

#### Connexion

```http
POST /connexion
Content-Type: application/json

{
  "email": "admin@example.com",
  "password": "motdepasse"
}
```

**Réponse :**
```
Bearer eyJhbGciOiJIUzUxMiJ9...
```

Pour les requêtes authentifiées, incluez le token JWT dans l'en-tête :
```http
Authorization: Bearer <votre_token>
```

---

### API Publiques (Visiteurs)

Ces endpoints sont accessibles sans authentification.

#### 1. Afficher la fiche d'un salarié

```http
GET /api/salaries/{id}
```

**Réponse :**
```json
{
  "id": 1,
  "nom": "Dupont",
  "prenom": "Jean",
  "telephoneFixe": "0123456789",
  "telephonePortable": "0612345678",
  "email": "jean.dupont@example.com",
  "service": {
    "id": 1,
    "nom": "Comptabilité"
  },
  "site": {
    "id": 1,
    "designation": "Siège social",
    "ville": "Paris"
  }
}
```

#### 2. Rechercher des salariés par nom

```http
GET /api/salaries/search?nom=Dup
```

**Description :** Recherche par nom ou prénom (insensible à la casse)

**Réponse :** Tableau de salariés correspondants

#### 3. Rechercher des salariés par site

```http
GET /api/salaries/site/{siteId}
```

**Exemple :**
```http
GET /api/salaries/site/1
```

**Réponse :** Tableau de tous les salariés du site spécifié

#### 4. Rechercher des salariés par service

```http
GET /api/salaries/service/{serviceId}
```

**Exemple :**
```http
GET /api/salaries/service/2
```

**Réponse :** Tableau de tous les salariés du service spécifié

#### 5. Obtenir la liste de tous les sites

```http
GET /api/sites
```

**Réponse :**
```json
[
  {
    "id": 1,
    "designation": "Siège social",
    "ville": "Paris"
  },
  {
    "id": 2,
    "designation": "Agence Lyon",
    "ville": "Lyon"
  }
]
```

#### 6. Obtenir la liste de tous les services

```http
GET /api/services
```

**Réponse :**
```json
[
  {
    "id": 1,
    "nom": "Comptabilité"
  },
  {
    "id": 2,
    "nom": "Informatique"
  },
  {
    "id": 3,
    "nom": "Commercial"
  }
]
```

---

### API Administrateur

Ces endpoints nécessitent une authentification et le rôle **ADMIN**.

#### Sites

##### Lister tous les sites

```http
GET /admin/sites
Authorization: Bearer <token>
```

##### Obtenir un site par ID

```http
GET /admin/sites/{id}
Authorization: Bearer <token>
```

##### Créer un site

```http
POST /admin/sites
Authorization: Bearer <token>
Content-Type: application/json

{
  "designation": "Agence Marseille",
  "rue": "123 Avenue de la République",
  "cp": "13001",
  "ville": "Marseille",
  "estSiege": false
}
```

##### Modifier un site

```http
PUT /admin/sites/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "designation": "Agence Marseille Centre",
  "rue": "123 Avenue de la République",
  "cp": "13001",
  "ville": "Marseille",
  "estSiege": false
}
```

##### Supprimer un site

```http
DELETE /admin/sites/{id}
Authorization: Bearer <token>
```

---

#### Services

##### Lister tous les services

```http
GET /admin/services
Authorization: Bearer <token>
```

##### Obtenir un service par ID

```http
GET /admin/services/{id}
Authorization: Bearer <token>
```

##### Créer un service

```http
POST /admin/services
Authorization: Bearer <token>
Content-Type: application/json

{
  "nom": "Production",
  "description": "Département de production"
}
```

##### Modifier un service

```http
PUT /admin/services/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "nom": "Production industrielle",
  "description": "Département de production et fabrication"
}
```

##### Supprimer un service

```http
DELETE /admin/services/{id}
Authorization: Bearer <token>
```

---

#### Salariés

##### Lister tous les salariés

```http
GET /admin/salaries
Authorization: Bearer <token>
```

##### Obtenir un salarié par ID

```http
GET /admin/salaries/{id}
Authorization: Bearer <token>
```

##### Créer un salarié

```http
POST /admin/salaries
Authorization: Bearer <token>
Content-Type: application/json

{
  "nom": "Martin",
  "prenom": "Sophie",
  "telephoneFixe": "0198765432",
  "telephonePortable": "0687654321",
  "email": "sophie.martin@example.com",
  "service": {
    "id": 1
  },
  "site": {
    "id": 1
  }
}
```

##### Modifier un salarié

```http
PUT /admin/salaries/{id}
Authorization: Bearer <token>
Content-Type: application/json

{
  "nom": "Martin",
  "prenom": "Sophie",
  "telephoneFixe": "0198765432",
  "telephonePortable": "0687654321",
  "email": "sophie.martin@example.com",
  "service": {
    "id": 2
  },
  "site": {
    "id": 1
  }
}
```

##### Supprimer un salarié

```http
DELETE /admin/salaries/{id}
Authorization: Bearer <token>
```

---

## Codes de réponse HTTP

| Code | Signification |
|------|---------------|
| 200 | OK - Requête réussie |
| 201 | Created - Ressource créée |
| 204 | No Content - Suppression réussie |
| 400 | Bad Request - Données invalides |
| 401 | Unauthorized - Authentification requise |
| 403 | Forbidden - Accès refusé (rôle insuffisant) |
| 404 | Not Found - Ressource non trouvée |
| 409 | Conflict - Conflit (ex: email déjà existant) |

## Gestion des erreurs

Les erreurs sont retournées au format JSON :

```json
{
  "message": "Salarié avec l'id 999 est introuvable",
  "status": "NOT_FOUND"
}
```

## Cas d'usage

### Flux visiteur : Recherche d'un salarié

1. **Obtenir la liste des sites** : `GET /api/sites`
2. **Sélectionner un site** et récupérer les salariés : `GET /api/salaries/site/1`
3. **Cliquer sur un salarié** pour voir sa fiche complète : `GET /api/salaries/1`

### Flux visiteur : Recherche par nom

1. **Saisir des lettres** dans le champ de recherche : `GET /api/salaries/search?nom=Dup`
2. **Cliquer sur un résultat** : `GET /api/salaries/1`

### Flux administrateur : Création d'un salarié

1. **Se connecter** : `POST /connexion`
2. **Récupérer le token JWT** de la réponse
3. **Créer le salarié** : `POST /admin/salaries` avec le token dans l'en-tête

## Sécurité

- **CORS** : Configuré pour accepter toutes les origines (à adapter en production)
- **JWT** : Tokens signés avec HMAC-SHA
- **Password** : Hashage BCrypt
- **Session** : Stateless (pas de cookies)

## Modèle de données

### Utilisateur
- id (Integer)
- nom, prenom
- email (unique)
- password (hashé)
- role (USER, ADMIN)

### Site
- id (Integer)
- designation
- rue, cp, ville
- estSiege (boolean)

### Service
- id (Integer)
- nom (unique)
- description

### Salarié
- id (Integer)
- nom, prenom
- telephoneFixe, telephonePortable
- email (unique)
- service (relation ManyToOne)
- site (relation ManyToOne)

## Licence

Projet éducatif