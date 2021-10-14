package com.company.controller;

/**
 * Created by Iryna Furmaniuk on 07.07.2021.
 */
public interface RegexContainer {
    // Cyrillic surname
    String REGEX_SURNAME_UKR = "^[А-ЩЬЮЯҐІЇЄ][а-щьюяґіїє']{1,30}$";
    // Latin surname
    String REGEX_SURNAME_LAT = "^[A-Z][a-z]{1,30}$";
    // nickname
    String REGEX_NICKNAME = "^[A-Za-z0-9_-]{5,30}$";
    "^[A-ZА-ЩЬЮЯҐІЇЄ][a-zа-щьюяґіїє']{1,30}$"
}
