AIRLINE RESERVATION SYSTEM: RANGERS

DATABASE SETUP:
All mysql commands can be found in the the DB folder. 
Inside that folder there will be three files airport.sql, airportDDL.sql, and db.sql. The airportDDL.sql contains the commands
to create the airport table, airport.sql contains a large file of INSERTS for the airport table with approximately 6000 inserts, and the db.sql can create the rest of the table and fill it out with data.

ORDER:
airportDDL.sql
airport.sql
db.sql

PROJECT SETUP:
To run the project a db.properties files is necessary to create within the src folder. 
This file needs to contain the url, the username, and the password for the mysql database
They have to be in the following format.

db.conn.url=jdbc:mysql://rangers-cs157a.ccxbf6i9cqfo.us-west-1.rds.amazonaws.com:3306/Rangers?serverTimezone=UTC
db.username=admin
db.password=rangers-dbserver

To run the project simply navigate to the AirlineSystem.java and run that file. This file is located within the src folder.