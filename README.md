# nojoir

An attempt at a RESTful data access layer using noir

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
```

Example

```bash
localhost:8080/mysql/some_database/some_table.json
localhost:8080/oracle/some_database/some_table.xml
```