package reader;
import java.io.BufferedReader;
import java.io.FileReader;
import user.User;
import user.UserInfo;
public class ReadUser
{
    int rows;
    String location_1, location_2;
    FileReader fr1, fr2;

    public ReadUser()
    {
        try
        {
            location_1 = "reader/data/users.csv";
            location_2 = "reader/data/user_holdings.csv";
            fr1 = new FileReader(location_1);
            fr2 = new FileReader(location_2);
        }
        catch (java.io.FileNotFoundException e)
        {
            System.out.println("Default Files were not found creating...");
        }
        
    }

    public ReadUser(String location_1, String location_2)
    {
        try
        {
            this.location_1 = location_1;
            this.location_2 = location_2;
            fr1 = new FileReader(location_1);
            fr2 = new FileReader(location_2);
        }
        catch (java.io.FileNotFoundException e)
        {
            System.out.println("File not Found");
            return;
        }
    }

    public BufferedReader get_new_bufferreader(String file_reader_type)
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
        catch (java.io.IOException e)
        {
            System.out.println("Problem with Buffered Reader has occured");
        }
        return null;

    } 

    public void read_user_info(User user_inst)
    {
        try
        {
            BufferedReader user_file = get_new_bufferreader("fr1");
            String user_info_data[];
            int filled = 0;
            while(user_file.ready())
            {
                user_info_data = user_file.readLine().split(",");
                user_inst.user_file_registration(user_info_data[0], user_info_data[1], Double.parseDouble(user_info_data[2]));
                filled += 1;
            }
            user_file.close();

            int equity_row, equity_quantity_row, debt_row, debt_quantity_row, real_estate_row, real_estate_quantity_row;
            int equity_filled, equity_quantity_filled, debt_filled, debt_quantity_filled, real_estate_filled, real_estate_quantity_filled; 
            int equity[], equity_quantity[], debt[], debt_quantity[], real_estate[], real_estate_quantity[];
            UserInfo user;
            for (int i=0; i<filled; i++)
            {
                
                equity_row = 0; 
                equity_quantity_row = 0; 
                debt_row = 0;
                debt_quantity_row = 0; 
                real_estate_row = 0;
                real_estate_quantity_row = 0;
                equity_filled = 0;
                equity_quantity_filled = 0; 
                debt_filled = 0;
                debt_quantity_filled = 0; 
                real_estate_filled = 0;
                real_estate_quantity_filled = 0;

                BufferedReader file_holdings = get_new_bufferreader("fr2");

                user = user_inst.users[i];

                while(file_holdings.ready())
                {
                    user_info_data = file_holdings.readLine().split(",");
                    if (user.username.equals(user_info_data[0]))
                    {
                        if (user_info_data[1].equals("equity"))
                        {
                            equity_row += 1;
                        }
                        else if (user_info_data[1].equals("equity_quantity"))
                        {
                            equity_quantity_row += 1;
                        }
                        else if (user_info_data[1].equals("debt"))
                        {
                            debt_row += 1;
                        }
                        else if (user_info_data[1].equals("debt_quantity"))
                        {
                            debt_quantity_row += 1;
                        }
                        else if (user_info_data[1].equals("real_estate"))
                        {
                            real_estate_row += 1;
                        }
                        else if (user_info_data[1].equals("real_estate_quantity"))
                        {
                            real_estate_quantity_row += 1;
                        }

                    }
                }

                file_holdings.close();
                file_holdings = get_new_bufferreader("fr2");


                equity = new int[equity_row];
                equity_quantity = new int[equity_quantity_row];
                debt = new int[debt_row];
                debt_quantity = new int[debt_quantity_row];
                real_estate = new int[real_estate_row];
                real_estate_quantity = new int[real_estate_quantity_row];
                
                
                while(file_holdings.ready())
                {
                    user_info_data = file_holdings.readLine().split(",");
                    if (user_info_data[0].equals(user.username))
                    {
                        
                        if (user_info_data[1].equals("equity"))
                        {
                            equity[equity_filled] = Integer.parseInt(user_info_data[2]);
                            equity_filled += 1;
                        }
                        else if (user_info_data[1].equals("equity_quantity"))
                        {
                            equity_quantity[equity_quantity_filled] = Integer.parseInt(user_info_data[2]);
                            equity_quantity_filled += 1;
                        }
                        else if (user_info_data[1].equals("debt"))
                        {
                            debt[debt_filled] = Integer.parseInt(user_info_data[2]);
                            debt_filled += 1;
                        }
                        else if (user_info_data[1].equals("debt_quantity"))
                        {
                            debt_quantity[debt_quantity_filled] = Integer.parseInt(user_info_data[2]);
                            debt_quantity_filled += 1;
                        }
                        else if (user_info_data[1].equals("real_estate"))
                        {
                            real_estate[real_estate_filled] = Integer.parseInt(user_info_data[2]);
                            real_estate_filled += 1;
                        }
                        else if (user_info_data[1].equals("real_estate_quantity"))
                        {
                            real_estate_quantity[real_estate_quantity_filled] = Integer.parseInt(user_info_data[2]);
                            real_estate_quantity_filled += 1;
                        }
                    }
                }

                user.set_user_assets(equity, equity_quantity, "equity");
                user.set_user_assets(debt, debt_quantity, "debt");
                user.set_user_assets(real_estate, real_estate_quantity, "real_estate");
            }
            
        }
        catch (java.io.FileNotFoundException e)
        {
            System.out.println("File not found");
        }
        catch (java.io.IOException e)
        {
            System.out.println("IO Exception has occured");
        }


    }

}