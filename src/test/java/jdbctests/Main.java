package jdbctests;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {
       String dbUrl="jdbc:oracle:thin:@3.89.101.80:1521:xe";
       String dbUsername="hr";
       String  dbPassword="hr";
       //create connection
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement=connection.createStatement();
        //run query and get the result in resultset object
        ResultSet resultSet= statement.executeQuery("Select * from regions");
        //move pointer to first row
//         resultSet.next();
//        System.out.println(resultSet.getString("region_name"));
//        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2));
//        resultSet.next();
//        System.out.println(resultSet.getString(2));
//        System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2));


        //getting all info
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"-"+resultSet.getString(2));
        }

        //close all connections
        resultSet.close();
        statement.close();
        connection.close();
    }
}
