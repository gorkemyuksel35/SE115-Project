// Main.java — Students version
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;


public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
                              "July","August","September","October","November","December"};
    static int[][][] profitData = new int[MONTHS][DAYS][COMMS];
    

    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        for (int month = 0; month < MONTHS; month++) {  // All of the months are checking with this for loop.
            String monthName = "src/Data_Files/" + months[month] + ".txt";  // This line helps to create name for finding the file.
            File monthlyFile = new File(monthName);  // This helps convert String to File.
            Scanner monthlyFileReader = null;  // This helps to scan.

            try {  // This try loop helps control existence of files.
                monthlyFileReader = new Scanner(monthlyFile);  // This code reads files.

                if (monthlyFileReader.hasNextLine()) {  // This code helps us a skipping first line because the first line in every file is "Day,Commodity,Profit".
                    monthlyFileReader.nextLine();
                }

                while (monthlyFileReader.hasNextLine()) {  // This helps to read file until file is finished.
                    String monthLine = monthlyFileReader.nextLine();  // This line help converting datas to String.
                    String[] parts = monthLine.split(",");  // This line helps us for split operation.

                    int day = Integer.parseInt(parts[0]);
                    String commodityName = parts[1];
                    int profit = Integer.parseInt(parts[2]);

                    int commodityIndex = -1;  // This for unknown commodities.
                    for (int i = 0; i < COMMS; i++) {  // This for loop helps us for checking all commodities one by one.
                        if (commodityName.equals(commodities[i])) {  // This if loop checks equality of names.
                            commodityIndex = i;
                            break;
                        }
                    }

                    int dayIndex = day - 1;
                    if (dayIndex >= 0 && dayIndex < DAYS && commodityIndex != -1) {  // This is security check. This line helps to validate commodities and days.
                        profitData[month][dayIndex][commodityIndex] = profit;  // This helps to create profitData array with new datas.
                    }
                }
            } catch (FileNotFoundException E) {  // This helps to program shouldn't crash if the file is missing.
                System.out.println("Error: " + monthlyFile + " not found. Skipping this month.");  // This helps to print error message.
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format in file: " + monthName);
            } if (monthlyFileReader != null) {  // This helps to check scanner status.
                monthlyFileReader.close();
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        if (month < 0 || month >= MONTHS) {
            return "INVALID_MONTH";
        }
        int maxProfit = Integer.MIN_VALUE;
        int mPCIM_Index = -1;

        for (int i = 0; i < COMMS; i++) {  // This line checks every commodity one by one.
            int sum = 0;
            for (int j = 0; j < DAYS; j++) {  // This line checks every day in one month one by one.
                sum += profitData[month][j][i];
            }
            if (sum > maxProfit) {  // This checks whether the situation is more profitable than before.
                maxProfit = sum;
                mPCIM_Index = i;
            }
        }
        return commodities[mPCIM_Index] +" " + maxProfit;
    }

    public static int totalProfitOnDay(int month, int day) {
        if (month < 0 || month >= MONTHS || day < 1 || day > DAYS) {
            return -99999;
        }
        int tPOD_Index = 0;
        int d = day - 1;  // This line converts datas to array index.

        for (int i = 0; i < COMMS; i++) {
            tPOD_Index += profitData[month][d][i];
        }
        return tPOD_Index;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        int cPIR_Index = -1;
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equals(commodity)) {
                cPIR_Index = i;
                break;
            }
        }

        if (cPIR_Index == -1 || from < 1 || to > DAYS || from > to) {  // This line checks invalidities about file contents.
            return -99999;
        }

        int sum = 0;
        for (int i = 0; i < MONTHS; i++) {
            for (int j = from - 1; j <= to - 1; j++) {
                sum += profitData[i][j][cPIR_Index];
            }
        }
        return sum;
    }

    public static int bestDayOfMonth(int month) { 
        return 1234; 
    }
    
    public static String bestMonthForCommodity(String comm) { 
        return "DUMMY"; 
    }

    public static int consecutiveLossDays(String comm) { 
        return 1234; 
    }
    
    public static int daysAboveThreshold(String comm, int threshold) { 
        return 1234; 
    }

    public static int biggestDailySwing(int month) { 
        return 1234; 
    }
    
    public static String compareTwoCommodities(String c1, String c2) { 
        return "DUMMY is better by 1234"; 
    }
    
    public static String bestWeekOfMonth(int month) { 
        return "DUMMY"; 
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}