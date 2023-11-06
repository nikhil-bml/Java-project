package reader;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;

public class ReadCsv {
    public ArrayList<ArrayList<Object>> total_data_list;
    int owned_equity_assets[], owned_equity_assets_quantity[];
    int rows;
    
    public ReadCsv(String csvFile)
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

    public void print(String option)
    {
        if (option.equals("name"))
        {
            for (int i =0; i<total_data_list.size(); i++)
            {
                System.out.print(i + ". ");
                System.out.println(total_data_list.get(i).get(0));
            }
        }
        else if (option.equals("value"))
        {
            for (int i =0; i<total_data_list.size(); i++)
            {
                System.out.println(total_data_list.get(i).get(1));
            }
        }
        else if (option.equals("return"))
        {
            for (int i =0; i<total_data_list.size(); i++)
            {
                System.out.println(total_data_list.get(i).get(2));
            }
        }
        else if (option.equals("all"))
        {
            for (int i=0; i<total_data_list.size(); i++)
            {
                for (int l=0 ;l< total_data_list.get(i).size(); l++)
                {
                    System.out.print(total_data_list.get(i).get(l));
                    if (l != total_data_list.get(i).size() -1)
                    {
                        System.out.print(", ");
                    }
                }
                System.out.println();
            }
        }

    }
    
}
