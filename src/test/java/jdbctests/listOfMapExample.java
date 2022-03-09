package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class listOfMapExample {
    String dbUrl="jdbc:oracle:thin:@3.89.101.80:1521:xe";
    String dbUsername="hr";
    String  dbPassword="hr";
    @Test
    public void MetaData() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet= statement.executeQuery("Select first_name,last_name,salary,job_id from employees where rownum<6");

        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        //list for keeping all rows of map
        List<Map<String,Object>> queryData=new ArrayList<>();
        //go to first row
        resultSet.next();
        Map<String,Object> row1=new HashMap<>();
        row1.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row1.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row1.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row1.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        System.out.println(row1.toString());

        //move to second row
        resultSet.next();
        Map<String,Object> row2=new HashMap<>();
        row2.put(resultSetMetaData.getColumnName(1),resultSet.getString(1));
        row2.put(resultSetMetaData.getColumnName(2),resultSet.getString(2));
        row2.put(resultSetMetaData.getColumnName(3),resultSet.getString(3));
        row2.put(resultSetMetaData.getColumnName(4),resultSet.getString(4));
        System.out.println(row2.toString());
        System.out.println(row2.get("SALARY"));

        //adding rows to my list
        queryData.add(row1);
        queryData.add(row2);
        //get the steven last name directly from the list
        System.out.println(queryData.get(0).get("LAST_NAME"));
        resultSet.close();
        statement.close();
        connection.close();
    }
}
