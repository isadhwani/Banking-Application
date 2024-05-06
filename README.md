

This application is a simple banking API that allows users to debit and credit from their accounts. Users are created by sending either request with a specified user ID. 

I've built this API spec using SpringBoot framework and Maven. The application begins in `BankingAplication.java` and it will scan for the `TransactionController`. The controller creates the three desired endpoints and delegates their functionality to the `TransactionService.java` file.    

The `TransactionService` has `EventProcessor` and `UserRepository` objects. The `UserRepository` object is an in-memory database containing all users and necessary methods. The `EventProcessor` object delegates transaction execution to the `DebitEvent` and `CreditEvent` objects respectively. These objects are responsible for updating the user's balance. Before processing, each event is added to the EventProcessor's list. 

All enums, request, response, and repository models are located in the `models` package.

All tests are in the `test` package. The `TestTransactionService` tests the functionality of the `TransactionService` class. The `TestEventLog` tests the functionality of the `EventProcessor` class. The `TestUserRepository` class tests the UserRepository and all necessary methods. If I had more time, I'd add tests for every method and class in the program to ensure 100% unit test coverage. 

While there is no endpoint specified for showing events logged in the EventProcessor, you can investigate it through the tests written in `TestEventLog.java`.  This test class creates multiple transactions and ensures that they are correctly added to the EventProcessor's log. 

The `EventProcessor` logs all `Credit` and `Debit` events, regardless of if they were successful. Debit and Credit events have their response code (approved or denied), weather or not it was a debit or a credit, the amount, and a copy of the target user. Storing this information allows for easy reversal of transactions by fetching the user and reversing the debit or credit event if it was approved. 

## Bootstrap instructions
Clone the repo and run it locally with the following maven commands. Ensure you are in the root of the directory before running.   

`mvn compile`

`mvn install`

`mvn exec:java -Dexec.mainClass=dev.codescreen.BankingApplication`

Alternativley, you could open the project in IntelliJ and have the IDE install maven dependencies for you. You can open the `BankingApplication.java`  file and click the run button. 

## Assumptions
I assume the program will be ran and tested locally. I have not implemented any CORs logic and expect requests to come from the same origin. There is also no security implemented in this service. The size of the database will be limited and would need to be depoloyed seperatley to be brought up to scale. 

I assume there are no  endpoints for this application other than the three specified in the spec
