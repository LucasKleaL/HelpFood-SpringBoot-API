package com.helpfood.util.excecao;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class FormatarExcecao {

    @ExceptionHandler({MessageException.class})
    public ResponseEntity<Object> sqlError(MessageException ex) {
        String retorno = "{" +
                "\"codigo\":\""+ex.getCodigo()+"\"," +
                "\"mensagem\":\""+ex.getMensagem()+"\"" +
                "}";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<Object>(retorno, headers, HttpStatus.BAD_REQUEST);
    }
}



