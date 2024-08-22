package com.company;

public class Book
{
    private String title;
    private Category category;
    private boolean isBorrowed;

    public Book(String title, Category category) {
        this.title = title;
        this.category = category;
        this.isBorrowed = false;
    }

    public String getTitle() {
        return title;
    }

    public Category getCategory() {
        return category;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }
}
