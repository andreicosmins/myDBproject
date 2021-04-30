package db2021spring.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class MyDB {
    public MyDB (Db2021springApplication.Operation Operation, Db2021springApplication.Type Type,String data) {
       String username="root";
       String password="870MCnts#";
       String database="MyDB";
       String url="jdbc:mysql://localhost:3306/";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        //System.out.println(Operation.toString() + " " + Type.toString());


       try (Connection con= DriverManager.getConnection(url + database, username, password)){
           if(!con.isClosed()){
               // System.out.println("Ok Connecting");
               String sql=null;
               PreparedStatement ps=null;

               switch (Type){
                   case Customer:
                       switch (Operation){
                           case CREATE:  sql= "INSERT INTO Customers values (null,?)";
                                ps=con.prepareStatement(sql);
                               ps.setString(1,data); // Tells what number of ? we refer to
                               ps.executeUpdate();
                               break;
                           case READ:
                                sql="SELECT * FROM Customers";
                                ps=con.prepareStatement(sql);
                               ResultSet resultSet= ps.executeQuery();

                               while (resultSet.next()){

                                   String FirstName=resultSet.getString("FirstName");
                                   Integer CustomerID=resultSet.getInt("CustomerID");

                                   System.out.println(" ID " + CustomerID + " Name:" + FirstName);
                               }
                               break;
                           case DELETE: sql= "DELETE FROM Customers WHERE (`CustomerID` = ?);";
                                        ps=con.prepareStatement(sql);
                                        ps.setString(1,data);
                                        ps.executeUpdate();
                                        break;
                           case UPDATE:    sql= "UPDATE Customers SET `FirstName` = ? WHERE (`CustomerID` = ?);";
                               ps=con.prepareStatement(sql);
                               String FirstName;
                               String ID;
                               String[] List;
                               List=data.split(",");
                               ID=List[0];
                               FirstName=List[1];
                               ps.setString(1,FirstName);
                               ps.setString(2,ID);
                               ps.executeUpdate();
                               break;

                       } break;
                   case Order:
                       switch (Operation){
                           case CREATE: sql= "INSERT INTO Orders (`customerID`, `Order`) VALUES (? ,? );";
                           String Order;
                           String ID;
                           String [] List;
                           List=data.split(",");
                           ID=List[0];
                           Order=List[1];
                           ps=con.prepareStatement(sql);
                           ps.setString(1,ID);
                           ps.setString(2,Order);
                           ps.executeUpdate();
                               break;
                           case READ: sql="SELECT * FROM Orders";
                               ps=con.prepareStatement(sql);
                               ResultSet resultSet= ps.executeQuery();

                               while (resultSet.next()){

                                   String OrderID=resultSet.getString("OrderID");
                                   Integer CustomerID=resultSet.getInt("CustomerID");
                                   String order= resultSet.getNString("Order");

                                   System.out.println(" Order ID " + OrderID + " CustomerID:" + CustomerID + " Order " + order);
                               }
                               break;
                       } break;
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
