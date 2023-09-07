package net.app.goodreview.exception;

public class ReviewNotFoundException extends NotFoundException{
    public ReviewNotFoundException(String message) {
        super(message);
    }
}
