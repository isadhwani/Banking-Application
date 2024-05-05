package dev.codescreen.models.responses;

public class LoadResponseError extends ALoadResponse {
     private String message;

     private String code;

     public LoadResponseError() {
     }

     public LoadResponseError(final String message) {
          this.message = message;
     }

        public LoadResponseError(final String message, final String code) {
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
