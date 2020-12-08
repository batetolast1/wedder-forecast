package io.github.batetolast1.wedderforecast.service;

public interface ValidationService {

    boolean isUniqueEmail(String email);

    boolean isUniqueUsername(String username);
}
