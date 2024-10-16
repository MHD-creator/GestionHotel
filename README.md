# GestionHotel
Le projet de Gestion d'Hôtel vise à développer une application de gestion simplifiée pour un hôtel. L'application permet aux administrateurs de gérer les chambres, les clients, et les réservations. Ce système offre une interface utilisateur intuitive et efficace pour faciliter les opérations quotidiennes de l'hôtel.
#Fonctionnalités du Système
  . Gestion des Chambres
L'application permet aux administrateurs de :
Ajouter de nouvelles chambres avec des informations telles que le numéro de chambre, le type de chambre, et le prix par nuit.
Visualiser la liste des chambres disponibles avec leurs informations détaillées.
Modifier ou supprimer des chambres existantes si nécessaire.
  . Gestion des Clients
L'administrateur peut gérer les clients à travers les fonctionnalités suivantes :

Ajouter un nouveau client avec un identifiant unique et son nom.
Afficher la liste des clients enregistrés dans le système.
  . Gestion des Réservations
Le module de gestion des réservations permet à l'administrateur de :

Créer une nouvelle réservation en sélectionnant un client et une chambre spécifique, avec une durée définie.
Visualiser toutes les réservations passées et en cours.
Gérer les réservations en cours avec la possibilité d'annuler ou de modifier une réservation.
#Technologies Utilisées
 .Java
Le langage principal utilisé pour la logique métier et l'interface graphique.

  .JavaFX
Utilisé pour créer l'interface utilisateur graphique. Il permet une conception claire et modulaire avec des éléments graphiques (TableView, Button, TextField, etc.).

  .PostgreSQL
Base de données relationnelle utilisée pour stocker les informations de l'hôtel. Les services liés à la gestion des chambres, clients et réservations interagissent avec cette base de données.

  .JDBC (Java Database Connectivity)
Permet la connexion à la base de données PostgreSQL et l'exécution des requêtes SQL.


#Interface utlisateur
    Panneau d'Administration
Le panneau d'administration centralise les fonctionnalités de gestion des chambres, des clients et des réservations. Il se compose de trois sections principales :

Gestion des chambres : Une interface permettant d’ajouter, de consulter et de gérer les chambres.
Gestion des clients : Permet de visualiser la liste des clients et d’enregistrer de nouveaux clients.
Gestion des réservations : Permet de consulter toutes les réservations, d’ajouter de nouvelles réservations et de gérer les réservations existantes.
Chaque section est dotée d'un tableau  permettant de lister les données, ainsi que de formulaires pour ajouter des nouvelles entrées.

#Aperçu des Améliorations Futures
Les prochaines étapes pour améliorer le système incluent les éléments suivants :

Gestion des paiements : Implémenter un système de gestion des paiements pour chaque réservation.
Notifications : Mettre en place un système de notifications pour les réservations à venir.
Gestion des employés : Ajouter un module pour gérer les employés de l'hôtel.
Rapports : Générer des rapports d'activités (revenus, taux d'occupation des chambres, etc.).
Sécurité : Améliorer la sécurité de l'application avec une gestion des droits d'accès (par exemple, un module de connexion pour les administrateurs).
