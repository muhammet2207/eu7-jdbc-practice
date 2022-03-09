package jdbctests;

import org.testng.annotations.Test;

import java.sql.*;

public class jdbc_example {
    String dbUrl="jdbc:oracle:thin:@3.89.101.80:1521:xe";
    String dbUsername="hr";
    String  dbPassword="hr";

    @Test
    public void CountNavigate() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet= statement.executeQuery("Select * from departments");
        //how many rows we have for quary
        //got to last row
        resultSet.last();
        //get the row count
        int rowCount= resultSet.getRow();
        System.out.println(rowCount);
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }//pointer is at the end
        //so go all the way up
        resultSet.beforeFirst();
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }
        resultSet= statement.executeQuery("Select * from regions");
        while(resultSet.next()){
            System.out.println(resultSet.getString(2));
        }
        resultSet.close();
        statement.close();
        connection.close();
    }
    @Test
    public void MetaData() throws SQLException {
        Connection connection= DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
        //create statement object
        Statement statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        //run query and get the result in resultset object
        ResultSet resultSet= statement.executeQuery("Select * from employees");

        //get the metabase related data inside the dbMetadara object
        DatabaseMetaData databaseMetaData=connection.getMetaData();
        System.out.println("User= "+databaseMetaData.getUserName());
        System.out.println("Database Product Name= "+databaseMetaData.getDatabaseProductName());
        System.out.println("Database  Product Version= "+databaseMetaData.getDatabaseProductVersion());
        System.out.println("Driver Name= "+databaseMetaData.getDriverName());
        System.out.println("Driver Version= "+databaseMetaData.getDriverVersion());

        //get the resultset object metadata
        ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
        //how many columns we have?
        int columnCount=resultSetMetaData.getColumnCount();
        System.out.println(columnCount);

        //how to get column names

        System.out.println(resultSetMetaData.getColumnName(1));
        System.out.println(resultSetMetaData.getColumnName(2));
        //print all the column names dynamically
        for(int i=1;i<=columnCount;i++){
            System.out.println(resultSetMetaData.getColumnName(i));
        }

        resultSet.close();
        statement.close();
        connection.close();
    }
}
