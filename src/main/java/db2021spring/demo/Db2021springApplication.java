package db2021spring.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class Db2021springApplication {
    public static Scanner input= new Scanner(System.in);
    enum Operation {
        CREATE,
        READ,
        UPDATE,
        DELETE
    }
    public static void InputReader(){
        System.out.println("Enter operation:" + "C to create " + "R to list " +"U to update " + "D to delete" +" Q to quit" );
        String operation = input.nextLine();
        switch (operation){
            case "C":
                System.out.println("Create user" );

                break;
            case "R":
                System.out.println("Read list");
                break;
            case "U":
                System.out.println("Update name");
                break;
            case "D":
                System.out.println("Delete name");
                break;
            case "Q":
                System.out.println("Quit");
                return;
            default:
                System.out.println("Incorrect operation");

        }

        InputReader(); // recursion

    }
    public static void main(String[] args) {

        InputReader();

        // SpringApplication.run(Db2021springApplication.class, args);
        // new MyDB();
    }


}
