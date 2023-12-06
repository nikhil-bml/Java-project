import reader.ReadUser;
import user.User;
import user.UserInfo;
import reader.WriteCsv;
import user.AdminInfo;
import reader_db.WriteDB;
import user.Admin;
import reader.ReadAdmin;

class temp
{
    public static void main(String args[])
    {
        // Admin admins = new Admin();
        // admins.registration("nikhil","tomar");
        // AdminInfo admin = admins.get_admin("nikhil");
        // System.out.println(admin.username);
        // WriteCsv ob = new WriteCsv();
        // ob.write_admin_info(admin);
        // ReadAdmin read_filed_admins = new ReadAdmin();
        // Admin new_admins = new Admin();
        // read_filed_admins.read_admin_info(new_admins);
        WriteDB write = new WriteDB(
            "jdbc:postgresql://localhost:5432/portfolio_management",
            "postgres",
            "123456789"
        );
        write.delete_entry("eqiuty","something");
    }
}

