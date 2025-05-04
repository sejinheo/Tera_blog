package com.Tera.blog.domain.member;

public class AlreadyExistException extends RuntimeException {
    public AlreadyExistException(String message) {

      super(message);
    }
}
