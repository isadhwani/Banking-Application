package dev.codescreen.service;

import dev.codescreen.eventlog.CreditEvent;
import dev.codescreen.eventlog.DebitEvent;
import dev.codescreen.eventlog.Event;
import dev.codescreen.eventlog.EventProcessor;
import dev.codescreen.models.enums.DebitOrCredit;
import dev.codescreen.models.enums.ResponseCode;
import dev.codescreen.models.repositories.User;
import dev.codescreen.models.repositories.UserRepository;
import dev.codescreen.models.requests.AuthorizationRequest;
import dev.codescreen.models.requests.LoadRequest;
import dev.codescreen.models.responses.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Service class that handles transaction requests, called from the controller
 *      - UserRepository userRepository: Repository for Users using the banking system
 *      - EventProcessor ep: Event processor to handle and log events
 *
 *      Note: If a request here contains a user ID that does not exist in the repository, a new user is created with a balance of 0
 */
@Service
public class TransactionService {
    @Autowired

    private UserRepository userRepository;

    private EventProcessor ep;

    /**
     * Constructor for Autowiring when running the API
     */
    public TransactionService() {
        this.ep = new EventProcessor();
    }

    /**
     * Constructor for TransactionService testing
     * @param userRepository: Repository for Users using the banking system
     *
     */
    public TransactionService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.ep = new EventProcessor();
    }

    /**
     * Constructor for TransactionService testing
     * @param userRepository: Repository for Users using the banking system
     * @param ep: Event processor to handle and log events
     */
    public TransactionService(UserRepository userRepository, EventProcessor ep) {
        this.userRepository = userRepository;
        this.ep = ep;
    }



    /**
     * Authorize a transaction for a user, called from controller
     * @param req: AuthorizationRequest object containing the user ID and transaction amount
     * @return ResponseEntity<AAuthorizationResponse>: Response object containing the user ID, response code, and balance
     * @return ResponseEntity<AuthorizationResponseError>: Response object containing an error message and status code
     */
    public ResponseEntity<AAuthorizationResponse> authorizeTransaction(AuthorizationRequest req) {
        float creditAmount = 0;
        try {
            creditAmount = Float.parseFloat(req.getTransactionAmount().getAmount());
            if (creditAmount < 0) {
                return new ResponseEntity(new AuthorizationResponseError("Credit amount cannot be negative", "400"), HttpStatus.BAD_REQUEST );
            }

        } catch (NumberFormatException e) {
            return new ResponseEntity(new AuthorizationResponseError("Credit amount not a number", "400"), HttpStatus.BAD_REQUEST );

        }


        Optional<User> optionalUser = userRepository.findById(req.getUserId());
        User user = optionalUser.isPresent() ? optionalUser.get() : new User(req.getUserId(), 0);


        CreditEvent creditEvent = new CreditEvent(user, creditAmount);
        ep.processEvent(creditEvent);
        Event processedEvent = ep.getLatestEvent();

        Amount balacne = new Amount(String.valueOf(user.getBalance()), "USD", DebitOrCredit.CREDIT);
        AuthorizationResponse ar = new AuthorizationResponse(req.getUserId(), processedEvent.getResponseCode(), balacne);
        return new ResponseEntity<>(ar, HttpStatus.OK);
    }


    /**
     * Load a transaction for a user, called from controller
     * @param loadRequest: LoadRequest object containing the user ID and transaction amount
     * @return ResponseEntity<ALoadResponse>: Response object containing the user ID and balance
     * @return ResponseEntity<LoadResponseError>: Response object containing an error message and status code
     */
    public ResponseEntity<ALoadResponse> loadTransaction(LoadRequest loadRequest) {
        float debitAmount;
        try {
            debitAmount = Float.parseFloat(loadRequest.getTransactionAmount().getAmount());
            if (debitAmount < 0) {
                return new ResponseEntity(new LoadResponseError("Debit amount cannot be negative", "400"), HttpStatus.BAD_REQUEST );
            }
        } catch (NumberFormatException e) {
            return new ResponseEntity(new AuthorizationResponseError("Debit amount not a number", "400"), HttpStatus.BAD_REQUEST );

        }

        Optional<User> optionalUser = userRepository.findById(loadRequest.getUserId());
        User user = optionalUser.orElseGet(() -> new User(loadRequest.getUserId(), 0));
        userRepository.save(user);

        DebitEvent debitEvent = new DebitEvent(user, debitAmount, ResponseCode.APPOROVED);
        ep.processEvent(debitEvent);

        Amount amount = new Amount(String.valueOf(user.getBalance()), "USD", DebitOrCredit.DEBIT);
        LoadResponse lr = new LoadResponse(loadRequest.getUserId(), amount);
        return new ResponseEntity<>(lr, HttpStatus.OK);
    }

    /**
     * Print the users in the repository, used for visual testing
     */
    public void printUsers() {
        System.out.println(userRepository);
    }
}
