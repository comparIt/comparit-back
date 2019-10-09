package com.pepit.exceptionHandler;

import com.pepit.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class ExceptionCatcher {

    @ExceptionHandler({BusinessException.class})
    public final ResponseEntity<Object> businessExceptionException(BusinessException ex){
        return ResponseEntity.status(500).body("Something went wrong...");
    }

    @ExceptionHandler({DataProvidedException.class})
    public final ResponseEntity<Object> dataProvidedExceptionException(DataProvidedException ex){
        return ResponseEntity.status(415).body("non-compliant data");
    }

    @ExceptionHandler({NoResultException.class})
    public final ResponseEntity<Object> noResultException(NoResultException ex){
        return ResponseEntity.status(404).body("No resources have been found");
    }

    @ExceptionHandler({UnauthorizedException.class})
    public final ResponseEntity<Object> unauthorizedException(UnauthorizedException ex) {
        return ResponseEntity.status(401).body("Unauthorized");
    }

    @ExceptionHandler({ReferentielRequestException.class})
    public final ResponseEntity<Object> referentielRequestException(ReferentielRequestException ex){
        return ResponseEntity.status(404).body("No result");
    }

    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> allException(Exception ex){
        return ResponseEntity.status(500).body(ex.getMessage());
    }
}
