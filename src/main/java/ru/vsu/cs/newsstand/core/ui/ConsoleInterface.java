package ru.vsu.cs.newsstand.core.ui;

import ru.vsu.cs.newsstand.annotation.InjectByType;
import ru.vsu.cs.newsstand.annotation.Singleton;
import ru.vsu.cs.newsstand.core.services.bl.Controller;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

@Singleton
public class ConsoleInterface {

    @InjectByType
    private Controller controller;

    public void start(){
        println("Welcome to the newsstand");
        println("What can i do for you?");

        Scanner scn = new Scanner(System.in);

        loop(scn);
    }

    private void loop(Scanner scn){
        while (true){
            println("Select a command:");
            println("0 - Exit");
            println("1 - View product");
            println("2 - Add a product");
            println("3 - View event log");

            int command = readCommand(scn);

            switch (command){
                case 0:
                    exit();
                case 1:
                    viewProduct(scn);
                    break;
                case 2:
                    addProduct(scn);
                    break;
                case 3:
                    viewEventLog(scn);
                    break;
                default:
                    println("Try again");

            }
        }
    }

    private void viewEventLog(Scanner scn) {
        println(controller.getEventLog());
    }

    private void addProduct(Scanner scn) {
        println("Select a command:");
        println("0 - Go back to the previous menu");
        println("1 - Add newspaper(s)");
        println("2 - Add magazine(s)");
        println("3 - Add book(s)");
        print("Waiting for the command : ");

        int command = readCommand(scn);

        switch (command) {
            case 0:
                return;
            case 1:
                addNewspaper(scn);
                break;
            case 2:
                addMagazine(scn);
                break;
            case 3:
                addBook(scn);
                break;
        }

    }

    private void addBook(Scanner scn) {
        println("-_-_-_-_-_-_-_-_-_-_-_-");
        println("You want add book.");
        println("Input <name> <price> <author> <publishingHouse> <count of page> <count>");
        String[] args = scn.nextLine().split(" ");
        String name = args[0];
        BigDecimal price = new BigDecimal(args[1]);
        String author = args[2];
        String publishingHouse = args[3];
        int countOfPage = Integer.parseInt(args[4]);
        int count = Integer.parseInt(args[5]);

        String result = controller.addBook(name, price, author, publishingHouse, countOfPage, count);
        println("You add " + result);
    }

    private void addMagazine(Scanner scn) {
        println("-_-_-_-_-_-_-_-_-_-_-_-");
        println("You want add magazine.");
        println("Input <name> <price> <№> <year> <month> <day> <count of page> <count>");
        String[] args = scn.nextLine().split(" ");
        String name = args[0];
        BigDecimal price = new BigDecimal(args[1]);
        Integer number = Integer.parseInt(args[2]);
        int year = Integer.parseInt(args[3]);
        int month = Integer.parseInt(args[4]);
        int day = Integer.parseInt(args[5]);
        int countOfPage = Integer.parseInt(args[6]);
        int count = Integer.parseInt(args[7]);
        Calendar calendar = new GregorianCalendar(year, month, day);

        String result = controller.addMagazine(name, price, number, calendar, countOfPage, count);
        println("You add " + result);
    }

    private void addNewspaper(Scanner scn) {
        println("-_-_-_-_-_-_-_-_-_-_-_-");
        println("You want add newspaper.");
        println("Input <name> <price> <№> <year> <month> <day> <count>");
        String[] args = scn.nextLine().split(" ");
        String name = args[0];
        BigDecimal price = new BigDecimal(args[1]);
        Integer number = Integer.parseInt(args[2]);
        int year = Integer.parseInt(args[3]);
        int month = Integer.parseInt(args[4]);
        int day = Integer.parseInt(args[5]);
        int count = Integer.parseInt(args[6]);
        Calendar calendar = new GregorianCalendar(year, month, day);

        String result = controller.addNewspaper(name, price, number, calendar, count);
        println("You add " + result);
    }

    private void viewProduct(Scanner scn) {
        println("Select a command:");
        println("0 - Go back to the previous menu");
        println("1 - View all newspapers");
        println("2 - View all magazines");
        println("3 - View all books");
        println("4 - View all printed matters");
        int command = readCommand(scn);

        switch (command){
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
        int newCommand = readCommand(scn);

        switch (newCommand){
            case 1:
                buySomething(scn, command);
                break;
            case 2:
                break;
        }
    }

    private void buySomething(Scanner scn, int command) {
        print("Enter the id of the product you want to buy : ");
        int id = Integer.parseInt(scn.nextLine());
        print("Enter the quantity of the product you want to buy : ");
        int count = Integer.parseInt(scn.nextLine());
        switch (command){
            case 1:
                println(controller.buyNewspaper(id,count));
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


    private void println(String message){
        System.out.println(message);
    }

    private void print(String message){
        System.out.print(message);
    }

    private int readCommand(Scanner scn){
        print("Waiting for the command : ");
        return Integer.parseInt(scn.nextLine());
    }

}
