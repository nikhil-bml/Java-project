
class Logic
{    
    Logic()
    {

    }
    void set_assets(int owned_equity_assets[], int owned_equity_assets_quantity[] )
    {
        this.owned_equity_assets = owned_equity_assets;
        this.owned_equity_assets_quantity = owned_equity_assets_quantity;
    }

    double assets_info(String info)
    {
        String choices[] = {"risk","worth"};

        if (!Arrays.asList(choices).contains(info)){
            System.out.println(info + " Choice is not applicable");
            return -1;
        }

        if (info.equals(choices[1]))
        {
            double worth = 0;
            
            if (owned_equity_assets == null)
            {
                System.out.println("No Equity Assets set");
                return -1;
            }

            for(int iter=0; iter<owned_equity_assets.length; iter++){
                
                int asset = owned_equity_assets[iter];
                int quantity = owned_equity_assets_quantity[iter];
                worth += Double.parseDouble(data_list[asset][1]) * quantity;
            }

            return worth;
        }

        if (info.equals(choices[0]))
        {
            double risk = 0;
            
            if (owned_equity_assets == null)
            {
                System.out.println("No Equity Assets set");
                return -1;
            }

            for(int iter=0; iter<owned_equity_assets.length; iter++){
                
                int asset = owned_equity_assets[iter];
                risk += Double.parseDouble(data_list[asset][2]);
            }

            return risk;
        }
        

        System.out.println("Something Went Wrong here");
        return -1;
    }
}