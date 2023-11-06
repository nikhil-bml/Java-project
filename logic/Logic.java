package logic;
import reader.ReadCsv;
import java.util.Arrays;
public class Logic
{   
    int owned_equity_assets[], owned_equity_assets_quantity[];
    int owned_debt_assets[], owned_debt_assets_quantity[];
    int owned_real_estate_assets[], owned_real_estate_assets_quantity[];
    public ReadCsv all_equity_assets, all_debt_assets, all_real_estate_assets;

    public Logic()
    {
        all_equity_assets = new ReadCsv("reader/data/equity.csv");
        all_debt_assets = new ReadCsv("reader/data/debt.csv");
        all_real_estate_assets = new ReadCsv("reader/data/real_estate.csv");
    }

    public void set_assets(int owned_assets[], int owned_assets_quantity[], String asset_type )
    {
        if (asset_type.equals("equity"))
        {
            this.owned_equity_assets = owned_assets;
            this.owned_equity_assets_quantity = owned_assets_quantity;
        }
        else if (asset_type.equals("debt"))
        {
            this.owned_debt_assets = owned_assets;
            this.owned_debt_assets_quantity = owned_assets_quantity;
        }
        else if (asset_type.equals("real_estate"))
        {
            this.owned_real_estate_assets = owned_assets;
            this.owned_real_estate_assets_quantity = owned_assets_quantity;
        }
    }

    public double get_asset_info(String asset_type, String info)
    {
        String choices[] = {"risk","worth","return"};

        if (!Arrays.asList(choices).contains(info)){
            System.out.println(info + " Choice is not applicable");
            return -1;
        }

        int owned_assets[], owned_assets_quantity[];
        double asset_value = 0;
        int asset = 0, quantity = 0;
        ReadCsv ob;

        if (asset_type.equals("equity"))
        {
            owned_assets = this.owned_equity_assets;
            owned_assets_quantity = this.owned_equity_assets_quantity;
            ob = all_equity_assets;
        }

        else if (asset_type.equals("debt"))
        {
            owned_assets = this.owned_debt_assets;
            owned_assets_quantity = this.owned_debt_assets_quantity;
            ob = all_debt_assets;
        }

        else if (asset_type.equals("real_estate"))
        {
            owned_assets = this.owned_real_estate_assets;
            owned_assets_quantity = this.owned_real_estate_assets_quantity;
            ob = all_real_estate_assets;
        }
        else
        {
            owned_assets = this.owned_equity_assets;
            owned_assets_quantity = this.owned_equity_assets_quantity;
            ob = all_equity_assets;
        }

        if (owned_assets == null)
        {
            return 0;
        }

        
        for(int iter=0; iter<owned_assets.length; iter++)
        {       
            asset = owned_assets[iter];
            quantity = owned_assets_quantity[iter];
            asset_value += ((double)ob.total_data_list.get(asset).get(1)) * quantity;
        }

        if (info.equals("worth"))
        {
            return asset_value;
        }
        else if (info.equals("risk"))
        {
            double curr_asset_alloc = 0, risk = 0;
            for(int iter=0; iter<owned_assets.length; iter++)
            {       
                asset = owned_assets[iter];
                quantity = owned_assets_quantity[iter];
                curr_asset_alloc = (((double)ob.total_data_list.get(asset).get(1)) * quantity) / asset_value;
                risk += ((double)ob.total_data_list.get(asset).get(3)) * curr_asset_alloc;
            }
            
            return risk;
        }
        else if (info.equals("return"))
        {
            double curr_asset_alloc = 0, returns = 0;
            for(int iter=0; iter<owned_assets.length; iter++)
            {       
                asset = owned_assets[iter];
                quantity = owned_assets_quantity[iter];
                curr_asset_alloc = (((double)ob.total_data_list.get(asset).get(1)) * quantity) / asset_value;
                returns += ((double)ob.total_data_list.get(asset).get(2)) * curr_asset_alloc;
            }
            
            return returns;
        }

        return -1;
    }

    public double get_asset_allocation(String asset_type)
    {
        double allocation=0;
        double total_value = get_asset_info("equity","worth") + get_asset_info("debt","worth") + get_asset_info("real_estate", "worth");

        allocation = (get_asset_info(asset_type,"worth") / total_value);
        return allocation;
    }

    public double total_assets_info(String info)
    {
        String choices[] = {"risk","worth","return"};

        if (!Arrays.asList(choices).contains(info)){
            System.out.println(info + " Choice is not applicable");
            return -1;
        }

        if (info.equals(choices[1]))
        {
            double worth = 0;

            worth = get_asset_info("equity","worth") + 
            get_asset_info("debt","worth") + 
            get_asset_info("real_estate", "worth");

            return worth;
        }

        else if (info.equals(choices[0]))
        {
            double risk = 0;
            
            risk = get_asset_info("equity","risk") * get_asset_allocation("equity") + 
            get_asset_info("debt","risk") * get_asset_allocation("debt") +
            get_asset_info("real_estate", "risk") * get_asset_allocation("real_estate");

            if (risk > 0)
            {
                return risk;
            }

            return 0;
        }
        
        else if (info.equals(choices[2]))
        {
            double returns = 0;
            
            returns = get_asset_info("equity","return") * get_asset_allocation("equity") + 
            get_asset_info("debt","return") * get_asset_allocation("debt") +
            get_asset_info("real_estate", "return") * get_asset_allocation("real_estate");

            if (returns > 0)
            {
                return returns;
            }
            
            return 0;

        }

        System.out.println("Something Went Wrong here");
        return -1;
    }

    public String portfolio_risk(double total_assets_risk)
    {
            String risk_profile = "";
            if (total_assets_risk >= 0 && total_assets_risk < 5)
            {
                risk_profile = "Very Low";
            }
            else if (total_assets_risk >= 5 && total_assets_risk < 10)
            {
                risk_profile = "Low-Medium Risk";
            }
            else if (total_assets_risk >= 10 && total_assets_risk < 15)
            {
                risk_profile = "Medium-High Risk";
            }
            else if (total_assets_risk >= 15)
            {
                risk_profile = "Very High Risk";
            }

            return risk_profile;

    }
}