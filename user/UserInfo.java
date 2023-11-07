package user;
public class UserInfo
{
    public String username, password;
    public int owned_assets_equity[], owned_assets_equity_quantity[], owned_assets_debt[], owned_assets_debt_quantity[], owned_assets_real_estate[], owned_assets_real_estate_quantity[];

    public UserInfo(String username, String password)
    {
        this.username = username;
        this.password = password;
        owned_assets_equity = null;
        owned_assets_equity_quantity = null;
        owned_assets_debt = null;
        owned_assets_debt_quantity = null;
        owned_assets_real_estate = null;
        owned_assets_real_estate_quantity = null;
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
    
}