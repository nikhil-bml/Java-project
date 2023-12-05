package user;
import user.AdminInfo;

public class Admin
{
    public AdminInfo admins[];
    public int admins_size;

    public Admin()
    {
        admins = new AdminInfo[10];
        admins_size = 0;
    }

    public AdminInfo get_admin(String username)
    {
        for (int i=0; i<admins_size; i++)
        {
            if (admins[i].username.equals(username))
            {
                return admins[i];
            }
        }

        System.out.println("admin does not exist");
        return null;
    }

    public void registration(String username, String password)
    {
        if (password.length() == 0)
        {
            System.out.println("Password cannot be empty");
            return;
        }
        else if (username.length() == 0)
        {
            System.out.println("Username cannot be empty");
            return;
        }

        else if (username.length() < 5)
        {
            System.out.println("Username cannot be smaller than 5 words");
            return;
        }
        
        else if (password.length() < 5)
        {
            System.out.println("Password cannot be smaller than 5 words");
            return;
        }

        else if (username.equals(password))
        {
            System.out.println("Username and password cannot be same");
            return;
        }

        for (int i=0; i<admins_size; i++)
        {
            if (admins[i].username.equals(username))
            {
                System.out.println("This username already exists");
                return;
            }
        }

        AdminInfo user = new AdminInfo(username, password);
        admins[admins_size] = user;
        admins_size += 1;
        System.out.println(username + " Your are succesfully Registered as a Admin, Please Login");

    }
    public int login(String username, String password)
    {
        if (password.length() == 0)
        {
            System.out.println("Password cannot be empty");
            return 0;
        }
        if (username.length() == 0)
        {
            System.out.println("Username cannot be empty");
            return 0;
        }

        for (int i=0; i<admins_size; i++)
        {
            if (admins[i].username.equals(username))
            {
                if (admins[i].tries >= 5)
                {
                    System.out.println("You have more than 3 failed attemps, Please login later");
                    return 0;
                }
                else if (admins[i].password.equals(password))
                {
                    return 1; 
                }
                System.out.println("Wrong password entered");
                admins[i].tries++;
                return 0;
            }
        }
        System.out.println("User not found, Please register first");
        return 0;
   
    }
    public void admin_file_registration(String username, String password)
    {
        AdminInfo admin = new AdminInfo(username, password);
        admins[admins_size] = admin;
        admins_size += 1;
    }
}