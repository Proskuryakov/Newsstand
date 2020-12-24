package ru.vsu.cs.newsstand.core.db.domains;

import lombok.Getter;
import ru.vsu.cs.newsstand.core.domain.Book;
import ru.vsu.cs.newsstand.core.domain.Magazine;
import ru.vsu.cs.newsstand.core.domain.Newspaper;
import ru.vsu.cs.newsstand.core.domain.PrintedMatter;

import java.math.BigDecimal;
import java.util.Calendar;

public class DataBasePrintedMatter {

    @Getter
    private Long id;
    @Getter
    private String name;
    @Getter
    private BigDecimal price;
    @Getter
    private String author;
    @Getter
    private String publishingHouse;
    @Getter
    private Integer pageCount;
    @Getter
    private String publishingDate;
    @Getter
    private Integer number;


    public DataBasePrintedMatter(PrintedMatter printedMatter){
        id = printedMatter.getId();
        name = printedMatter.getName();
        price = printedMatter.getPrice();
        author = null;
        publishingHouse = null;
        pageCount = null;
        publishingDate = null;
        number = null;

        switch (printedMatter.getType()) {
            case BOOK:
                Book book = (Book) printedMatter;
                author = book.getAuthor();
                publishingHouse = book.getPublishingHouse();
                pageCount = book.getNumberOfPage();
                break;
            case MAGAZINE:
                Magazine magazine = (Magazine) printedMatter;
                publishingDate = magazine.getStringDate();
                number = magazine.getNumber();
                pageCount = magazine.getNumberOfPage();
                break;
            case NEWSPAPER:
                Newspaper newspaper = (Newspaper) printedMatter;
                publishingDate = newspaper.getStringDate();
                number = newspaper.getNumber();
                break;
        }
    }


}
