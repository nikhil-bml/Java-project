package exceptions;

public class NegativeWalletBalance extends Exception
{
    public NegativeWalletBalance()
    {
        super("Negative Wallet Balance is not allowed");
    }
}