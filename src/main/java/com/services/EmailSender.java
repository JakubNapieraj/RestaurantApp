package com.services;

public interface EmailSender {
    void sendEmail(String to, String subject, String content);
}
