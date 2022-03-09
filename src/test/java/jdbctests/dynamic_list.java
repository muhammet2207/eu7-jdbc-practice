package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class dynamic_list {
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

        //number of columns
        int colCount=resultSetMetaData.getColumnCount();

        //loop through each row
        while (resultSet.next()){
            Map<String,Object> row=new HashMap<>();

            for (int i = 1; i <= colCount; i++) {
                row.put(resultSetMetaData.getColumnName(i),resultSet.getObject(i));
            }

            //add your map to List
            queryData.add(row);
        }

        //print the result
        for(Map<String,Object> row:queryData){
            System.out.println(row.toString());
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
