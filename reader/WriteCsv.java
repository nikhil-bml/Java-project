package reader;
import java.io.FileWriter;
import user.UserInfo;
import user.User;
import user.AdminInfo;
import java.io.BufferedReader;
import java.io.FileReader;

public class WriteCsv
{
    FileWriter fw1, fw2, fw3;
    String location_1, location_2, location_3;
    public WriteCsv()
    {
        try
        {
            location_1 = "reader/data/users.csv";
            location_2 = "reader/data/user_holdings.csv";
            location_3 = "reader/data/admins.csv";
            fw1 = new FileWriter(location_1, true);
            fw2 = new FileWriter(location_2, true);
            fw3 = new FileWriter(location_3, true);
        }
        catch (Exception e)
        {
            System.out.println("File not Found");
        }
        
    }

    public void write_user(UserInfo user)
    {
        try
        {
            String data = user.username + ',' + user.password + ',' + user.wallet +'\n';
            fw1.write(data);
            fw1.flush();
        }
        catch (Exception e)
        {
            System.out.println("Something Unexpected happened");
        }
    }

    BufferedReader get_new_bufferreader(String file_reader_type)
    {
        try
        {
            if (file_reader_type.equals("fr2"))
            {
                FileReader fr2_2 = new FileReader(location_2);
                BufferedReader file_holdings_2 = new BufferedReader(fr2_2);
                return file_holdings_2;
            }
            else if (file_reader_type.equals("fr1"))
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
    
    public void remove_user_info(User user_inst, String username)
    {
        try {
            BufferedReader user_file_reader = get_new_bufferreader("fr1");
            String line;
            int rows = 0;
            
            while (user_file_reader.ready())
            {
                user_file_reader.readLine();
                rows ++;
            }

            String user_strings[] = new String[rows];

            user_file_reader = get_new_bufferreader("fr1");
            int user_str_ptr = 0; 
            while (user_file_reader.ready()) 
            {
                line = user_file_reader.readLine();
                String[] userData = line.split(",");
                String curr_username = userData[0];

                if (!curr_username.equals(username)) {
                    user_strings[user_str_ptr] = line + "\n";
                    user_str_ptr ++;
                }
            }
            
            FileWriter user_file_writer = new FileWriter(this.location_1); 
            String curr_data;
            for (int i=0; i<user_strings.length; i++)
            {
                if (user_strings[i] != null)
                {
                    curr_data = user_strings[i];
                    user_file_writer.write(curr_data);
                }
            }

            user_file_reader.close();
            user_file_writer.close();
            
            BufferedReader user_info_file_reader = get_new_bufferreader("fr2");
            rows = 0;
            
            while (user_info_file_reader.ready())
            {
                user_info_file_reader.readLine();
                rows ++;
            }

            String user_info_strings[] = new String[rows];

            user_info_file_reader = get_new_bufferreader("fr2");
            int user_info_str_ptr = 0; 
            while (user_info_file_reader.ready()) 
            {
                line = user_info_file_reader.readLine();
                String[] user_info_data = line.split(",");
                String curr_username = user_info_data[0];

                if (!curr_username.equals(username)) {
                    user_info_strings[user_info_str_ptr] = line + "\n";
                    user_info_str_ptr ++;
                }
            }

            FileWriter user_info_file_writer = new FileWriter(this.location_2); 

            for (int i=0; i<user_info_strings.length; i++)
            {
                if (user_info_strings[i] != null)
                {
                    curr_data = user_info_strings[i];
                    user_info_file_writer.write(curr_data);
                }
            }

            user_info_file_reader.close();
            user_info_file_writer.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }

    public void write_user_holdings(UserInfo user)
    {
        try
        {
            String asset_type, asset_quantity_type, data;
            if (user.owned_assets_equity != null)
            {
                asset_type = "equity";
                asset_quantity_type = "equity_quantity";
                
                for(int asset: user.owned_assets_equity)
                {
                    data = user.username + ',' + asset_type + ',' + asset + "\n";
                    fw2.write(data);
                }
                for(int asset_quantity: user.owned_assets_equity_quantity)
                {
                    data = user.username + ',' + asset_quantity_type + ',' + asset_quantity + "\n";
                    fw2.write(data);
                }
                fw2.flush();
            }

            if (user.owned_assets_debt != null)
            {
                asset_type = "debt";
                asset_quantity_type = "debt_quantity";
                
                for(int asset: user.owned_assets_debt)
                {
                    data = user.username + ',' + asset_type + ',' + asset + "\n";
                    fw2.write(data);
                }
                for(int asset_quantity: user.owned_assets_debt_quantity)
                {
                    data = user.username + ',' + asset_quantity_type + ',' + asset_quantity + "\n";
                    fw2.write(data);
                }
                fw2.flush();
            }

            if (user.owned_assets_real_estate != null)
            {
                asset_type = "real_estate";
                asset_quantity_type = "real_estate_quantity";
                
                for(int asset: user.owned_assets_real_estate)
                {
                    data = user.username + ',' + asset_type + ',' + asset + "\n";
                    fw2.write(data);
                }
                for(int asset_quantity: user.owned_assets_real_estate_quantity)
                {
                    data = user.username + ',' + asset_quantity_type + ',' + asset_quantity + "\n";
                    fw2.write(data);
                }
                fw2.flush();
            }

        }
        catch (Exception e)
        {
            System.out.println("Something Unexpected happened");
        }

    }

    public void write_admin_info(AdminInfo admin)
    {
        try
        {
            String data = admin.username + ',' + admin.password + '\n';
            fw3.write(data);
            fw3.flush();
        }
        catch (Exception e)
        {
            System.out.println("Something Unexpected happened");
        }
    }
}