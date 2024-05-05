package dev.codescreen.models.responses;

/**
 * An abstract class that represents a response to an authorization request.
 */
public abstract class AAuthorizationResponse {

    private String message;

    public AAuthorizationResponse() {
    }

    public AAuthorizationResponse(final String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

}
