package user;
import logic.Logic;
public class UserInfo
{
    public String username, password;
    public int owned_assets_equity[], owned_assets_equity_quantity[], owned_assets_debt[], owned_assets_debt_quantity[], owned_assets_real_estate[], owned_assets_real_estate_quantity[];
    public int tries;
    public double wallet;
    public UserInfo(String username, String password, double wallet)
    {
        this.username = username;
        this.password = password;
        this.wallet = wallet;
        tries = 0;
    }
    public UserInfo(String username, String password)
    {
        this.username = username;
        this.password = password;
        this.wallet = 0;
        tries = 0;
    }

    public void set_user_assets(int owned_assets[],int owned_assets_quantity[], String asset_type)
    {
        if (asset_type.equals("equity"))
        {
            owned_assets_equity = new int[owned_assets.length];
            owned_assets_equity_quantity = new int[owned_assets_quantity.length];
            owned_assets_equity = owned_assets;
            owned_assets_equity_quantity = owned_assets_quantity;
        }

        else if (asset_type.equals("debt"))
        {
            owned_assets_debt = new int[owned_assets.length];
            owned_assets_debt_quantity = new int[owned_assets_quantity.length];
            owned_assets_debt = owned_assets;
            owned_assets_debt_quantity = owned_assets_quantity;
        }

        else if (asset_type.equals("real_estate"))
        {
            owned_assets_real_estate = new int[owned_assets.length];
            owned_assets_real_estate_quantity = new int[owned_assets_quantity.length];
            owned_assets_real_estate = owned_assets;
            owned_assets_real_estate_quantity = owned_assets_quantity;
        }
    }

    public boolean wallet_status(double total_worth)
    {
        if (wallet - total_worth >= 0)
        {
            return true;
        } 
        System.out.println("Not Enough Funds available in the Wallet, \nPlease Load Some funds and try again");
        return false;
    }
    
}