import java.util.Scanner;
import user.User;
import reader.ReadCsv;
import logic.Logic;
import user.UserInfo;
import user.Admin;
import user.AdminInfo;
import reader.ReadAdmin;
import reader.ReadUser;
import reader.WriteCsv;
import reader_db.WriteDB;
import exceptions.NegativeWalletBalance;
import exceptions.MaximumWalletBalanceExceeded;

interface PortfolioBackend
{
    
    static int take_any_input(Scanner sc)
    {       
        try
        {
            int input = Integer.parseInt(sc.nextLine());
            return input;
        }
        catch (NumberFormatException e)
        {
            System.out.println("");
            System.out.println("Caught " + e);
            System.out.println("Please Try again");
            return Integer.MAX_VALUE;
        }
    }
    
    static void main_menu()
    {   
        String TITLES[] = {
            "1. Add your assets in [Equity, Real Estate, Debt]", 
            "2. Calculate Net Worth in INR", 
            "3. Calculate Risk Profile",
            "4. Calculate Returns", 
            "5. Save Your Information",
            "6. Wallet Operations",
            "-1. Logout"
            };

        for (String title: TITLES)
        {
            System.out.println(title);
        }

    }

    static void main_asset_menu()
    {
        String TITLES[] = {
            "1. Add Equity Assets", 
            "2. Add Real Estate Assets", 
            "3. Add Debt Assets", 
            "-1. Back"
            };

        for (String title: TITLES)
        {
            System.out.println(title);
        }              
    }

    static void specific_asset_menu(String asset_type, Logic assets)
    {
        if (asset_type.equals("equity"))
        {
            assets.all_equity_assets.print();
        }   
        else if (asset_type.equals("debt"))
        {
            assets.all_debt_assets.print();
        }   
        else if (asset_type.equals("real_estate"))
        {
            assets.all_real_estate_assets.print();
        }
        System.out.println("-1. Back");
    }
    
    static void main_worth_risk_return_menu()
    {
        String TITLES[] = {
            "1. Equity", 
            "2. Debt", 
            "3. Real Estate", 
            "4. All Portfolio",
            "-1. Back"
            };

        for (String title: TITLES)
        {
            System.out.println(title);
        }              

    }

    static int[] normalise_arraylist(int array[], int filled)
    {
        if (filled == 0)
        {
            return new int[]{};
        }

        int temp_array[] = new int[filled];

        for (int i=0; i<filled; i++)
        {
            temp_array[i] = array[i];
        }

        return temp_array;
    }

    static void main_user_menu()
    {
        String TITLES[] = {
            "Main Menu",
            "1. Register", 
            "2. Login",  
            "3. Admin Register",
            "4. Admin Login",
            "-1. Exit"
        };

        for (String title: TITLES)
        {
            System.out.println(title);
        }              

    }

    static void main_wallet_menu()
    {
        String TITLES[] = {
            "1. Add funds to the wallet", 
            "2. View funds in wallet",  
            "-1. Back"
        };

        for (String title: TITLES)
        {
            System.out.println(title);
        }              

    }
    static void main_admin_menu()
    {
        String TITLES[] = {
            "1. Add New Assets[Equity, Debt, Real Estate]",
            "2. Update Assets",
            "3. Delete Assets",
            "4. Save your(Admin) Information",
            "-1. Logout"
        };

        for (String title: TITLES)
        {
            System.out.println(title);
        }              
    }
    static void admin_asset_menu()
    {
        String TITLES[] = {
            "1. Add Equity Asset",
            "2. Add Debt Asset",
            "3. Add Real Estate Asset",
            "-1. Go Back"
        };

        for (String title: TITLES)
        {
            System.out.println(title);
        }              
    }
    static void admin_delete_menu()
    {
        String TITLES[] = {
            "1. Delete Equity Asset",
            "2. Delete Debt Asset",
            "3. Delete Real Estate Asset",
            "-1. Go Back"
        };

        for (String title: TITLES)
        {
            System.out.println(title);
        }              

    }


}

