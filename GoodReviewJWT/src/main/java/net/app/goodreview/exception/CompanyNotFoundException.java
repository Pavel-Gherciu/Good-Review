package net.app.goodreview.exception;

public class CompanyNotFoundException extends NotFoundException{
    public CompanyNotFoundException(String message) {
        super(message);
    }
}