package reader_db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;

public class ReadDB
{
    String url, username, password;
    public double total_data_list[][];
    String total_data_names[];

    public ReadDB(String url, String username, String password, String table_name)
    {
        String main_query, count_query;
        Connection connection;
        Statement cursor;
        try
        {

            connection = DriverManager.getConnection(url, username, password);
            cursor = connection.createStatement();
            main_query = "SELECT * FROM " + table_name;
            count_query = "SELECT COUNT(*) FROM " + table_name;

            int rows=0;
            ResultSet resultObj = cursor.executeQuery(count_query);

            while (resultObj.next()) 
            {
                rows = resultObj.getInt("count");
            }
            
            total_data_list = new double[rows][3];
            total_data_names = new String[rows];

            resultObj = cursor.executeQuery(main_query); 
            int name_row_ptr = 0, data_row_ptr = 0;
            while(resultObj.next())
            {
                double temporary_data_list[] = new double[3];
                total_data_names[name_row_ptr] = resultObj.getString("name");

                temporary_data_list[0] = resultObj.getDouble("price");
                temporary_data_list[1] = resultObj.getDouble("return");
                temporary_data_list[2] = resultObj.getDouble("risk");
                
                total_data_list[data_row_ptr] = temporary_data_list;
                name_row_ptr ++;
                data_row_ptr ++;
            }

        }
        catch(java.sql.SQLException e)
        {
            System.out.println(e);
            System.exit(0);
        }
    }
    public void print()
    {
        
        for (int i =0; i < total_data_names.length; i++)
        {
            System.out.print(i + ". ");
            System.out.println(total_data_names[i]);
        }
    }


}

// class temp{
//     public static void main(String args[])
//     {
//         ReadDB db_obj = new ReadDB(
//             "jdbc:postgresql://localhost:5432/portfolio_management",
//             "postgres",
//             "123456789",
//             "equity"
//             );
//         // db_obj.load_table("equity");
        
//     }
// }