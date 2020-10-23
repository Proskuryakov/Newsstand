package ru.vsu.cs.newsstand.core.domain;

import lombok.Getter;

import java.math.BigDecimal;

public class Book extends PrintedMatter {

    @Getter
    private String author;
    @Getter
    private String publishingHouse;
    @Getter
    private Integer numberOfPage;

    public Book(Long id, String name, BigDecimal price, String author, String publishingHouse, Integer numberOfPage) {
        super(id, name, price, PrintedMatterType.BOOK);
        this.author = author;
        this.publishingHouse = publishingHouse;
        this.numberOfPage = numberOfPage;
    }

    public Book(String name, BigDecimal price, String author, String publishingHouse, Integer numberOfPage) {
        this(null, name, price, author, publishingHouse, numberOfPage);
    }

    @Override
    public PrintedMatter copy() {
        return new Book(name, price, author, publishingHouse, numberOfPage);
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
