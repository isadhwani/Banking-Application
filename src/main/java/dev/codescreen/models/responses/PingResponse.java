package dev.codescreen.models.responses;

public class PingResponse {
    public String serverTime;

    public PingResponse() {
        this.serverTime = String.valueOf(System.nanoTime());
    }


}
