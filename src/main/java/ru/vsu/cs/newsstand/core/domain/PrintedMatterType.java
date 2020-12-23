package ru.vsu.cs.newsstand.core.domain;

import lombok.Getter;

public enum PrintedMatterType {

    BOOK ("Книга"),
    MAGAZINE("Журнал"),
    NEWSPAPER("Газета");

    @Getter
    private String value;

    PrintedMatterType(String value) {
        this.value = value;
    }


}
