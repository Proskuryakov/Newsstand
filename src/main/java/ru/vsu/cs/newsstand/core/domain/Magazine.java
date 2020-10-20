package ru.vsu.cs.newsstand.core.domain;

import java.math.BigDecimal;
import java.util.Calendar;

public class Magazine extends PrintedMatter{

    private Integer number;
    private Calendar date;
    private Integer numberOfPage;

    public Magazine(String name, BigDecimal price, Integer number, Calendar date, Integer numberOfPage) {
        super(name, price, PrintedMatterType.MAGAZINE);
        this.number = number;
        this.date = date;
        this.numberOfPage = numberOfPage;
    }

    @Override
    public PrintedMatter copy() {
        return new Magazine(name, price, number, date, numberOfPage);
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

    public Integer getNumberOfPage() {
        return numberOfPage;
    }

    public void setNumberOfPage(Integer numberOfPage) {
        this.numberOfPage = numberOfPage;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Magazine{ ");

        sb.append("ID = ").append(id).append(", ");
        sb.append("Name = ").append(name).append(", ");
        sb.append("Price = ").append(price).append(", ");
        sb.append("№").append(number).append(", ");
        sb.append("Date = ");
        sb.append(date.get(Calendar.DATE)).append(".");
        sb.append(date.get(Calendar.MONTH)).append(".");
        sb.append(date.get(Calendar.YEAR)).append(", ");;
        sb.append("Number of page = ").append(numberOfPage);

        sb.append(" }");
        return sb.toString();
    }

}
