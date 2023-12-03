import reader.ReadUser;
import user.User;
import user.UserInfo;
import reader.WriteCsv;
class temp
{
    public static void main(String args[])
    {
        // ReadUser all_user_obj = new ReadUser();
        // User user_inst = new User();
        // all_user_obj.read_user_info(user_inst);
        // WriteCsv write_obj = new WriteCsv();
        // write_obj.remove_user_info(user_inst, "a");
        UserInfo curr_user = new UserInfo("abcde","mokas",20);
        curr_user.wallet_status(10);
    }
}