# CRESWAVE_CODE_TEST
This is a blog system.
#THE GENERAL SCOPE OF THE SYSTEM
For this system, there are two actors, ADMIN and USER.
ADMIN has controll over the entire system 
USER can only register,update their details,view blogs posted by the ADMIN,make comments on the blogs, update comment,delete comment, users are also restricted to see only the comments they created.

Users can also search for the blogs by content/title.
For purpose of security and audit, the system uses Jwt (JSON Web Token)authentication which expires after every 5 mins.
I have also ensure that every post request is audited for further security measures.

#GUIDLINES ON HOW TO RUN AND TEST THE APIS
1. Clone the project from the git repository using the following command
#git clone https://github.com/OMONDI123/CRESWAVE_CODE_TEST.git

2. Ensure you have eclipse/intelliJ IDE for running the project.
3. Create a directory /var/www/html/blogs/uploads/
to do this use this command
#sudo mkdir /var/www/html/blogs/uploads/
4. Grand RWX on this folder by running this command 
#sudo chmod -R 777 /var/www/html/blogs/uploads/

The folder is created for storing blog images,videos and any file uploads. The essence of this is to ensure database efficiency since file uploads consume a large space in the database which may later result to high loading time for the database when a request is made to the database. For further optimization of the database we can use database indexing instead of hiting the database directly each time a request is made to the database.

5.create a database called blog using postgresql.
to do this follow the below steps,
#sudo su 
enter password
#su postgres
#psql
#CREATE USER adempiere WITH PASSWORD 'bunde1234';

#CREATE DATABASE blog WITH OWNER adempiere;
6. After creating the db, navigate to the project root directory and restore the db using the following command
#psql -U adempiere -d blog -f blog-2024-02-26.dmp

7.clean and build the project by running the following command
#mvn clean install
8.Import the project to eclipse if you are using eclipse as per my case though not limited to eclipse also intelliJ can do.
To open the project in eclipse, launch eclipse and click on import existing maven project,navigate to the project location and import it.

9. Once the imported, navigate to the project main class and run the project as java application/springbooot application.

10. If the project is successfully running navigate to the browser and paste this url to expose swagger for the api documentation.
#http://localhost:8084/swagger-ui.html#/role-controller/getAllRoleUsingGET

This will expose all the available endpoints for the project.
11. Move on to test each of the endpoints.

#@Thank you and goodbye.
#@Designed and developed by Austine Bunde.


