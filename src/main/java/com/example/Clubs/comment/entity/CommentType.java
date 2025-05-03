package com.example.Clubs.comment.entity;

public enum CommentType {
    POST("POST"),
    CLUB("CLUB"),
    REPLY("REPLY"),
    QUESTION("QUESTION");

    private final String type;

    CommentType(String type) {
        this.type = type;
    }
}
