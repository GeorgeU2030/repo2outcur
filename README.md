## Outcome Curricular Management Software

This project is the backend code part of a bigger project aimed to cover the administration facets of managing courses, academic competencies, performance indicators - and the relationship between these three -, scoring rubrics and other academic profiling tools, elements and statistics. The tech stack featured in this repository is listed down below.


## Tech Stack

Java 17
Maven
Spring Boot 3
Spring 6
Project lombok
Mapstruts
Mockito
Jacoco


---
## Getting Started

First, compile the project with maven:

```bash
mvn clean install
```

Run the main class, for local execution use the dev profile.

```bash

```

Then, open [http://localhost:9992/outcrrapi/v1/auth/faculties/](http://localhost:3013) with your browser to access it. The default port is [9992](http://localhost:3000), and the configuration can be changed from the file ```/application-dev.properties```.

You can start editing the page by modifying `app/page.tsx`. The page auto-updates as you edit the file through hot reloading.

---

## Stack Documentation

###  Java 17

To learn more about Java 17, take a look at the following resources:

---

## Sonarqube Badges


[![Coverage](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=coverage&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Bugs](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=bugs&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Code Smells](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=code_smells&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Duplicated Lines (%)](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=duplicated_lines_density&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Lines of Code](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=ncloc&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Technical Debt](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=sqale_index&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)

[![Vulnerabilities](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=vulnerabilities&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Security Rating](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=security_rating&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Maintainability Rating](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=sqale_rating&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Reliability Rating](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=reliability_rating&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)
[![Quality Gate Status](https://pi2tools.icesi.edu.co/sonar/api/project_badges/measure?project=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e&metric=alert_status&token=sqb_1019c88ef5af63cf64649891b1428c90877c942b)](https://pi2tools.icesi.edu.co/sonar/dashboard?id=OUTCUR_outcome-curr-mgmnt-backend_AYthuxqSWeULmn6qh66e)