package ru.vsu.cs.newsstand.core.domain;

import java.math.BigDecimal;
import java.util.Calendar;

public class Newspaper extends PrintedMatter{

    private Integer number;
    private Calendar date;

    public Newspaper(String name, BigDecimal price, Integer number, Calendar date) {
        super(name, price, PrintedMatterType.NEWSPAPER);
        this.number = number;
        this.date = date;
    }

    @Override
    public PrintedMatter copy() {
        return new Newspaper(name, price, number, date);
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
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
