package dev.codescreen.controller;
//
//import dev.models.Exceptions.UserNotFoundException;
//import dev.models.Requests.AuthorizationRequest;
//import dev.models.Responses.AuthorizationResponse;
//import dev.models.Responses.PingResponse;
//import dev.models.Transaction;
//import dev.models.User;
//import dev.models.enums.ResponseCode;
////import dev.repositories.TransactionRepository;
////import dev.repositories.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;

import dev.codescreen.models.requests.AuthorizationRequest;
import dev.codescreen.models.requests.LoadRequest;
import dev.codescreen.models.responses.AAuthorizationResponse;
import dev.codescreen.models.responses.ALoadResponse;
import dev.codescreen.models.responses.PingResponse;
import dev.codescreen.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController


public class TransactionController {


//    @Autowired
   // private TransactionRepository transactionRepository;

    @Autowired private TransactionService ts;

    @GetMapping("/ping")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<PingResponse> ping() {
        return new
                ResponseEntity<>(new PingResponse(), HttpStatus.OK);
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:8080")
    public String welcome() {
        ts.printUsers();
        return "Welcome to the transaction service";
    }


    @PutMapping("/authorization")
    public ResponseEntity<AAuthorizationResponse> authorizeTransaction(@RequestBody AuthorizationRequest authorizationRequest)  {
        return ts.authorizeTransaction(authorizationRequest);

    }

    @PutMapping("/load")
    public ResponseEntity<ALoadResponse> loadTransaction(@RequestBody LoadRequest loadRequest)  {
        return ts.loadTransaction(loadRequest);
    }
}