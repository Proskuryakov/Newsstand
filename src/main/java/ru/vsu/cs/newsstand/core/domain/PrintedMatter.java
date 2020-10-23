package ru.vsu.cs.newsstand.core.domain;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

public abstract class PrintedMatter {

    @Getter
    @Setter
    protected Long id;
    @Getter
    protected String name;
    @Getter
    protected BigDecimal price;
    @Getter
    protected PrintedMatterType type;

    public PrintedMatter(Long id, String name, BigDecimal price, PrintedMatterType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public abstract PrintedMatter copy();

}
