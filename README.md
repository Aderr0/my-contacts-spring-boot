# Application Java avec Spring Boot

Ce projet est une auto-formation sur les technologies Java et Spring Boot. Dans l'ensemble, certaines méthodes pourraient être optimisées et l'interface pourrait être plus attrayante, mais l'objectif était de travailler sur le plus de fonctionnalités possible pour améliorer les compétences et mieux comprendre comment ces applications fonctionnent.

## Structure de l'application

La structure MVC est utilisée pour organiser l'application de la manière suivante :

- Le package Repository pour la connexion à la BDD
- Le package Beans pour décrire la BDD
- Le package Service pour effectuer des tâches sur la BDD
- Le package Controller pour générer des URL qui font appel au service
- Le package View pour le frontend

La liste des dépendances Spring utilisées comprend :

- Thymeleaf
- Vaadin
- Devtools
- Lombok
- MySQL

Vaadin est utilisé pour toute la partie Front + pour la navigation entre les pages, tandis que Devtools permet d'automatiser le rafraîchissement de la page après la compilation en utilisant l'extension live-reload.

L'application est connectée à une base de données locale MySQL.

## Fonctionnement de l'application

Pour commencer, une base de données est créée avec une table "contacts" qui contient les champs nom, prénom, mail et numéro de téléphone. Une bean est créée pour décrire cette table et le décorateur `@Data` est utilisé pour éviter d'écrire les getters et les setters de la table.

Les décorateurs sont également utilisés sur les attributs pour ajouter des contraintes telles que la taille ou le type de donnée.

Ensuite, un Repository est créé qui étend JpaRepository pour avoir des List en retour plutôt que des Iterable si l'on utilise CrudRepository.

Le Service est développé avec trois méthodes :

1. Récupérer tous les contacts
2. Récupérer certains contacts avec un filtre sur le nom et le prénom
3. Création d'un contact

Le Controller est ensuite développé pour implémenter les 3 méthodes du Service :

1. GET - /contacts?name=xxx ⇒ la variable de chemin "name" est facultative
2. POST - /contacts

Enfin, deux vues sont développées :

1. Pour la liste des contacts
2. Pour créer un nouveau contact

## Comment utiliser l'application

Pour utiliser cette application, vous devez tout d'abord la cloner sur votre ordinateur. Ensuite, vous pouvez lancer l'application en la compilant avec votre IDE Java préféré ou en utilisant la commande `mvn spring-boot:run` dans le terminal.

Une fois l'application lancée, vous pouvez accéder à la liste des contacts à l'adresse `http://localhost:9000/`. 

N'oubliez pas de configurer votre base de données MySQL locale avant de lancer l'application.
