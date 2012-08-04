# nojoir

An attempt at a RESTful data access layer for clojure using noir 

## Run server

```bash
lein deps
lein run
```

## Querying

```bash
  localhost:8080/:conn/:db/:table.:format
```

Example

```bash
localhost:8080/mysql/some_database/some_table.json
localhost:8080/oracle/some_database/some_table.xml
```