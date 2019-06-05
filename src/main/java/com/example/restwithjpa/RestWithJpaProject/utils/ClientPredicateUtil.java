package com.example.restwithjpa.RestWithJpaProject.utils;

import java.util.Optional;
import java.util.function.Predicate;

public class ClientPredicateUtil {

    /**
     * Check if Optional is empty
     * @return
     */
    public static Predicate<Optional> isPresent(){
        return optional -> !optional.isPresent();
    }
}
