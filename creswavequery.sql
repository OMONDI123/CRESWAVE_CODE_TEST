update hp_patient_visit_ecare set issync=1;


-----MYSQL

CREATE USER 'maishapoa' IDENTIFIED BY 'creswave$$KE2019';
GRANT ALL PRIVILEGES ON swiftfood.* TO 'maishapoa'@'%';
mysql> 
SHOW VARIABLES LIKE 'port';

FLUSH PRIVILEGES;

