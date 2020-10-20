package ru.vsu.cs.newsstand.core.domain;

import java.math.BigDecimal;
import java.util.Calendar;

public class Event {

    private Long id;
    private Action action;
    private Integer quantity;
    private PrintedMatter printedMatter;
    private Calendar dataTime;
    private BigDecimal amount;

    public Event(Action action, Integer quantity, PrintedMatter printedMatter, Calendar dataTime, BigDecimal amount) {
        this.id = null;
        this.action = action;
        this.quantity = quantity;
        this.printedMatter = printedMatter;
        this.dataTime = dataTime;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Action getAction() {
        return action;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public PrintedMatter getPrintedMatter() {
        return printedMatter;
    }

    public Calendar getDataTime() {
        return dataTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("Event{");

        sb.append("ID = ").append(id);
        sb.append(", action: ").append(action);
        sb.append(", quantity: ").append(quantity);
        sb.append(", printedMatter: ").append(printedMatter);
        sb.append(", data and time: ").append(dataTime.getTime());
        sb.append(", amount: ").append(amount);
        sb.append(" }");
        return sb.toString();

    }
}
