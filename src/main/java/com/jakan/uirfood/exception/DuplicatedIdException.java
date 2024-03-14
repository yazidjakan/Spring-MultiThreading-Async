package com.jakan.uirfood.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DuplicatedIdException extends RuntimeException {
    private String ressourceName;
    private String duplicatedIdName;
    private String duplicatedIdValue;

    public DuplicatedIdException(String ressourceName, String duplicatedIdName, String duplicatedIdValue){
        super(String.format("%s has been registred with the %s : '%s'", ressourceName, duplicatedIdName, duplicatedIdValue));
        this.ressourceName=ressourceName;
        this.duplicatedIdName=duplicatedIdName;
        this.duplicatedIdValue=duplicatedIdValue;
    }
}
