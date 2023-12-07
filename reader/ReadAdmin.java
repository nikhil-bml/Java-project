package reader;
import java.io.BufferedReader;
import java.io.FileReader;
import user.User;
import user.UserInfo;
import user.Admin;

public class ReadAdmin
{
    int rows;
    String location_1;
    FileReader fr1;

    public ReadAdmin()
    {
        try
        {
            location_1 = "reader/data/admins.csv";
            fr1 = new FileReader(location_1);
        }
        catch(java.io.FileNotFoundException e)
        {
            System.out.println("File Not Found");
        }
        // catch (java.io.IOException e)
        // {
        //     System.out.println("IO Exception occured");
        // }
    }

    
    public BufferedReader get_new_bufferreader(String file_reader_type)
    {
        try
        {
            if (file_reader_type.equals("fr1"))
            {
                FileReader fr1_1 = new FileReader(location_1);
                BufferedReader file_holdings_1 = new BufferedReader(fr1_1);
                return file_holdings_1;
            }
            else
            {
                return null;
            }
        }
        catch (Exception e)
        {
            System.out.println("Something Unexpected happened");
        }

        return null;

    } 

    public void read_admin_info(Admin admin_inst)
    {
        try
        {
            BufferedReader admin_file = get_new_bufferreader("fr1");
            String admin_info_data[];
            int filled = 0;
            
            while(admin_file.ready())
            {
                admin_info_data = admin_file.readLine().split(",");
                admin_inst.admin_file_registration(admin_info_data[0], admin_info_data[1]);
                filled += 1;
            }
            admin_file.close();
            
        }
        catch (Exception e)
        {
            System.out.println("Something Unexpected Happened");
        }

    }

}