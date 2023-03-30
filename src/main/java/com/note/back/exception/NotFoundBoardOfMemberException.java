package com.note.back.exception;

public class NotFoundBoardOfMemberException extends RuntimeException{

    public NotFoundBoardOfMemberException(String message) {
        super(message);
    }
}
