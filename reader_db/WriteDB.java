package reader_db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class WriteDB
{
    String url, username, password;
    Statement cursor;
    public WriteDB(String url, String username, String password)
    {
        String main_query, count_query;
        Connection connection;
        try
        {
            connection = DriverManager.getConnection(url, username, password);
            cursor = connection.createStatement();
        }
        catch(java.sql.SQLException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }

    public void add_new_entry(String table_name, String name, Double price, Double returns, Double risk)
    {
        String main_query = "INSERT INTO " 
                            + table_name 
                            + " VALUES("
                            +"'" + name + "'" + ","
                            + price + ","
                            + returns + ","
                            + risk + ")";

        try
        {
        cursor.executeUpdate(main_query);
        }
        catch(java.sql.SQLException e)
        {
            System.out.println(e);
        }
    }
}