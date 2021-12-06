# pl.patryk
REST XML Parser

Technology stack:
Java8, Maven, Spring Boot, PostgreSQL

How to run:
- Download project
- Run 'docker-compose up' in 'docker' directory

How to use:
API:
- Get Entries : (GET) http://localhost:8080/api/entries/all
- Get Entry : (GET) http://localhost:8080/api/entries?id=ID where ID is Entry ID
- Add Entry : (POST) http://localhost:8080/api/entries/ -> Files are in 'files' directory. Attach them as form-data in Postman: (file: file_1.xml or file_2.xml, xsd: fileSchema.xsd)
