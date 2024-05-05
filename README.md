**Important: Don't forget to update the [Candidate README](#candidate-readme) section**

Real-time Transaction Challenge
===============================
## Overview
Welcome to Current's take-home technical assessment for backend engineers! We appreciate you taking the time to complete this, and we're excited to see what you come up with.

You are tasked with building a simple bank ledger system that utilizes the [event sourcing](https://martinfowler.com/eaaDev/EventSourcing.html) pattern to maintain a transaction history. The system should allow users to perform basic banking operations such as depositing funds, withdrawing funds, and checking balances. The ledger should maintain a complete and immutable record of all transactions, enabling auditability and reconstruction of account balances at any point in time.

## Details
The [included service.yml](service.yml) is the OpenAPI 3.0 schema to a service we would like you to create and host.

The service accepts two types of transactions:
1) Loads: Add money to a user (credit)

2) Authorizations: Conditionally remove money from a user (debit)

Every load or authorization PUT should return the updated balance following the transaction. Authorization declines should be saved, even if they do not impact balance calculation.


Implement the event sourcing pattern to record all banking transactions as immutable events. Each event should capture relevant information such as transaction type, amount, timestamp, and account identifier.
Define the structure of events and ensure they can be easily serialized and persisted to a data store of your choice. We do not expect you to use a persistent store (you can you in-memory object), but you can if you want. We should be able to bootstrap your project locally to test.

## Expectations
We are looking for attention in the following areas:
1) Do you accept all requests supported by the schema, in the format described?

2) Do your responses conform to the prescribed schema?

3) Does the authorizations endpoint work as documented in the schema?

4) Do you have unit and integrations test on the functionality?

Here’s a breakdown of the key criteria we’ll be considering when grading your submission:

**Adherence to Design Patterns:** We’ll evaluate whether your implementation follows established design patterns such as following the event sourcing model.

**Correctness**: We’ll assess whether your implementation effectively implements the desired pattern and meets the specified requirements.

**Testing:** We’ll assess the comprehensiveness and effectiveness of your test suite, including unit tests, integration tests, and possibly end-to-end tests. Your tests should cover critical functionalities, edge cases, and potential failure scenarios to ensure the stability of the system.

**Documentation and Clarity:** We’ll assess the clarity of your documentation, including comments within the code, README files, architectural diagrams, and explanations of design decisions. Your documentation should provide sufficient context for reviewers to understand the problem, solution, and implementation details.

# Candidate README

This application is a simple banking API that allows users to debit and credit from their account. Users are created by sending either request with a specified user ID. 

I've built this API spec using SpringBoot framework and Maven. The application begins in `BankingAplication.java` and it will scan for the `TransactionController`. The controller creates the three desired endpoints and delegates their functionality to the `TransactionService.java` file.  
The `TransactionService` has `EventProcessor` and `UserRepository` objects. The `UserRepository` object is an in memory database containing all users and necessary methods. The `EventProcessor` object delegates transaction execution to `DebitEvent` and `CreditEvent` objects respectively. These objects are responsible for updating the user's balance. Before processing, each event is added to the EventProcessor's list. 

All enums, request, response, and repository models are located in the `models` package.

All tests are in the `test` package. The `TestTransactionService` tests the functionality of the `TransactionService` class. The `TestEventLog` tests the functionality of the `EventProcessor` class.

While there is no endpoint specified for logging events in the EventProcessor, you can investigate it through the tests written in `TestEventLog.java`.
## Bootstrap instructions
Clone the repo and run it locally with the following maven commands. Ensure you are in the root of the directory before running.   

`mvn compile`

`mvn install`

`mvn exec:java -Dexec.mainClass=dev.codescreen.BankingApplication`
## Design considerations
This service functions as a simple banking application, allowing users to debit and credit funds from their account. All funds are handled in USD and users cannot have balances below 0.  

I found a couple issues in the described assignment while I was implementing. 

First, the API spec gives no ability to create a user. I've designed to service to create a user if there is not one of the specified ID. 

Second, each load and authorization request takes in a message ID. While I understand the need to make messages unique, the end user should not have to come up with their own unique message ID. I choose not to restrict the user from reusing message IDs, and would generate a new message ID for each response. While the example request shows a response message returning the same message ID as the request, I believe this is a mistake. If this was the desired functionality, I feel this should be renamed. Because of this issue, I did not implement an logging service to keep track of every user request and response. While I feel this would be useful for assesing the quality and speed of responses, not having messages be unique would create a problem and make this impractical. 

Third, the API spec does not specify how to handle a user trying to debit more money than they have. I have chosen to return a 400 error in this case.

Fourth, the API spec does not specify how to handle a user trying to credit or debit a negative amount of money. I have chosen to create a custom error handling service that gives a 400 error code. 
## Assumptions
I have assumed the program will be ran and tested locally. I have not implemented any CORs logic and expect requests to come from the same origin. There is also no security implemented in this service. 

## Bonus: Deployment considerations
*Replace this: If I were to deploy this, I would host it in this way with these technologies.*

## License

At CodeScreen, we strongly value the integrity and privacy of our assessments. As a result, this repository is under exclusive copyright, which means you **do not** have permission to share your solution to this test publicly (i.e., inside a public GitHub/GitLab repo, on Reddit, etc.). <br>

## Submitting your solution

Please push your changes to the `main branch` of this repository. You can push one or more commits. <br>

Once you are finished with the task, please click the `Submit Solution` link on <a href="https://app.codescreen.com/candidate/dc3b345c-67fd-49a9-a9cf-f0d821b0a06e" target="_blank">this screen</a>.