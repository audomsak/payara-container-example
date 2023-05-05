# Application Example Jakarta EE

**Article:** [Payara + MySQL in containers](https://www.apuntesdejava.com/2022/02/payara-mysql-en-contenedores.html)

**Original Repo:** [sales-manager](https://bitbucket.org/apuntesdejava/sales-manager/src/develop/)

---

This is an example implementation of Jakarte EE 8 that includes (so far):

* JSF
* EJB
* JPA

## Running in containers (Payara + MySQL)

First you have to build and prepare the libraries and deployment files:

```sh
mvn clean install -P payara
```

Then, start containers with `docker-compose` :

```sh
 docker-compose -f containers/docker-compose.yml up
 ```
