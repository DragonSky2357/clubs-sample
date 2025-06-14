package com.example.Clubs.comment.entity;

import java.io.Serializable;
import java.util.Objects;

public class CommentId implements Serializable {
    private long id;
    private CommentType type;

    // 기본 생성자 필수
    public CommentId() {}

    public CommentId(long id, CommentType type) {
        this.id = id;
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CommentId)) return false;
        CommentId c = (CommentId) o;
        return id == c.id && type == c.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }
}
