package TestService;

import dev.codescreen.eventlog.DebitEvent;
import dev.codescreen.eventlog.Event;
import dev.codescreen.eventlog.EventProcessor;
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

public class TestEventLog {

    /**
     * Test that a new user is added to the repository with 100 and that transaction was added to the
     * event log.
     */
    @Test
    public void testLoadTransactionAdded() {
        UserRepository ur = new UserRepository();
        EventProcessor ep = new EventProcessor();
        TransactionService ts = new TransactionService(ur, ep);

        // Add a new user
        Amount amount = new Amount("100", "USD", DebitOrCredit.DEBIT);

        LoadRequest ar = new LoadRequest("1", "12", amount);
        ResponseEntity<ALoadResponse> response = ts.loadTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        LoadResponse castedResponse = (LoadResponse) response.getBody();
        assert (castedResponse.getBalance().getAmount().equals("100.0"));
        assert (castedResponse.getBalance().getCurrency().equals("USD"));


        // Check if the event was added
        assert (ep.getEvents().size() == 1);

        Event event = ep.getLatestEvent();

        DebitEvent de = (DebitEvent) event;

        assert (de.getUser().getBalance() == 100.0);
        assert (de.getResponseCode().equals(ResponseCode.APPOROVED));
    }

    /**
     * Test that an unsuccessful credit attempt was sent and that denied transaction was added to the
     * event log.
     */
    @Test
    public void testCreditTransactionAddedNoFunds() {
        UserRepository ur = new UserRepository();
        EventProcessor ep = new EventProcessor();
        TransactionService ts = new TransactionService(ur, ep);

        // Add a new user
        Amount amount = new Amount("100", "USD", DebitOrCredit.DEBIT);

        AuthorizationRequest ar = new AuthorizationRequest("1", "12", amount);
        ResponseEntity<AAuthorizationResponse> response = ts.authorizeTransaction(ar);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        AuthorizationResponse castedResponse = (AuthorizationResponse) response.getBody();
        assert (castedResponse.getBalance().getAmount().equals("0.0"));
        assert (castedResponse.getBalance().getCurrency().equals("USD"));

        assert(ep.getEvents().size() == 1);
        assert(ep.getLatestEvent().getResponseCode().equals(ResponseCode.DECLINDED));
    }

    /**
     * Test that a debit transaction followed by a credit transaction were added to the event log.
     */
    @Test
    public void testMultipleTransactionsLogged() {

        UserRepository ur = new UserRepository();
        EventProcessor ep = new EventProcessor();
        TransactionService ts = new TransactionService(ur, ep);

        // Add a new user
        Amount amount = new Amount("100", "USD", DebitOrCredit.DEBIT);

        LoadRequest lr = new LoadRequest("1", "12", amount);
        ResponseEntity<ALoadResponse> response = ts.loadTransaction(lr);

        assert (response.getStatusCode().equals(HttpStatus.OK));

        LoadResponse castedResponse = (LoadResponse) response.getBody();
        assert (castedResponse.getBalance().getAmount().equals("100.0"));
        assert (castedResponse.getBalance().getCurrency().equals("USD"));


        // Check if the event was added
        assert (ep.getEvents().size() == 1);

        Event event = ep.getLatestEvent();

        DebitEvent de = (DebitEvent) event;

        assert (de.getUser().getBalance() == 100.0);
        assert (de.getResponseCode().equals(ResponseCode.APPOROVED));


        Amount amount2 = new Amount("12", "USD", DebitOrCredit.DEBIT);

        AuthorizationRequest ar = new AuthorizationRequest("1", "123", amount2);
        ResponseEntity<AAuthorizationResponse> response2 = ts.authorizeTransaction(ar);

        assert (response2.getStatusCode().equals(HttpStatus.OK));

        AuthorizationResponse castedResponse2 = (AuthorizationResponse) response2.getBody();
        assert (castedResponse2.getBalance().getAmount().equals("88.0"));
        assert (castedResponse2.getBalance().getCurrency().equals("USD"));

        assert(ep.getEvents().size() == 2);
        assert(ep.getLatestEvent().getResponseCode().equals(ResponseCode.APPOROVED));
    }
}
