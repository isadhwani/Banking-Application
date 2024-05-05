package dev.codescreen.models.responses;

/**
 * An abstract class that represents a response to an authorization request.
 * Used to tell the user when they send an invalid request
 */
public class AuthorizationResponseError extends AAuthorizationResponse {
    private String message;

    private String code;

    public AuthorizationResponseError() {
    }

    public AuthorizationResponseError(final String message) {
        this.message = message;
    }

    public AuthorizationResponseError(final String message, final String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

}
