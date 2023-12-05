package user;

public class AdminInfo
{
    public String username, password;
    public int tries;

    public AdminInfo(String username, String password)
    {
        this.username = username;
        this.password = password;
        tries = 0;
    }

}