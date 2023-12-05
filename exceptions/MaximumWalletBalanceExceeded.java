package exceptions;
public class MaximumWalletBalanceExceeded extends Exception
{
    public MaximumWalletBalanceExceeded()
    {
        super("Maximum Wallet Balance Allowed is Exceeded, Please choose a acceptable amount");
    }
}