# API REST example with JAX-RS, Swagger and Swagger UI

This is the repository for the REST API with Jersey assignment.

 - The default name of the project is `jersey-filmaffinity`.
 - Swagger UI shows on the root path. Endpoint documentation is displayed there.
 - It has support for a volatile in memory database and a MySQL database.

## Usage

The application uses the following environment variables as shown in `docker/example.env` for the MySQL connection:

```
FILMAFFINITY_DB_URL=jdbc:mysql://mysql:3306/mglezsosa-filmaffinity
FILMAFFINITY_DB_USER=root
FILMAFFINITY_DB_PASS=
```

A `docker-compose.yml` file is provided for setting up in docker. You may run `sh run-docker.sh` to start the containers. Thus, the database initializes and populates executing `schema.sql` and `populate.sql`.

## License

MIT License: http://mit-license.org/ or see [the `LICENSE` file](https://github.com/mglezsosa/jersey-filmaffinity/blob/master/LICENSE.md).

Miguel González Sosa <sosa@uma.es>
