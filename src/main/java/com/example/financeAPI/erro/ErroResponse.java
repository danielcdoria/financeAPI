package com.example.financeAPI.erro;

public class ErroResponse {
    private String message;

    public ErroResponse(String message){
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
