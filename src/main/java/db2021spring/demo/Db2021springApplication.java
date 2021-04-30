package db2021spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Db2021springApplication {
    public static Scanner scanner= new Scanner(System.in);
    enum Operation {
        CREATE,
        READ,
        UPDATE,
        DELETE
    }
    enum Type {
        Customer,
        Order
    }
    public static String InputData(Operation operation, Type type){
        switch (type) {
            case Customer:
                switch (operation){
                    case CREATE:
                        System.out.println(" Enter customer name");
                        break;
                    case DELETE:
                        System.out.println("Enter customer ID");
                        break;
                    case UPDATE:
                        System.out.println("Enter customer ID, to update name (ID,Name)");
                        break;
                }
                break;
            case Order:
                switch (operation) {
                    case CREATE:
                        System.out.println("Enter customer ID and order (ID,Order)");
                        break;
                }

                break;
        }
        if (!operation.equals(Operation.READ)){
            String input =scanner.nextLine();
            return input;
        }

        return "";

    }
    public static Type InputType(){
        System.out.println("Choose CU Customer /n OR Order Q Quit ");
        String input =scanner.nextLine();
        Type type = null;
        switch (input) {
            case "CU": type=Type.Customer;
            System.out.println("Customer");
            break;
            case "OR": type=Type.Order;
                System.out.println("Order");
            break;
            case "Q":
                System.out.println("Quit");
                System.exit(0);
            default:
                System.out.println("Incorrect input");

        }
        return type;

    }
    public static void InputReader(){
        Type type = InputType();
        System.out.println("Enter operation:" + "C to create " + "R to list " +"U to update " + "D to delete" +" Q to quit" );
        String input = scanner.nextLine();
        Operation operation = null;
        switch (input){
            case "C":
                operation=Operation.CREATE;
                System.out.println("Create user" );
                break;
            case "R": operation=Operation.READ;
                System.out.println("Read list");
                break;
            case "U": operation=Operation.UPDATE;
                System.out.println("Update name");
                break;
            case "D": operation=Operation.DELETE;
                System.out.println("Delete name");
                break;
            case "Q":
                System.out.println("Quit");
                return;
            default:
                System.out.println("Incorrect operation");

        }
        if (operation!=null&&type!=null) {
            System.out.println(operation.toString() + " " + type.toString());
            String data=InputData(operation,type);
            new MyDB(operation,type,data);

        }

        InputReader(); // recursion
    }
    public static void main(String[] args) {

        InputReader();

        // SpringApplication.run(Db2021springApplication.class, args);

    }


}
