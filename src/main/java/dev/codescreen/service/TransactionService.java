package dev.codescreen.service;

import dev.codescreen.DebitEvent;
import dev.codescreen.EventProcessor;
import dev.codescreen.models.User;
import dev.codescreen.models.enums.ResponseCode;
import dev.codescreen.models.repositories.TransactionRepository;
import dev.codescreen.models.repositories.UserRepository;
import dev.codescreen.models.requests.AuthorizationRequest;
import dev.codescreen.models.requests.LoadRequest;
import dev.codescreen.models.responses.AuthorizationResponse;
import dev.codescreen.models.responses.LoadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    EventProcessor ep = new EventProcessor();


    public AuthorizationResponse authorizeTransaction(AuthorizationRequest req) {
        int creditAmount = 0;
        try {
            creditAmount = Integer.parseInt(req.getTransactionAmount().getAmount());

        } catch (NumberFormatException e) {
            // return no such user error
            throw e;
        }

        Optional<User> optionalUser = userRepository.findById(req.getUserId());
        User user = optionalUser.isPresent() ? optionalUser.get() : new User(req.getUserId(), 0);

        // throw new UserNotFoundException("User not found") ;//no such transaction error code...
        // Instead of throwing an error, I'll create a new user with this ID as the API spec does not have an add user endpoint

//        Transaction transaction = new Transaction(req.getMessageId(), req.getUserId(), req.getTransactionAmount());
//        transactionRepository.save(transaction);

        if (user.canCredit(creditAmount)) {
            user.credit(creditAmount);
            return new AuthorizationResponse(req.getUserId(), req.getMessageId(), ResponseCode.APPOROVED, req.getTransactionAmount());
        }
        return new AuthorizationResponse(req.getUserId(), req.getMessageId(), ResponseCode.DECLINDED, req.getTransactionAmount());
    }


    public LoadResponse loadTransaction(LoadRequest loadRequest) {
        float debitAmount;
        try {
            debitAmount = Float.parseFloat(loadRequest.getTransactionAmount().getAmount());
        } catch (NumberFormatException e) {
            // return no such user error
            throw e;
        }

        Optional<User> optionalUser = userRepository.findById(loadRequest.getUserId());
        User user = optionalUser.isPresent() ? optionalUser.get() : new User(loadRequest.getUserId(), 0);

        DebitEvent debitEvent = new DebitEvent(user, debitAmount);
        ep.processEvent(debitEvent);

       // Amount amount = new Amount(String.valueOf(user.getBalance()), "USD", DebitOrCredit.DEBIT);
        return new LoadResponse(loadRequest.getUserId(), loadRequest.getMessageId(), loadRequest.getTransactionAmount());
    }
}
