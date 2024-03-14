package com.jakan.uirfood.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class FonctionNotFoundException extends RuntimeException{
    private String fonctionName;

    public FonctionNotFoundException(String fonctionName){
        super(String.format("User not found with fonction : '%s' ", fonctionName));
        this.fonctionName=fonctionName;
    }
}
