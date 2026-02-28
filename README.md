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

   <img width="403" height="648" alt="image" src="https://github.com/user-attachments/assets/1aeb834b-d29a-4cac-8b05-7418c8c62341" />


##  3. Fonctionnalités CRUD

Nous avons implémenté une gestion complète (Créer, Lire, Mettre à jour, Supprimer) pour les 3 entités principales avec validation des données.

## Agriculteurs 

<img width="433" height="534" alt="image" src="https://github.com/user-attachments/assets/253d75d1-d194-40df-93b5-698039a4f5a6" />

<img width="434" height="378" alt="image" src="https://github.com/user-attachments/assets/738abc6c-5618-4851-a64c-b791f6878910" />


## Produits

<img width="448" height="457" alt="image" src="https://github.com/user-attachments/assets/f9a84a6a-83d5-476b-9bcb-07364edad69a" />

<img width="447" height="348" alt="image" src="https://github.com/user-attachments/assets/02788049-06e1-4881-9e3b-5cd584b9fb50" />



## Ventes


<img width="457" height="421" alt="image" src="https://github.com/user-attachments/assets/dd362abf-293d-49b3-9730-6a3b33683ad5" />

<img width="463" height="333" alt="image" src="https://github.com/user-attachments/assets/03120174-4ff1-46c2-8866-9b4ae114f268" />


##  4. Filtrage et Recherche

L'application permet d'extraire des données précises grâce à des requêtes SQL paramétrées :


<img width="583" height="388" alt="Capture d’écran 2026-02-26 070041" src="https://github.com/user-attachments/assets/22177fc5-5019-4af4-b843-e424e4f51811" />


 <img width="589" height="395" alt="Capture d’écran 2026-02-26 070228" src="https://github.com/user-attachments/assets/b58bc6db-e926-479e-9a8f-3092e2243836" />


<img width="684" height="397" alt="Capture d’écran 2026-02-26 070404" src="https://github.com/user-attachments/assets/4088d5e5-9fe3-4e4a-8f7f-2d3df4608d0d" />


##  5. Sécurité (Hachage)

+ Une interface de Login.

+ Le hachage des mots de passe (BCrypt/SHA-256) : aucun mot de passe n'est stocké en clair dans la base de données.

  
![WhatsApp Image 2026-02-26 at 22 35 56](https://github.com/user-attachments/assets/e812c314-6dd8-49ea-b8bb-5aadb0c8c961)



##  6. Statistiques et Visualisation

Intégration de JFreeChart pour une aide à la décision visuelle.


<img width="595" height="394" alt="Capture d’écran 2026-02-26 070512" src="https://github.com/user-attachments/assets/2b2eb54f-c2c7-448b-970f-ba611801a4ce" />
 

## 7. Ergonomie et Interface

+  Messages de confirmation (Pop-ups).

 +  Navigation fluide entre les modules.


<img width="587" height="394" alt="message1" src="https://github.com/user-attachments/assets/db221064-360c-4df6-b014-7989addf82df" />

<img width="492" height="354" alt="message2" src="https://github.com/user-attachments/assets/a449430c-6e18-46c5-ab1e-30918cd06dac" />




## Technologies Utilisées

  + Langage : Java

   + Bibliothèque Graphique : Swing / AWT

   + Base de données : MySQL

   + Reporting : JFreeChart




## Vidéo démonstrative 



https://github.com/user-attachments/assets/df9ef814-02e5-436c-a9b1-4b811339fec4







