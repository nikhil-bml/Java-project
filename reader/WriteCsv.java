// This is currently in Development its features are not added to the program
package reader;
import java.io.FileWriter;
import user.UserInfo;

public class WriteCsv
{
    FileWriter fw1, fw2;
    public WriteCsv()
    {
        try
        {
            fw1 = new FileWriter("reader/data/users.csv", true);
            fw2 = new FileWriter("reader/data/user_holdings.csv", true);
        }
        catch (Exception e)
        {
            System.out.println("File not Found");
        }
        
    }
    public WriteCsv(String location_1, String location_2)
    {
        try
        {
            fw1 = new FileWriter(location_1, true);
            fw2 = new FileWriter(location_2, true);
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
            String data = user.username + ',' + user.password + '\n';
            fw1.write(data);
            fw1.flush();
        }
        catch (Exception e)
        {
            System.out.println("Something Unexpected happened");
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
}