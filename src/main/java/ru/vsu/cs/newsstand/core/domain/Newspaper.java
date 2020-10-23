package ru.vsu.cs.newsstand.core.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.util.Calendar;

public class Newspaper extends PrintedMatter {

    @Getter
    private Integer number;
    @Getter
    private Calendar date;

    public Newspaper(Long id, String name, BigDecimal price, Integer number, Calendar date) {
        super(id, name, price, PrintedMatterType.NEWSPAPER);
        this.number = number;
        this.date = date;
    }

    public Newspaper(String name, BigDecimal price, Integer number, Calendar date) {
        this(null, name, price, number, date);
    }

    @Override
    public PrintedMatter copy() {
        return new Newspaper(name, price, number, date);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Newspaper{ ");

        sb.append("ID = ").append(id).append(", ");
        sb.append("Name = ").append(name).append(", ");
        sb.append("Price = ").append(price).append(", ");
        sb.append("№").append(number).append(", ");
        sb.append("Date = ");
        sb.append(date.get(Calendar.DATE)).append(".");
        sb.append(date.get(Calendar.MONTH)).append(".");
        sb.append(date.get(Calendar.YEAR));

        sb.append(" }");
        return sb.toString();
    }


}
