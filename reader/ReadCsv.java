// package reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

abstract class Base {
    ArrayList<ArrayList<Object>> total_data_list;
    int owned_equity_assets[], owned_equity_assets_quantity[];
    int rows;
    
    Base(String csvFile)
    {
        try {
            total_data_list = new ArrayList<>();

            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            while (br.ready()) {
                ArrayList<Object> curr_data_list = new ArrayList<>();

                String temp_list[] = br.readLine().split(",");
                for(int i=0; i<4; i++)
                {
                    if (i==0)
                    {
                        curr_data_list.add((String)temp_list[0]);
                    }
                    else
                    {
                        curr_data_list.add(Double.parseDouble(temp_list[i]));
                    }


                }
                
                total_data_list.add(curr_data_list);
            }

        } catch (IOException e) {
            System.out.println("Cannot find the file specified");
        }
    }

    void print()
    {
        System.out.println(total_data_list);
    }
    
}

class EquityCSV extends Base {
    
    EquityCSV(String csvFile) 
    {
        super(csvFile);
    }

    EquityCSV() 
    {
        super("data/equity.csv");
    }

}

class DebtCSV extends Base {

    DebtCSV(String csvFile) 
    {
        super(csvFile);
    }
    DebtCSV() 
    {
        super("data/debt.csv");
    }

}

class RealEstate extends Base {

    RealEstate(String csvFile) 
    {
        super(csvFile);
    }
    RealEstate() 
    {
        super("data/real_estate.csv");
    }
}

class ReadCsv {
    public static void main(String[] args) 
    {
        EquityCSV ob = new EquityCSV();
    }
}
