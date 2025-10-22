package com.example.demo.core.domain.services;

import java.util.Date;
import java.util.regex.Pattern;

import com.example.demo.core.domain.models.User;

public class ValidateUserService {

    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");

    public static void validate(User user) {
        if (user == null) {
            throw new IllegalArgumentException("User must not be null");
        }

        // name
        if (isBlank(user.getName())) {
            throw new IllegalArgumentException("Name is required");
        }

        // email
        if (isBlank(user.getEmail())) {
            throw new IllegalArgumentException("Email is required");
        }
        if (!EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new IllegalArgumentException("Email format is invalid");
        }

        // password
        if (isBlank(user.getPassword())) {
            throw new IllegalArgumentException("Password is required");
        }
        if (user.getPassword().length() < 8) {
            throw new IllegalArgumentException("Password must be at least 8 characters");
        }



    }
    private void validateFull(User user){
        Date dob = user.getDateOfBirth();
        if (dob == null) {
            throw new IllegalArgumentException("Date of birth is required");
        }
        if (dob.after(new Date())) {
            throw new IllegalArgumentException("Date of birth cannot be in the future");
        }

        // national id (Uruguayan CI)
        if (isBlank(user.getNationalId())) {
            throw new IllegalArgumentException("National ID (CI) is required");
        }
        if (!ValidateUruguayanIdService.validateUruguayanId(user.getNationalId())) {
            throw new IllegalArgumentException("National ID (CI) is invalid");
        }

        // phone number (basic sanity)
        if (isBlank(user.getPhoneNumber())) {
            throw new IllegalArgumentException("Phone number is required");
        }
        if (!user.getPhoneNumber().matches("^[+]?\n?[- 0-9()]{6,20}$")) {
            throw new IllegalArgumentException("Phone number format is invalid");
        }
    }
    private static boolean isBlank(String s) {
        return s == null || s.trim().isEmpty();
    }
}

