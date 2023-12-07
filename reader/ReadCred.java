package reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadCred {
    public String url, username, password;
    String credentials_list[];
    int rows;
    
    public ReadCred()
    {
        try 
        {
            BufferedReader file_read = new BufferedReader(new FileReader("reader/data/credentials.csv"));
            if (file_read.ready())
            {
                credentials_list = file_read.readLine().split(",");
                url = credentials_list[0];
                username = credentials_list[1];
                password = credentials_list[2];
            }
        }
        catch (java.io.FileNotFoundException e)
        {
            System.out.println("Cannot find the Credentials file");
        }
        catch(NullPointerException e)
        {
            System.out.println("Please add your credentials in the credentials.csv");
        }
        catch(java.io.IOException e)
        {
            System.out.println("IO Exception has occured");
        }
    }

}
