package TestService;

import dev.codescreen.models.enums.DebitOrCredit;
import dev.codescreen.models.enums.ResponseCode;
import dev.codescreen.models.repositories.UserRepository;
import dev.codescreen.models.requests.AuthorizationRequest;
import dev.codescreen.models.requests.LoadRequest;
import dev.codescreen.models.responses.*;
import dev.codescreen.service.TransactionService;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TestTransactionService {

    @Test
    public void testNewUserNoBalance() {
        UserRepository ur = new UserRepository();
        TransactionService ts = new TransactionService(ur);


        Amount amount = new Amount("100", "USD", DebitOrCredit.DEBIT);
        AuthorizationRequest ar = new AuthorizationRequest("1", "12", amount);
        ResponseEntity<AAuthorizationResponse> response = ts.authorizeTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        AuthorizationResponse castedResponse = (AuthorizationResponse) response.getBody();
        System.out.println(castedResponse.getBalance().getAmount());
        assert (castedResponse.getBalance().getAmount().equals("0.0"));
        assert (castedResponse.getBalance().getCurrency().equals("USD"));
        assert (castedResponse.getResponseCode().equals(ResponseCode.DECLINDED));
    }

    @Test
    public void testLoadNewUser() {
        UserRepository ur = new UserRepository();
        TransactionService ts = new TransactionService(ur);


        Amount amount = new Amount("100", "USD", DebitOrCredit.DEBIT);

        LoadRequest ar = new LoadRequest("1", "12", amount);
        ResponseEntity<ALoadResponse> response = ts.loadTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        LoadResponse castedResponse = (LoadResponse) response.getBody();
        assert (castedResponse.getBalance().getAmount().equals("100.0"));
        assert (castedResponse.getBalance().getCurrency().equals("USD"));
    }

    @Test
    public void testCreditAndDebitNewUser() {
        UserRepository ur = new UserRepository();
        TransactionService ts = new TransactionService(ur);


        Amount amount = new Amount("100", "USD", DebitOrCredit.DEBIT);

        LoadRequest ar = new LoadRequest("1", "12", amount);
        ResponseEntity<ALoadResponse> response = ts.loadTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        LoadResponse castedResponse = (LoadResponse) response.getBody();
        assert (castedResponse.getBalance().getAmount().equals("100.0"));
        assert (castedResponse.getBalance().getCurrency().equals("USD"));


        Amount amount2 = new Amount("12", "USD", DebitOrCredit.DEBIT);
        AuthorizationRequest ar2 = new AuthorizationRequest("1", "12", amount2);
        ResponseEntity<AAuthorizationResponse> response2 = ts.authorizeTransaction(ar2);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        AuthorizationResponse castedResponse2 = (AuthorizationResponse) response2.getBody();
        assert (castedResponse2.getBalance().getAmount().equals("88.0"));
        assert (castedResponse2.getBalance().getCurrency().equals("USD"));
        assert (castedResponse2.getResponseCode().equals(ResponseCode.APPOROVED));

    }


    @Test
    public void testCreditGreaterThanDebit() {
        UserRepository ur = new UserRepository();
        TransactionService ts = new TransactionService(ur);


        Amount amount = new Amount("100", "USD", DebitOrCredit.DEBIT);

        LoadRequest ar = new LoadRequest("1", "12", amount);
        ResponseEntity<ALoadResponse> response = ts.loadTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        LoadResponse castedResponse = (LoadResponse) response.getBody();
        assert (castedResponse.getBalance().getAmount().equals("100.0"));
        assert (castedResponse.getBalance().getCurrency().equals("USD"));


        Amount amount2 = new Amount("500", "USD", DebitOrCredit.DEBIT);
        AuthorizationRequest ar2 = new AuthorizationRequest("1", "12", amount2);
        ResponseEntity<AAuthorizationResponse> response2 = ts.authorizeTransaction(ar2);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        AuthorizationResponse castedResponse2 = (AuthorizationResponse) response2.getBody();
        assert (castedResponse2.getBalance().getAmount().equals("100.0"));
        assert (castedResponse2.getBalance().getCurrency().equals("USD"));
        assert (castedResponse2.getResponseCode().equals(ResponseCode.DECLINDED));
    }

    @Test
    public void testDebitNegative() {
        UserRepository ur = new UserRepository();
        TransactionService ts = new TransactionService(ur);
        Amount amount = new Amount("-100", "USD", DebitOrCredit.DEBIT);

        LoadRequest ar = new LoadRequest("1", "12", amount);
        ResponseEntity<ALoadResponse> response = ts.loadTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        LoadResponseError lre = (LoadResponseError) response.getBody();
        assert (lre.getMessage().equals("Debit amount cannot be negative"));
    }

    @Test
    public void testCreditNegative() {
        UserRepository ur = new UserRepository();
        TransactionService ts = new TransactionService(ur);
        Amount amount = new Amount("-100", "USD", DebitOrCredit.CREDIT);

        AuthorizationRequest ar = new AuthorizationRequest("1", "12", amount);
        ResponseEntity<AAuthorizationResponse> response = ts.authorizeTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.BAD_REQUEST));
        AuthorizationResponseError are = (AuthorizationResponseError) response.getBody();
        assert (are.getMessage().equals("Credit amount cannot be negative"));
    }

}
