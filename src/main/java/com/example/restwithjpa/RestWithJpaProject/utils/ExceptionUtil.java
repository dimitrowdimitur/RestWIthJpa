package com.example.restwithjpa.RestWithJpaProject.utils;
import java.util.Optional;

import static com.example.restwithjpa.RestWithJpaProject.utils.ClientPredicateUtil.isPresent;

public class ExceptionUtil {

    /**
     * @param optional
     * @param e
     * Checks if optional is empty and throws passed exception
     */
    public static void allowExceptionThrowing(Optional optional, RuntimeException e){
        if (isPresent().test(optional)){
            throw e;
        }

    }
}
