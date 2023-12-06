package reader_db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import reader.ReadCred;

public class WriteDB
{
    Statement cursor;
    public WriteDB()
    {
        ReadCred creds = new ReadCred();
        String main_query, count_query;
        Connection connection;
        try
        {
            connection = DriverManager.getConnection(creds.url, creds.username, creds.password);
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
            System.out.println(e.getMessage());
        }
    }

    public void delete_entry(String table_name, String name)
    {
        String main_query = "DELETE FROM " 
                            + table_name 
                            + " WHERE "
                            + "name = "
                            + "'" + name + "'";
        try
        {
            cursor.executeUpdate(main_query);
        }
        catch(java.sql.SQLException e)
        {
            System.out.println(e.getMessage());
        }

    }
}