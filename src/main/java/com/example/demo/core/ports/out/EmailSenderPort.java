package com.example.demo.core.ports.out;

public interface EmailSenderPort {
    void sendEmail(String toEmail, String subject, String textBody, String htmlBody);
}

