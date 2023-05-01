package br.com.projeto.api_meio_ambiente.models;

import org.springframework.stereotype.Component;

@Component
public class Messenger {
    
    private String messenger;

    public String getMessenger() {
        return messenger;
    }

    public void setMessenger(String messenger) {
        this.messenger = messenger;
    }

}
