package TestService;

import dev.codescreen.controller.TransactionController;
import dev.codescreen.models.responses.PingResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class TestTransactionController {

    @Test
    public void testPing() {
        TransactionController tc = new TransactionController();

        ResponseEntity<PingResponse> pr = tc.ping();
        assert (pr.getBody().serverTime != null);
        assert (pr.getStatusCode().equals(HttpStatus.OK));
    }

    }
