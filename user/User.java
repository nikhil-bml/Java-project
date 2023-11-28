package user;
import user.UserInfo;

public class User
{
    public UserInfo users[];
    int users_size;

    public User()
    {
        users = new UserInfo[10];
        users_size = 0;
    }

    public UserInfo get_user(String username)
    {
        for (int i=0; i<users_size; i++)
        {
            if (users[i].username.equals(username))
            {
                return users[i];
            }
        }

        System.out.println("User does not exist");
        return null;
    }

    public void registration(String username, String password)
    {
        if (password.length() == 0)
        {
            System.out.println("Password cannot be empty");
            return;
        }
        if (username.length() == 0)
        {
            System.out.println("Username cannot be empty");
            return;
        }
        
        for (int i=0; i<users_size; i++)
        {
            if (users[i].username.equals(username))
            {
                System.out.println("This username already exists");
                return;
            }
        }

        UserInfo user = new UserInfo(username, password);
        users[users_size] = user;
        users_size += 1;
        System.out.println(username + " Your are succesfully Registered on our System, Please Login");

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

        for (int i=0; i<users_size; i++)
        {
            if (users[i].username.equals(username))
            {
                if (users[i].password.equals(password))
                {
                    return 1; 
                }
                System.out.println("Wrong password entered");
                return 0;
            }
        }
        System.out.println("User not found, Please register first");
        return 0;
   
    }

    public void user_file_registration(String username, String password)
    {
        UserInfo user = new UserInfo(username, password);
        users[users_size] = user;
        users_size += 1;
    }
}