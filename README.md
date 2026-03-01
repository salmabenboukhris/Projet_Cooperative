### 🌾 Système de Gestion : Coopérative Agricole

##  Description du Projet

Ce projet consiste en une application de bureau destinée à la Coopérative Agricole.
Elle permet de gérer le cycle de vente des produits agroalimentaires, de suivre les
stocks des agriculteurs et d'analyser les performances commerciales via des outils statistiques.

##  1. Conception (UML)
  
La phase de conception a été réalisée avec le standard UML pour garantir une structure de données
 robuste et une logique métier claire.

## Diagramme de Cas d'Utilisation

Objectif : Identifier les acteurs et les fonctionnalités clés.

![WhatsApp Image 2026-02-26 at 23 39 22](https://github.com/user-attachments/assets/896974f2-819d-455d-8680-e6c228e3b24e)



## Diagramme de Classes

Objectif : Modéliser les entités ProduitAgro, Agriculteur, et VenteAgro ainsi que leurs relations.

 <img width="623" height="425" alt="image" src="https://github.com/user-attachments/assets/3479bc3c-f8be-4b6d-85b2-951181c44531" />



## 2. Architecture du Projet

L'application respecte le design pattern DAO (Data Access Object) pour une séparation 
stricte entre l'interface utilisateur (UI), la logique métier et l'accès aux données.


![ARCHITECTURE PROJET](https://github.com/user-attachments/assets/301c3e3b-96c8-41b8-84d7-bcdfbb2be8fc)


##  3. Fonctionnalités CRUD

Nous avons implémenté une gestion complète (Créer, Lire, Mettre à jour, Supprimer) pour les 3 entités principales avec validation des données.


##  4. Filtrage et Recherche

L'application permet d'extraire des données précises grâce à des requêtes SQL paramétrées :


##  5. Sécurité (Hachage)

+ Une interface de Login.

+ Le hachage des mots de passe (BCrypt/SHA-256) : aucun mot de passe n'est stocké en clair dans la base de données.

  
![WhatsApp Image 2026-02-26 at 22 35 56](https://github.com/user-attachments/assets/e812c314-6dd8-49ea-b8bb-5aadb0c8c961)



##  6. Statistiques et Visualisation

Intégration de JFreeChart pour une aide à la décision visuelle.


![WhatsApp Image 2026-03-01 at 14 35 49](https://github.com/user-attachments/assets/4c318630-9ee4-44bd-bfa3-c9bec4e681e3)


## 7. Ergonomie et Interface

+  Messages de confirmation (Pop-ups).

 +  Navigation fluide entre les modules.



## Technologies Utilisées

  + Langage : Java

   + Bibliothèque Graphique : Swing / AWT

   + Base de données : MySQL

   + Reporting : JFreeChart




## Vidéo démonstrative 




https://github.com/user-attachments/assets/a4df4a59-f8dd-47c2-988c-7950d4f1fc45









