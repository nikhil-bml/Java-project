package user;
import user.UserInfo;

public class User
{
    static UserInfo users[];
    static int users_size;

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

    public static void registration(String username, String password)
    {
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
    public static int login(String username, String password)
    {
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
}