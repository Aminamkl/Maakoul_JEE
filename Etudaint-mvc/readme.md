I- Introduction:

Cet activité pratique est une application Web basée sur Spring MVC, Spring Data JPA et Spring Security qui permet de gérer des étudiants. L'application propose des fonctionnalités suivantes:
  - Chercher des étudiants par nom
  - Faire la pagination
  - Supprimer des étudiants en utilisant la méthode (DELETE au lieu de GET)
  - Saisir et Ajouter des étudiants avec validation des formulaires
  - Editer et mettre à jour des étudiants
  - Créer une page template 
  - Sécuriser l'accès à l'application avec un système d'authentification basé sur Spring security en utilisant la stratégie UseDetails Service
  

II- Présentation d'application:

La page Login
![image](https://user-images.githubusercontent.com/52087288/164979081-ac131f3f-aa83-4ebf-9b2c-e27be5fa6541.png)

Login avec un utilisateur a le rôle "ADMIN"
![1](https://user-images.githubusercontent.com/52087288/164990496-54606754-7008-4745-b01d-272dda3b19a4.PNG)
![image](https://user-images.githubusercontent.com/52087288/164979215-c4990f0a-8f52-49bf-ba52-eb5dbe4e3469.png)

La recherche dans les étudiants
![2](https://user-images.githubusercontent.com/52087288/164990584-b496f836-ee80-4433-a28c-59061e5d69e7.PNG)


La supression d'étudiant "Karam Ahmed"
![image](https://user-images.githubusercontent.com/52087288/164979916-5270d089-011f-4908-b65a-14cff64e7c36.png)
Aprés la supression
![3](https://user-images.githubusercontent.com/52087288/164990621-4f31f6ca-745f-4a57-b305-68b7ce1df775.PNG)

L'ajout d'un étudiant "Wadii Youssef"
![4](https://user-images.githubusercontent.com/52087288/164990637-ec03d62a-fc1d-43af-b5ff-8ce0c92e1282.PNG)
Aprés l'ajout d'étudiant qui a pris l'ID 20
![5](https://user-images.githubusercontent.com/52087288/164990688-9f450697-2264-4985-b225-17b4fe0274d4.PNG)

On va modifier l'email de l'étudiant ayant l'ID 20
![6- edit](https://user-images.githubusercontent.com/52087288/164990714-5c8a83ee-20ec-4c8c-a7bd-b8797e2d6b1e.PNG)
Aprés la modification
![7-edit](https://user-images.githubusercontent.com/52087288/164990735-7bb1eea0-d1f0-466e-bf0b-2c69d50f5402.PNG)

Maintenant on fait l'authentification avec un utilisateur ayant le rôle 
![8](https://user-images.githubusercontent.com/52087288/164990811-3d8afb24-7b47-4c5a-96a2-2083be03ff8f.PNG)

Alors cet utilisateur ne peut que voir et chercher dans la liste des étudiants
![9](https://user-images.githubusercontent.com/52087288/164990835-8632287c-7644-43e9-9483-4ed71bdb935a.PNG)

Si cet utilisateur a saisi un URL contient "/admin", la page 403 sera afficher   
![10](https://user-images.githubusercontent.com/52087288/164990932-96115e2c-b8a7-46ac-b78c-3cbc077065e2.PNG)







