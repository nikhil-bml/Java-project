import java.util.Scanner;

class PortfolioBackend
{
    double equity_assets, debt_assets, gold_assets, real_estate_assets, EQUITY_STD, GOLD_STD, DEBT_STD, REAL_ESTATE_STD;
    PortfolioBackend(){
        EQUITY_STD = 15.08;
        DEBT_STD = 0.94;
        GOLD_STD = 11.4;
        REAL_ESTATE_STD = 15.94;
        equity_assets = 0;
        debt_assets = 0;
        gold_assets = 0;
        real_estate_assets = 0;
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
        System.out.println(equity_assets + debt_assets + gold_assets + real_estate_assets);
    }

    void menu()
    {   
        System.out.println("");
        System.out.println("Menu is available below: ");
        System.out.println("0. Exit");
        System.out.println("1. Add your assets in [Equity, Gold, Real Estate, Debt]");
        System.out.println("2. Calculate your Net Worth in INR");
        System.out.println("");
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
        }
    }
}