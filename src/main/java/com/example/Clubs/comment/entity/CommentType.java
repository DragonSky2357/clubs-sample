package com.example.Clubs.comment.entity;

import javax.management.Notification;

public enum CommentType {
    POST("POST"),
    CLUB("CLUB"),
    REPLY("REPLY"),
    QUESTION("QUESTION"),
    Notification("Notification");

    private final String type;

    CommentType(String type) {
        this.type = type;
    }
}