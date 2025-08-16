package com.example.Clubs.comment.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

public class CommentId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;
    private CommentType type;

    // 기본 생성자 필수
    public CommentId() {}

    public CommentId(Long id, CommentType type) {
        if (id == null) {
            throw new IllegalArgumentException("Comment ID cannot be null");
        }
        if (type == null) {
            throw new IllegalArgumentException("Comment type cannot be null");
        }
        this.id = id;
        this.type = type;
    }

    public Long getId() {
        return id;
    }
    public CommentType getType() {
        return type;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setType(CommentType type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj){
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        CommentId that = (CommentId) obj;
        return Objects.equals(this.id, that.id) &&
                Objects.equals(this.type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type);
    }

    @Override
    public String toString() {
        return String.format("CommentId{id=%d, type=%s}", id, type);
    }

    // 현재 CommentId가 유효한지 검증
    public boolean isValid() {
        return id != null && type != null;
    }
}

