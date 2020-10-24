package ru.vsu.cs.newsstand.core.domain;

import lombok.Getter;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Magazine extends PrintedMatter {

    @Getter
    private Integer number;
    @Getter
    private Calendar date;
    @Getter
    private Integer numberOfPage;

    public Magazine(Long id, String name, BigDecimal price, Integer number, Calendar date, Integer numberOfPage) {
        super(id, name, price, PrintedMatterType.MAGAZINE);
        this.number = number;
        this.date = date;
        this.numberOfPage = numberOfPage;
    }

    public Magazine(String name, BigDecimal price, Integer number, Calendar date, Integer numberOfPage) {
        this(null, name, price, number, date, numberOfPage);
    }

    public String getStringDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        return dateFormat.format(date.getTime());
    }

    @Override
    public PrintedMatter copy() {
        return new Magazine(name, price, number, date, numberOfPage);
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
        sb.append(getStringDate());
        sb.append("Number of page = ").append(numberOfPage);

        sb.append(" }");
        return sb.toString();
    }

}
