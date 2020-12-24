package ru.vsu.cs.newsstand.core.ui;

import lombok.SneakyThrows;
import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

@Singleton
public class ConsoleInterface {

    @InjectByType
    private Controller controller;
    private Scanner scn;

    public void start() {
        println("Welcome to the newsstand");
        println("What can i do for you?");

        scn = new Scanner(System.in);

        loop();
    }

    private void loop() {
        while (true) {
            println("Select a command:");
            println("0 - Exit");
            println("1 - View product");
            println("2 - Add a product");
            println("3 - View event log");
            println("4 - Delete product");

            int command = readCommand();

            switch (command) {
                case 0:
                    exit();
                case 1:
                    viewProduct();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    viewEventLog();
                    break;
                case 4:
                    deleteProduct();
                    break;
                default:
                    println("Try again");

            }
        }
    }

    private void viewEventLog() {
        println(controller.getEventLog());
    }

    private void addProduct() {
        println("Select a command:");
        println("0 - Go back to the previous menu");
        println("1 - Add newspaper(s)");
        println("2 - Add magazine(s)");
        println("3 - Add book(s)");
        print("Waiting for the command : ");

        int command = readCommand();

        switch (command) {
            case 0:
                return;
            case 1:
                addNewspaper();
                break;
            case 2:
                addMagazine();
                break;
            case 3:
                addBook();
                break;
        }

    }

    private void addBook() {
        println("-_-_-_-_-_-_-_-_-_-_-_-");
        println("You want add book.");

        String name = readName();
        BigDecimal price = readPrice();
        String author = readAuthor();
        String publishingHouse = readPublishingHouse();
        int countOfPage = readCountOfPage();
        int count = readCount();

        String result = controller.addBook(name, price, author, publishingHouse, countOfPage, count);
        println("You add " + result);
    }

    private void addMagazine() {
        println("-_-_-_-_-_-_-_-_-_-_-_-");
        println("You want add magazine.");

        String name = readName();
        BigDecimal price = readPrice();
        Integer number = readNumber();
        int countOfPage = readCountOfPage();
        int count = readCount();
        Calendar calendar = readDate();

        String result = controller.addMagazine(name, price, number, calendar, countOfPage, count);
        println("You add " + result);
    }

    private void addNewspaper() {
        println("-_-_-_-_-_-_-_-_-_-_-_-");
        println("You want add newspaper.");

        String name = readName();
        BigDecimal price = readPrice();
        Integer number = readNumber();
        Calendar calendar = readDate();
        int count = readCount();

        String result = controller.addNewspaper(name, price, number, calendar, count);
        println("You add " + result);
    }

    private String readName() {
        print("Enter name: ");
        return scn.nextLine();
    }

    private BigDecimal readPrice() {
        print("Enter price: ");
        return new BigDecimal(scn.nextLine());
    }

    private int readNumber() {
        print("Enter number: ");
        return Integer.parseInt(scn.nextLine());
    }

    @SneakyThrows
    private Calendar readDate() {
        print("Enter data in format like 8.10.2018: ");
        String strDate = scn.nextLine();

        SimpleDateFormat formatter = new SimpleDateFormat("d.M.y");

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(formatter.parse(strDate));

        return calendar;
    }

    private String readAuthor() {
        print("Enter author: ");
        return scn.nextLine();
    }

    private String readPublishingHouse() {
        print("Enter publishing house: ");
        return scn.nextLine();
    }

    private int readCountOfPage() {
        print("Enter count of page: ");
        return Integer.parseInt(scn.nextLine());
    }

    private int readCount() {
        print("Enter count: ");
        return Integer.parseInt(scn.nextLine());
    }

    private void viewProduct() {
        println("Select a command:");
        println("0 - Go back to the previous menu");
        println("1 - View all newspapers");
        println("2 - View all magazines");
        println("3 - View all books");
        println("4 - View all printed matters");
        int command = readCommand();

        switch (command) {
            case 0:
                return;
            case 1:
                println(controller.getAllNewspaper());
                break;
            case 2:
                println(controller.getAllMagazines());
                break;
            case 3:
                println(controller.getAllBooks());
                break;
            case 4:
                println(controller.getAllPrintedMatters());
                return;
        }

        println("Want to buy something?");
        println("1 - YES");
        println("2 - NO");
        int newCommand = readCommand();

        switch (newCommand) {
            case 1:
                buySomething(command);
                break;
            case 2:
                break;
        }
    }

    private void buySomething(int command) {
        print("Enter the id of the product you want to buy : ");
        int id = Integer.parseInt(scn.nextLine());
        print("Enter the quantity of the product you want to buy : ");
        int count = Integer.parseInt(scn.nextLine());
        switch (command) {
            case 1:
                println(controller.buyNewspaper(id, count));
                break;
            case 2:
                println(controller.buyMagazine(id, count));
                break;
            case 3:
                println(controller.buyBook(id, count));
                break;
        }
    }

    private void exit() {
        println("End of work");
        System.exit(0);
    }

    private void deleteProduct() {
        println(controller.getAllPrintedMatters());
        print("Select the id of the product to be removed: ");
        Long id = Long.parseLong(scn.nextLine());
        println(controller.deletePrintedMatter(id));
    }


    private void println(String message) {
        System.out.println(message);
    }

    private void print(String message) {
        System.out.print(message);
    }

    private int readCommand() {
        print("Waiting for the command : ");
        return Integer.parseInt(scn.nextLine());
    }
}
