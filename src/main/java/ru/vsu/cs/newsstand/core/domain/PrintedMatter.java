package ru.vsu.cs.newsstand.core.domain;

import java.math.BigDecimal;

public abstract class PrintedMatter {

    protected Long id;
    protected String name;
    protected BigDecimal price;
    protected PrintedMatterType type;

    public PrintedMatter(String name, BigDecimal price, PrintedMatterType type) {
        this.id = null;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public abstract PrintedMatter copy();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public PrintedMatterType getType() {
        return type;
    }

    public void setType(PrintedMatterType type) {
        this.type = type;
    }
}
