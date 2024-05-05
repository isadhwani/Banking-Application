package dev.codescreen;
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
import dev.codescreen.models.responses.AuthorizationResponse;
import dev.codescreen.models.responses.LoadResponse;
import dev.codescreen.models.responses.PingResponse;
import dev.codescreen.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController


public class TransactionController {


//    @Autowired
   // private TransactionRepository transactionRepository;

    @Autowired private TransactionService ts;

    @GetMapping("/ping")
    @CrossOrigin(origins = "http://localhost:8080")
    public PingResponse ping() {
        System.out.println("pinging...");
        PingResponse p =  new PingResponse();
        System.out.println(p.serverTime);
        return p;
    }

    @GetMapping("/")
    @CrossOrigin(origins = "http://localhost:8080")
    public String welcome() {
        return "Yello bud";
    }


    @PutMapping("/authorization")
    public AuthorizationResponse authorizeTransaction(@RequestBody AuthorizationRequest authorizationRequest) {
        return ts.authorizeTransaction(authorizationRequest);

    }

    @PutMapping("/load")
    public LoadResponse loadTransaction(@RequestBody LoadRequest loadRequest) {
        System.out.println("Loading transaction");
        return ts.loadTransaction(loadRequest);
    }
}