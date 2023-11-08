// This is currently in Development its features are not added to the program

package reader;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadUser
{
    int rows;
    String location_1, location_2;
    FileReader fw1, fw2;
    public ReadUser()
    {
        System.out.println("This is not allowed please use ReadUser(String <user_location>, String <user_holdings_location>)");
        return;
    }
    public ReadUser(String location_1, String location_2)
    {
        try
        {
            this.location_1 = location_1;
            this.location_2 = location_2;
            fw1 = new FileReader(location_1);
            fw2 = new FileReader(location_2);
        }
        catch (Exception e)
        {
            System.out.println("File not Found");
            return;
        }
    }

    public void read_user_info()
    {
        try
        {
            BufferedReader file = new BufferedReader(fw1);
            String user_info_data[];
            while(file.ready())
            {
                
                user_info_data = file.readLine().split(",");
            }
        }
        catch (Exception e)
        {
            System.out.println("Something Unexpected Happened");
        }


    }

}