class PortfolioMenu
{
    public static void main(String args[])
    {
        UserInfo current_user = null;
        AdminInfo current_admin = null;
        Logic assets = new Logic();
        int choice, logged_in = 0;
        Scanner sc = new Scanner(System.in);
        User users = new User();
        Admin admins = new Admin();
        ReadUser read_filed_users = new ReadUser();
        WriteCsv write_filed_users = new WriteCsv();
        WriteCsv write_filed_admins = new WriteCsv();
        ReadAdmin read_filed_admins = new ReadAdmin();
        WriteDB write_db_object = new WriteDB(
            "jdbc:postgresql://localhost:5432/portfolio_management",
            "postgres",
            "123456789"
        );
        read_filed_admins.read_admin_info(admins);
        read_filed_users.read_user_info(users);

        while (true)
        {
            assets.read_database();

            PortfolioBackend.main_user_menu();
            System.out.print("Enter your choice: ");
            choice = PortfolioBackend.take_any_input(sc);

            if (choice == -1)
            {
                break;
            }

            else if(choice == 1)
            {
                String username, password;
                System.out.print("Enter your username: ");
                username = sc.nextLine();

                System.out.print("Enter your password: ");
                password = sc.nextLine();
                users.registration(username, password);
            }

            else if(choice == 2)
            {
                String username, password;
                System.out.print("Enter your username: ");
                username = sc.nextLine();

                System.out.print("Enter your password: ");
                password = sc.nextLine();
                logged_in = users.login(username, password);
                if (logged_in == 1)
                {
                    current_user = users.get_user(username);
                    System.out.println();
                    System.out.println("Welcome to our Portfolio Management System");  
                    System.out.println();          

                    while (current_user != null)
                    {
                        assets.set_assets(current_user.owned_assets_equity, current_user.owned_assets_equity_quantity, "equity");    
                        assets.set_assets(current_user.owned_assets_debt, current_user.owned_assets_debt_quantity, "debt");
                        assets.set_assets(current_user.owned_assets_real_estate, current_user.owned_assets_real_estate_quantity, "real_estate");

                        PortfolioBackend.main_menu();
                        System.out.print("Enter your choice: ");
                        choice = PortfolioBackend.take_any_input(sc);
                        System.out.println("");

                        if (choice == -1)
                        {
                            current_user = null;
                        }
                        else if(choice == 1)
                        {
                            while (true)
                            {
                                int owned_assets[] = new int[10]; 
                                int owned_assets_quantity[] = new int[10];
                                int filled = 0;
                                PortfolioBackend.main_asset_menu();
                                System.out.print("Enter your choice: ");
                                choice = PortfolioBackend.take_any_input(sc);

                                if (choice == -1)
                                {
                                    System.out.println();
                                    break;
                                }
                                else if(choice == 1)
                                {
                                    System.out.println();
                                    double wallet = current_user.wallet;
                                    double total_worth = 0;
                                    while (true)
                                    {
                                        PortfolioBackend.specific_asset_menu("equity", assets);
                                        System.out.print("Enter your choice: ");
                                        choice = PortfolioBackend.take_any_input(sc);
                                        
                                        if (choice == -1)
                                        {
                                            System.out.println("");
                                            
                                            current_user.set_user_assets(
                                                            PortfolioBackend.normalise_arraylist(owned_assets, filled),
                                                            PortfolioBackend.normalise_arraylist(owned_assets_quantity, filled),
                                                            "equity"
                                                            );
                                            current_user.wallet -= total_worth;

                                            break;
                                        }
                                        else if (choice >= assets.all_equity_assets.total_data_list.length)
                                        {
                                            System.out.println("Choice Invalid");
                                            System.out.println("");
                                            break;
                                        }
                                        else
                                        {
                                            int quantity; 
                                            System.out.print("Enter the quantity: ");
                                            quantity = PortfolioBackend.take_any_input(sc);
                                            int temp_owned_assets[] = owned_assets;
                                            int temp_owned_assets_quantity[] = owned_assets_quantity;
                                            int temp_filled = filled; 
                                            temp_owned_assets[temp_filled] = choice;
                                            temp_owned_assets_quantity[temp_filled] = quantity;
                                            temp_filled += 1;
                                            assets.set_assets(
                                                PortfolioBackend.normalise_arraylist(temp_owned_assets, temp_filled),
                                                PortfolioBackend.normalise_arraylist(temp_owned_assets_quantity, temp_filled),
                                                "equity"
                                            );

                                            total_worth = assets.get_asset_info("equity", "worth");
                                            if (wallet - total_worth < 0)
                                            {
                                                System.out.println("Low Funds, Please Add funds before adding real estate assets");
                                                break;
                                            }
                                            System.out.println();
                                            owned_assets[filled] = choice;
                                            owned_assets_quantity[filled] = quantity;
                                            filled += 1;
                                        }

                                    }
                                }

                                else if(choice == 2)
                                {
                                    System.out.println();
                                    double wallet = current_user.wallet;
                                    double total_worth = 0;


                                    while (true)
                                    {
                                        PortfolioBackend.specific_asset_menu("real_estate", assets);
                                        System.out.print("Enter your choice: ");
                                        choice = PortfolioBackend.take_any_input(sc);
                                        
                                        if (choice == -1)
                                        {
                                            System.out.println("");
                                            
                                            current_user.set_user_assets(
                                                            PortfolioBackend.normalise_arraylist(owned_assets, filled),
                                                            PortfolioBackend.normalise_arraylist(owned_assets_quantity, filled),
                                                            "real_estate"
                                                            );
                                            current_user.wallet -= total_worth;
                                            break;
                                        }
                                        else if (choice >= assets.all_real_estate_assets.total_data_list.length)
                                        {
                                            System.out.println("Choice Invalid");
                                            System.out.println("");
                                            break;
                                        }
                                        else
                                        {
                                            int quantity; 
                                            System.out.print("Enter the land in square metre: ");
                                            quantity = PortfolioBackend.take_any_input(sc);
                                            int temp_owned_assets[] = owned_assets;
                                            int temp_owned_assets_quantity[] = owned_assets_quantity;
                                            int temp_filled = filled; 
                                            temp_owned_assets[temp_filled] = choice;
                                            temp_owned_assets_quantity[temp_filled] = quantity;
                                            temp_filled += 1;
                                            assets.set_assets(
                                                PortfolioBackend.normalise_arraylist(temp_owned_assets, temp_filled),
                                                PortfolioBackend.normalise_arraylist(temp_owned_assets_quantity, temp_filled),
                                                "real_estate"
                                            );

                                            total_worth = assets.get_asset_info("real_estate", "worth");
                                            if (wallet - total_worth < 0)
                                            {
                                                System.out.println("Low Funds, Please Add funds before adding real estate assets");
                                                break;
                                            }

                                            System.out.println();
                                            owned_assets[filled] = choice;
                                            owned_assets_quantity[filled] = quantity;
                                            filled += 1;
                                        }

                                    }
                                }

                                else if(choice == 3)
                                {
                                    double wallet = current_user.wallet;
                                    double total_worth = 0;

                                    System.out.println();

                                    while (true)
                                    {
                                        PortfolioBackend.specific_asset_menu("debt", assets);
                                        System.out.print("Enter your choice: ");
                                        choice = PortfolioBackend.take_any_input(sc);
                                        
                                        if (choice == -1)
                                        {
                                            System.out.println("");
                                            
                                            current_user.set_user_assets(
                                                            PortfolioBackend.normalise_arraylist(owned_assets, filled),
                                                            PortfolioBackend.normalise_arraylist(owned_assets_quantity, filled),
                                                            "debt"
                                                            );
                                            current_user.wallet -= total_worth;

                                            break;
                                        }
                                        else if (choice >= assets.all_debt_assets.total_data_list.length)
                                        {
                                            System.out.println("Choice Invalid");
                                            System.out.println("");
                                            break;
                                        }
                                        else
                                        {
                                            int quantity; 
                                            System.out.print("Enter the quantity: ");
                                            quantity = PortfolioBackend.take_any_input(sc);
                                            int temp_owned_assets[] = owned_assets;
                                            int temp_owned_assets_quantity[] = owned_assets_quantity;
                                            int temp_filled = filled; 
                                            temp_owned_assets[temp_filled] = choice;
                                            temp_owned_assets_quantity[temp_filled] = quantity;
                                            temp_filled += 1;
                                            assets.set_assets(
                                                PortfolioBackend.normalise_arraylist(temp_owned_assets, temp_filled),
                                                PortfolioBackend.normalise_arraylist(temp_owned_assets_quantity, temp_filled),
                                                "debt"
                                            );

                                            total_worth = assets.get_asset_info("debt", "worth");
                                            if (wallet - total_worth < 0)
                                            {
                                                System.out.println("Low Funds, Please Add funds before adding equity assets");
                                                break;
                                            }

                                            System.out.println();
                                            owned_assets[filled] = choice;
                                            owned_assets_quantity[filled] = quantity;
                                            filled += 1;
                                        }

                                    }
                                }

                            }
                        }
                        else if (choice == 2)
                        {
                            while (true)
                            {
                                PortfolioBackend.main_worth_risk_return_menu();
                                System.out.print("Enter your choice: ");
                                choice = PortfolioBackend.take_any_input(sc);

                                if (choice == -1)
                                {
                                    System.out.println();
                                    break;
                                }
                                
                                else if (choice == 1)
                                {
                                    System.out.println();
                                    System.out.println("worth of Equity is INR " + assets.get_asset_info("equity","worth"));
                                    System.out.println();
                                }
                                else if (choice == 2)
                                {
                                    System.out.println();
                                    System.out.println("worth of Debt is INR " + assets.get_asset_info("debt","worth"));
                                    System.out.println();
                                }
                                else if (choice == 3)
                                {
                                    System.out.println();
                                    System.out.println("worth of Real Estate is INR " + assets.get_asset_info("real_estate","worth"));
                                    System.out.println();
                                }
                                else if (choice == 4)
                                {
                                    System.out.println();
                                    System.out.println("worth of Entire Portfolio is INR " + assets.total_assets_info("worth"));
                                    System.out.println();
                                }
                                else
                                {
                                    System.out.println();
                                    System.out.println("Choice Invalid");
                                    System.out.println();
                                }
                            }
                        }
                        else if (choice == 3)
                        {
                            while (true)
                            {
                                PortfolioBackend.main_worth_risk_return_menu();
                                System.out.print("Enter your choice: ");
                                choice = PortfolioBackend.take_any_input(sc);

                                if (choice == -1)
                                {
                                    System.out.println();
                                    break;
                                }
                                
                                else if (choice == 1)
                                {
                                    System.out.println();
                                    System.out.println("risk of Equity " + assets.get_asset_info("equity","risk"));
                                    if (assets.get_asset_info("equity", "risk") > 0)
                                    {
                                        System.out.println("This investment is of " + assets.portfolio_risk(assets.get_asset_info("equity","risk")));
                                    }
                                    System.out.println();
                                }
                                else if (choice == 2)
                                {
                                    System.out.println();
                                    System.out.println("risk of Debt " + assets.get_asset_info("debt","risk"));
                                    if (assets.get_asset_info("debt", "risk") > 0)
                                    {
                                        System.out.println("This investment is of " + assets.portfolio_risk(assets.get_asset_info("debt","risk")));
                                    }

                                    System.out.println();
                                }
                                else if (choice == 3)
                                {
                                    System.out.println();
                                    System.out.println("risk of Real Estate " + assets.get_asset_info("real_estate","risk"));
                                    if (assets.get_asset_info("real_estate", "risk") > 0)
                                    {
                                        System.out.println("This investment is of " + assets.portfolio_risk(assets.get_asset_info("real_estate","risk")));
                                    }

                                    System.out.println();
                                }
                                else if (choice == 4)
                                {
                                    System.out.println();
                                    System.out.println("risk of Entire Portfolio is " + assets.total_assets_info("risk"));
                                    if (assets.total_assets_info("risk") > 0)
                                    {
                                        System.out.println("This investment is of " + assets.portfolio_risk(assets.total_assets_info("risk")));
                                    }

                                    System.out.println();
                                }
                            }

                        }
                        else if (choice == 4)
                        {
                            while (true)
                            {
                                PortfolioBackend.main_worth_risk_return_menu();
                                System.out.print("Enter your choice: ");
                                choice = PortfolioBackend.take_any_input(sc);

                                if (choice == -1)
                                {
                                    System.out.println();
                                    break;
                                }
                                
                                else if (choice == 1)
                                {
                                    System.out.println();
                                    System.out.println("returns of Equity in % " + assets.get_asset_info("equity","return"));
                                    System.out.println();
                                }
                                else if (choice == 2)
                                {
                                    System.out.println();
                                    System.out.println("returns of Debt in % " + assets.get_asset_info("debt","return"));
                                    System.out.println();
                                }
                                else if (choice == 3)
                                {
                                    System.out.println();
                                    System.out.println("returns of Real Estate in % " + assets.get_asset_info("real_estate","return"));
                                    System.out.println();
                                }
                                else if (choice == 4)
                                {
                                    System.out.println();
                                    System.out.println("returns of Entire Portfolio in % " + assets.total_assets_info("return"));
                                    System.out.println();
                                }
                            }

                        }
                        else if (choice == 5)
                        {
                            write_filed_users.remove_user_info(current_user.username);
                            write_filed_users.write_user(current_user);
                            write_filed_users.write_user_holdings(current_user);
                            System.out.println("Your profile is saved you can Exit Safely");
                            System.out.println();
                        }

                        else if (choice == 6)
                        {
                            while (true)
                            {
                                PortfolioBackend.main_wallet_menu();
                                System.out.print("Enter your choice: ");
                                choice = PortfolioBackend.take_any_input(sc);

                                if (choice == -1)
                                {
                                    System.out.println();
                                    break;
                                }
                                
                                else if (choice == 1)
                                {
                                    try
                                    {
                                        Double amount;
                                        System.out.print("Enter the amount to load: ");
                                        amount = Double.parseDouble(sc.nextLine());
                                        if (amount > 1000000)
                                        {
                                            throw new MaximumWalletBalanceExceeded();
                                        }
                                        if (amount < 0)
                                        {
                                            throw new NegativeWalletBalance();
                                        }
                                        current_user.wallet += amount;
                                        System.out.println("Value loaded into wallet successfully");
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println(e.getMessage());
                                    }

                                }
                                else if (choice == 2)
                                {
                                    System.out.println();
                                    System.out.println("Your Balance is " + current_user.wallet);
                                    System.out.println();
                                }
                            }
                        }

                        else 
                        {
                            System.out.println();
                            System.out.println("Choice Invalid, Please Try again from the options below");
                            System.out.println();
                        }

                    }
                }
            }

            else if(choice == 3)
            {
                String username, password;
                System.out.print("Enter your username: ");
                username = sc.nextLine();

                System.out.print("Enter your password: ");
                password = sc.nextLine();
                admins.registration(username, password);

            }

            else if(choice == 4)
            {
                String username, password;
                System.out.print("Enter your username: ");
                username = sc.nextLine();

                System.out.print("Enter your password: ");
                password = sc.nextLine();
                logged_in = admins.login(username, password);
                if (logged_in == 1)
                {
                    current_admin = admins.get_admin(username);
                    System.out.println();
                    System.out.println("Welcome to our Admin Page");  
                    System.out.println();
                    while (current_admin != null)
                    {
                        PortfolioBackend.main_admin_menu();
                        System.out.print("Enter your choice: ");
                        choice = PortfolioBackend.take_any_input(sc);

                        if (choice == -1)
                        {
                            current_admin = null;
                        }
                        else if(choice == 1)
                        {
                            while(true)
                            {
                                PortfolioBackend.admin_asset_menu();
                                System.out.print("Enter your choice: ");
                                choice = PortfolioBackend.take_any_input(sc);

                                if (choice == -1)
                                {
                                    break;
                                }

                                else if(choice == 1)
                                {
                                    String name;
                                    double price, risk, returns;
                                    try
                                    {
                                        System.out.print("Enter name for new Equity asset: ");
                                        name = sc.nextLine();
                                        System.out.print("Enter price for new Equity asset: ");
                                        price = Double.parseDouble(sc.nextLine());
                                        System.out.print("Enter risk for new Equity asset: ");
                                        risk = Double.parseDouble(sc.nextLine());
                                        System.out.print("Enter returns for new Equity asset: ");
                                        returns = Double.parseDouble(sc.nextLine());
                                        write_db_object.add_new_entry(
                                            "equity",
                                            name,
                                            price,
                                            risk,
                                            returns
                                            );
                                        System.out.println("Your new equity asset is created Successfully");
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println("Wrong input try again");
                                    }
                                    System.out.println();
                                }
                                else if(choice == 2)
                                {
                                    String name;
                                    double price, risk, returns;
                                    try
                                    {
                                        System.out.print("Enter name for new Debt asset: ");
                                        name = sc.nextLine();
                                        System.out.print("Enter price for new Debt asset: ");
                                        price = Double.parseDouble(sc.nextLine());
                                        System.out.print("Enter risk for new Debt asset: ");
                                        risk = Double.parseDouble(sc.nextLine());
                                        System.out.print("Enter returns for new Debt asset: ");
                                        returns = Double.parseDouble(sc.nextLine());
                                        write_db_object.add_new_entry(
                                            "debt",
                                            name,
                                            price,
                                            risk,
                                            returns
                                            );
                                        System.out.println("Your new debt asset is created Successfully");
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println("Wrong input try again");
                                    }
                                    System.out.println();
                                }
                                else if(choice == 3)
                                {
                                    String name;
                                    double price, risk, returns;
                                    try
                                    {
                                        System.out.print("Enter name for new Real Estate asset: ");
                                        name = sc.nextLine();
                                        System.out.print("Enter price for new Real Estate asset: ");
                                        price = Double.parseDouble(sc.nextLine());
                                        System.out.print("Enter risk for new Real Estate asset: ");
                                        risk = Double.parseDouble(sc.nextLine());
                                        System.out.print("Enter returns for new Real Estate asset: ");
                                        returns = Double.parseDouble(sc.nextLine());
                                        write_db_object.add_new_entry(
                                            "real_estate",
                                            name,
                                            price,
                                            risk,
                                            returns
                                            );
                                        System.out.println("Your new Real Estate asset is created Successfully");
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println("Wrong input try again");
                                    }
                                    System.out.println();
                                }

                            }                            
                        }
                        else if(choice == 3)
                        {
                            while(true)
                            {
                                PortfolioBackend.admin_delete_menu();
                                System.out.print("Enter your choice: ");
                                choice = PortfolioBackend.take_any_input(sc);

                                if (choice == -1)
                                {
                                    break;
                                }
                                else if (choice == 1)
                                {
                                    String name;
                                    try
                                    {
                                        System.out.print("Enter name for equity asset to be deleted: ");
                                        name = sc.nextLine();
                                        write_db_object.delete_entry("equity", name);
                                        System.out.println("Your new equity asset is Deleted Successfully");
                                    }
                                    catch(Exception e)
                                    {
                                        System.out.println("Wrong input please try again");
                                    }
                                }
                            }
                        }
                        else if(choice == 4)
                        {
                            write_filed_admins.remove_admin_info(current_admin.username);
                            write_filed_admins.write_admin_info(current_admin);
                            System.out.println("Your Admin profile is saved you can Exit Safely");
                            System.out.println();
                        }
                        else
                        {
                            System.out.println();
                            System.out.println("Choice Invalid, Please Try again");
                            System.out.println();

                        }
                    }          
                }      
            }
            else
            {
                System.out.println();
                System.out.println("Choice Invalid, Please Try again");
                System.out.println();
            }

        }

    }
}