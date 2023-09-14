import java.util.Scanner;

class PortfolioBackend
{
    // equity assets are in INR
    // equity std is in their respective units
    // return is in percentage
    double equity_assets, debt_assets, gold_assets, real_estate_assets, 
    EQUITY_STD, GOLD_STD, DEBT_STD, REAL_ESTATE_STD, 
    EQUITY_RETURN, GOLD_RETURN, DEBT_RETURN, REAL_ESTATE_RETURN;
    int DASH_CONSTANT;
    PortfolioBackend(){
        EQUITY_STD = 15.08;
        DEBT_STD = 0.94;
        GOLD_STD = 11.4;
        REAL_ESTATE_STD = 15.94;
        EQUITY_RETURN = 12.03;
        GOLD_RETURN = 15;
        DEBT_RETURN = 7.52;
        REAL_ESTATE_RETURN = 36.32;
        equity_assets = 0;
        debt_assets = 0;
        gold_assets = 0;
        real_estate_assets = 0;
        DASH_CONSTANT = 70;
    }

    void add_assets()
    {
        Scanner cin = new Scanner(System.in);
        System.out.print("Enter the worth of your assets in equity in INR(Enter 0 if you don't have any): ");
        equity_assets = cin.nextDouble();
        System.out.print("Enter the worth of your assets in debt in INR(Enter 0 if you don't have any): ");
        debt_assets = cin.nextDouble();
        System.out.print("Enter the worth of your assets in gold in INR(Enter 0 if you don't have any): ");
        gold_assets = cin.nextDouble();
        System.out.print("Enter the worth of your assets in real estate in INR(Enter 0 if you don't have any): ");
        real_estate_assets = cin.nextDouble();
    }

    void calculate_networth()
    {
        double total = equity_assets + debt_assets + gold_assets + real_estate_assets;
        System.out.println("Your Networth is INR: " + total);
    }

    void portfolio_risk()
    {
        double total = equity_assets + debt_assets + gold_assets + real_estate_assets;
        if (total > 0)
        {
            double portfolio_overall_std = (
                (equity_assets/total)*EQUITY_STD +
                (debt_assets/total)*DEBT_STD +
                (gold_assets/total)*GOLD_STD +
                (real_estate_assets/total)*REAL_ESTATE_STD
                );
            System.out.println("Your overall Portfolio Standard Deviation: " + portfolio_overall_std);

            if (portfolio_overall_std >= 0 && portfolio_overall_std < 5)
            {
                System.out.println("Your overall Risk Profile is Very Low");
            }
            else if (portfolio_overall_std >= 5 && portfolio_overall_std < 10)
            {
                System.out.println("Your overall Risk Profile is Low-Medium Risk");
            }
            else if (portfolio_overall_std >= 10 && portfolio_overall_std < 15)
            {
                System.out.println("Your overall Risk Profile is Medium-High Risk");
            }
            else if (portfolio_overall_std >= 15)
            {
                System.out.println("Your overall Risk Profile is Very High Risk");
            }
        }
        else{
            System.out.println("Please add your assets before accessing this option");
        }
    }

    void overall_return()
    {
        double total = equity_assets + debt_assets + gold_assets + real_estate_assets;
        if (total > 0)
        {
            double portfolio_overall_return = (
                (equity_assets/total)*EQUITY_RETURN +
                (debt_assets/total)*DEBT_RETURN +
                (gold_assets/total)*GOLD_RETURN +
                (real_estate_assets/total)*REAL_ESTATE_RETURN
                );

            System.out.println("Your overall Portfolio Return in % " + portfolio_overall_return);
        }
        else{
            System.out.println("Please add your assets before accessing this option");
        }

    }

    // The functions print_dash and dash_calculator are to increase uniformity of dashes and words on the screen
    void print_dash(int times)
    {
        System.out.print(" ");
        for (int line=0;line < times; line++)
        {
            System.out.print("-");
        }
        System.out.print(" ");

    }

    int dash_calculator(String text)
    {
        int text_length = text.length();
        return DASH_CONSTANT - text_length;
    }

    void menu()
    {   
        String array[] = {
            "Menu here", "0. Exit", "1. Add your assets in [Equity, Gold, Real Estate, Debt]", 
            "2. Calculate your Net Worth in INR", "3. Calculate Risk Profile of your Portfolio in INR",
            "4. Calculate Overall Return of your Portfolio in %"
            };
        for (int ptr = 0;ptr < array.length; ptr++)
        {
            
            print_dash(DASH_CONSTANT - 20);

            System.out.print(array[ptr]);
            
            print_dash(dash_calculator(array[ptr]));

            System.out.println("");
        }
    }

}


class PortfolioManagement
{
    public static void main(String args[])
    {
        int choice;
        PortfolioBackend current_object = new PortfolioBackend();
        Scanner cin = new Scanner(System.in);
        while (true)
        {
            current_object.menu();
            System.out.print("Enter your choice: ");
            choice = cin.nextInt();
            System.out.println("");
            if (choice == 0)
            {
                break;
            }
            else if(choice == 1)
            {
                current_object.add_assets();
            }
            else if (choice == 2)
            {
                current_object.calculate_networth();
            }
            else if (choice == 3)
            {
                current_object.portfolio_risk();
            }
            else if (choice == 4)
            {
                current_object.overall_return();
            }

        }
    }
}