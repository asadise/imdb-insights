# IMDb Insight
Query over IMDb tsv files.

## Tech Stack
- Spring Boot 4.1.0 
- java 21
- H2 


## Dataset

The size of the three files `title.basics.tsv`, `name.basics.tsv` and `title.principals.tsv` is several GB. Due to GitHub file size limitation, This project uses first 100,000 line of these files:
```
head -100000 title.basics.tsv > sample.title.basics.tsv
head -100000 name.basics.tsv > sample.name.basics.tsv
head -100000 title.principals.tsv > sample.title.principals.tsv
```
- title.basics.tsv
- name.basics.tsv
- title.principals.tsv
- title.ratings.tsv

source file: https://datasets.imdbws.com/

before local run, you can place original dataset in:
``src/main/resources/data/``

## Endpoints
[download postman file](/postman.json) for all endpoints
```
GET /titles/same-director-writer
```
Return all the titles in which both director and writer are the same person and he/she is still alive
```
GET /titles/best-per-year?genre={genre}
```
Get a genre from the user and return best titles on each year for that genre based on

```
GET /principals/common-movies?actor1={actor1}&actor2={actor2}
```
Get two actors and return all the titles in which both of them played at
```
GET /principals/co-actors?actor={actor1}
```
Returns actors who have appeared in at least one common title with the given actor.
```
GET /metrics/request-count
```
Counts all HTTP requests since application startup


## How to Run

```bash
mvn spring-boot:run
```