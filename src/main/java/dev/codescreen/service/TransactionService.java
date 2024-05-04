package dev.codescreen.service;

import dev.codescreen.models.Transaction;
import dev.codescreen.models.User;
import dev.codescreen.models.enums.ResponseCode;
import dev.codescreen.models.repositories.UserRepository;
import dev.codescreen.models.requests.AuthorizationRequest;
import dev.codescreen.models.responses.AuthorizationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TransactionService {
    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private TransactionRepository transactionRepository;


    public AuthorizationResponse authorizeTransaction(AuthorizationRequest req) {
        int creditAmount;
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

        Transaction transaction = new Transaction(req.getMessageId(), req.getUserId(), req.getTransactionAmount());
//        transactionRepository.save(transaction);

        if (user.canCredit(creditAmount)) {
            user.credit(creditAmount);
            return new AuthorizationResponse(req.getUserId(), req.getMessageId(), ResponseCode.APPOROVED, req.getTransactionAmount());
        }
        return new AuthorizationResponse(req.getUserId(), req.getMessageId(), ResponseCode.DECLINDED, req.getTransactionAmount());
    }

//
//    public LoadResponse loadTransaction(LoadRequest loadRequest) {
//        int debitAmount;
//        try {
//            debitAmount = Integer.parseInt(loadRequest.getTransactionAmount().getAmount());
//        } catch (NumberFormatException e) {
//            // return no such user error
//            throw e;
//        }
//
//        Optional<User> optionalUser = userRepository.findById(loadRequest.getUserId());
//        User user = optionalUser.isPresent() ? optionalUser.get() : new User(loadRequest.getUserId(), 0);
//
//        user.debit(debitAmount);
//        TransactionAmount amount = new TransactionAmount(String.valueOf(user.getBalance()), "USD", DebitOrCredit.DEBIT);
//        return new LoadResponse(loadRequest.getUserId(), loadRequest.getMessageId(), amount);
//    }
}
