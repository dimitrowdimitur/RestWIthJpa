package com.example.restwithjpa.RestWithJpaProject.utils;
import com.example.restwithjpa.RestWithJpaProject.client.Client;

import java.util.Optional;

import static com.example.restwithjpa.RestWithJpaProject.utils.ClientPredicateUtil.isNotPresent;

public class ExceptionUtil {

    /**
     * @param optional
     * @param e
     * Checks if optional is empty and throws passed exception
     */
    public static void allowExceptionThrowing(Optional optional, RuntimeException e){
        if (isNotPresent().test(optional)){
            throw e;
        }
    }
}
