> Les fichiers faisant référence à "Users" dans le code étaient initialement destinés à l'entraînement sur le langage et sur le framework SpringBoot avant de passer à la partie "Products". Tous les fichiers concernant "Users" peuvent être ignorés.

# Configuration

## Variables d'environnement pour la création du service de la base de données :

Les viariables d'environnement sont à parametrer dans le fichier `docker-compose.yml` :

```
environment:
    MYSQL_ROOT_PASSWORD: root
    MYSQL_DATABASE: alten_shop
    MYSQL_USER: user
    MYSQL_PASSWORD: password
```

## Variables d'environnement pour la création du service de l'API :

Les viariables d'environnement sont à parametrer dans le fichier `docker-compose.yml` :

```
environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://database:3306/alten_shop?useSSL=false&allowPublicKeyRetrieval=true
      SPRING_DATASOURCE_USERNAME: user
      SPRING_DATASOURCE_PASSWORD: password
```

> `database` fait référence au nom du service de la base de données. Si vous modifiez le nom du service de la base de données dans le fichier ` docker-compose.yml` pensez à  mettre à jour cette ligne.

> `alten_shop` fait référence au nom de la base de données. Si vous modifier le nom de votre base de données pensez à mettre à jour cette ligne.

# Démarrage

Pour pouvoir démarrer le projet, executez cette commande depuis le dossier `back` :

```
docker compose up -d
```

# Utilisation de l'API
L'url de l'api est : [http://localhost:8080](http://localhost:8080)

| Resource           | POST                  | GET                            | PATCH                                    | PUT | DELETE           |
| ------------------ | --------------------- | ------------------------------ | ---------------------------------------- | --- | ---------------- |
| **/products**      | Create a new products | Retrieve all products          | X                                        | X   |     X            |
| **/products/1**    | X                     | Retrieve details for product 1 | Update details of product 1 if it exists | X   | Remove product 1 |

Vous trouverez également une collection postamn `alten-shop java.postman_collection.json` dans le dossier `back`.

## Base de données

La table `products` stocke les informations sur chaque produit. Voici un aperçu des colonnes de la table `products` :

| Colonne           | Description                                                                     | Requis |
|-------------------|---------------------------------------------------------------------------------|--------|
| id                | Identifiant unique du produit                                                   | Auto   |
| code              | Code du produit                                                                 | ✅     |
| name              | Nom du produit                                                                  | ✅     |
| description       | Description du produit                                                          | ✅     |
| price             | Prix du produit (DECIMAL(10, 2))                                                | ✅     |
| quantity          | Quantité disponible en stock                                                    | ✅     |
| inventory_status  | Statut de l'inventaire du produit (ENUM('INSTOCK', 'LOWSTOCK', 'OUTOFSTOCK'))   | ✅     |
| category          | Category du produit (ENUM('Accessories', 'Clothing', 'Electronics', 'Fitness')) | ✅     |
| image             | URL de l'image du produit                                                       | ❌     |
| rating            | Évaluation du produit                                                           | ❌     |
| created_at        | Timestamp de création du produit                                                | Auto   |
| updated_at        | Timestamp de la dernière mise à jour du produit                                 | Auto   |

La table `users` est également présente dans la base de données mais celle-ci n'est pas a prendre en compte pour le projet. Voici un aperçu des colonnes de la table `users` :

| Colonne      | Description              | Requis |
|--------------|--------------------------|--------|
| id           | Identifiant unique de l'utilisateur | Auto   |
| first_name   | Prénom de l'utilisateur   | ✅     |
| last_name    | Nom de l'utilisateur      | ✅     |

Ces tables sont définies dans le fichier SQL `init.sql` qui se trouve dans `back/src/main/resources`.