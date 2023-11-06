package reader;
import java.io.BufferedReader;
import java.io.FileReader;

public class ReadCsv {
    public double total_data_list[][];
    String total_data_names[];
    int owned_equity_assets[], owned_equity_assets_quantity[];
    int rows;
    
    public ReadCsv(String csvFile)
    {
        try {
            BufferedReader file_row = new BufferedReader(new FileReader(csvFile));
            int rows = 0;
            while(file_row.ready())
            {
                file_row.readLine();
                rows += 1;
            }

            total_data_list = new double[rows][3];
            total_data_names = new String[rows]; 

            BufferedReader file_read = new BufferedReader(new FileReader(csvFile));
            int name_row_ptr = 0, data_row_ptr = 0;

            while (file_read.ready()) {
                double temporary_data_list[] = new double[3];

                String temp_list[] = file_read.readLine().split(",");
                total_data_names[name_row_ptr] = temp_list[0];

                for(int i=1; i<4; i++)
                {
                    temporary_data_list[i - 1] = Double.parseDouble(temp_list[i]);
                }
                
                total_data_list[data_row_ptr] = temporary_data_list;

                data_row_ptr += 1;
                name_row_ptr += 1;

            }

        } catch (Exception e){
            System.out.println("Cannot find the file specified");
        }
    }

    public void print()
    {
        
        for (int i =0; i<total_data_names.length; i++)
        {
            System.out.print(i + ". ");
            System.out.println(total_data_names[i]);
        }
    }
    
}
