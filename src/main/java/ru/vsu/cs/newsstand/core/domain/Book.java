package ru.vsu.cs.newsstand.core.domain;

import java.math.BigDecimal;

public class Book extends PrintedMatter{

    private String author;
    private String publishingHouse;
    private Integer numberOfPage;

    public Book(String name, BigDecimal price, String author, String publishingHouse, Integer numberOfPage) {
        super(name, price, PrintedMatterType.BOOK);
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.numberOfPage = numberOfPage;
    }

    @Override
    public PrintedMatter copy() {
        return new Book(name, price, author, publishingHouse, numberOfPage);
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Book{ ");

        sb.append("ID = ").append(id).append(", ");
        sb.append("Name = ").append(name).append(", ");
        sb.append("Price = ").append(price).append(", ");
        sb.append("Author = ").append(author).append(", ");
        sb.append("Publishing house = ").append(publishingHouse).append(", ");
        sb.append("Number of page = ").append(numberOfPage);

        sb.append(" }");
        return sb.toString();
    }

}
