package db2021spring.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MyDB {
    public MyDB () {


       String username="root";
       String password="87mosCraciun19";
       String database="MyDB";
       String url="jdbc:mysql://localhost:3306/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
         Scanner inp= new Scanner(System.in);
        System.out.println("Enter username");
        String user_name = inp.nextLine();


       try (Connection con= DriverManager.getConnection(url + database, username, password)){
           if(!con.isClosed()){
               // System.out.println("Ok Connecting");

               String sql= "INSERT INTO Customers values (null,?)";
               PreparedStatement ps=con.prepareStatement(sql);
               ps.setString(1,user_name); // Tells what number of ? we refer to
               ps.executeUpdate();
               // Getting the rows
               sql="SELECT * FROM Customers";
               ps= con.prepareStatement(sql);
               ResultSet resultSet= ps.executeQuery();

               while (resultSet.next()){

                   String FirstName=resultSet.getString("FirstName");
                   System.out.println("Name:" + FirstName);
               }
           }else {
               System.out.println(":-( no connection");
           }
       }

       catch (Exception e){
           System.out.println("Error " + e.getMessage());
       }



    }
}
