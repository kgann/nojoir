# nojoir

An attempt at a semi-RESTful data access layer using noir

This is a work in progress.

## Run server

```bash
lein deps
lein run
```

## Querying
#### returns all data (select *) - for now!

```bash
  localhost:8080/:conn/:db/:table.:format
  localhost:8080/:conn/:db/:table.:format?w[:col]=:val&w[:b]=:val
```

Example

```bash
localhost:8080/mysql/some_database/some_table.json
localhost:8080/oracle/some_database/some_table.xml
localhost:8080/mysql/some_database/some_table.json?w[a]=1&w[b]=2
# select * from some_database.some_table where a = 1 and b = 2
```