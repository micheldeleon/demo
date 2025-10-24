package com.example.demo.core.ports.out;

public interface HasherPort {
    String hash(String raw);
    boolean matches(String raw, String hashed);
}
