# TP: Tester en Java

## TP

### Jouer avec l'application
Pour lancer l'application, tapez la commande suivante:
```bash
mvn clean spring-boot:run
```

Ceci démarre une application Spring Boot accessible depuis l'URL [http://localhost:8080](http://localhost:8080).
La [documentation Swagger](https://swagger.io/tools/swagger-ui/) est accessible depuis [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

Pour vous, simplifier la vie, vous pouvez exécuter le script [insert_values.sh](insert_values.sh) pour créer des comptes et transactions.
```bash
sh ./insert_values.sh
```

### Tester avec *mockito*
Voir les classes [org.foo.core.service.TransactionServiceImplTest](src/test/java/org/foo/core/service/TransactionServiceImplTest.java) et [org.foo.core.service.authorization.AuthorizationServiceImpl](src/main/java/org/foo/core/service/authorization/AuthorizationServiceImpl.java)

Tâches:
  * Ajouter des tests pour *AccountServiceImpl*
  * Compléter les tests du service *TransactionServiceImpl*
  * Ajouter des tests pour *AuthorizationServiceImpl*
  * Ajouter des tests pour les stratégies d'authorisation

Analyses:
  * Que se passe-t-il si une transaction dispose d'un montant négatif, null ou zéro?
      * Tâche: corriger ce bug
  * Que se passe-t-il si on effectue une transaction pour un compte créditeur inexistant ?
  * Que se passe-t-il si aucune stratégie d'authorisation est applicable à une transaction ?
    * Faudrait-il prendre en compte ce cas de figure ? Si oui, comment ?


### Tester avec *assertj*
Voir la classe [org.foo.core.service.TransactionDistributionServiceImplTest](src/test/java/org/foo/core/service/TransactionDistributionServiceImplTest.java)

Tâches:
  * Compléter les tests
  * Ajouter des tests pour les cas au limite: liste des transactions vide ou null


### Tester les requêtes en base de données
Voir la classe [org.foo.core.repository.TransactionRepositoryTest](src/test/java/org/foo/core/repository/TransactionRepositoryTest.class)

Tâches:
  * Tester le cas où un compte ne dispose d'aucune transaction sur la période sélectionnée lors d'un appel à la méthode *getSpentAmountFromDate*

### Tester les controllers en intégration
Voir la classe [org.foo.web.controller.TransactionControllerTest](src/test/java/org/foo/web/controller/TransactionControllerTest.java)

### Tester la charge
Voir la classe [org.foo.bank.BankSimulation](src/scala/test/org/foo/bank/BankSimulation.scala)
```bash
mvn clean integration-test -Pgatling
```


