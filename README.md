# nojoir

An attempt at a semi-RESTful data access layer using noir

This is a work in progress.

## Run server

```bash
lein deps
lein run
```

## Querying

```bash
  localhost:8080/:conn/:db/:table.:format
  localhost:8080/:conn/:db/:table.:format?w[col]=val&w[b]=val
  localhost:8080/:conn/:db/:table.:format?f=a,b
  localhost:8080/:conn/:db/:table.:format?limit=10
  localhost:8080/:conn/:db/:table.:format?offset=5
```

Example

```bash
localhost:8080/mysql/some_database/some_table.json
localhost:8080/oracle/some_database/some_table.xml
# select * from some_database.some_table

localhost:8080/mysql/some_database/some_table.json?w[a]=1&w[b]=2
# select * from some_database.some_table where a = 1 and b = 2

localhost:8080/mysql/some_database/some_table.json?w[a]=1&w[b]=2&f=a,c
# select a, c from some_database.some_table where a = 1 and b = 2

localhost:8080/:conn/:db/:table.:format?w[:col]=:val&w[:b]=:val&f=a,b&limit=10
# select a, c from some_database.some_table where a = 1 and b = 2 limit 10

localhost:8080/:conn/:db/:table.:format?w[:col]=:val&w[:b]=:val&f=a,b&limit=10&offset=5
# select a, c from some_database.some_table where a = 1 and b = 2 limit 5, 10
```