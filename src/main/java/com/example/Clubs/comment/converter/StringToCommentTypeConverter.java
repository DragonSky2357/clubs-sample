package com.example.Clubs.comment.converter;

import com.example.Clubs.comment.entity.CommentType;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToCommentTypeConverter implements Converter<String, CommentType> {
    @Override
    public CommentType convert(String source) {
        return CommentType.valueOf(source.toUpperCase());
    }
}
