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
            String monthName = "Data_Files/" + months[month] + ".txt";  // This line helps to create name for finding the file.
            File monthlyFile = new File(monthName);  // This helps convert String to File.
            Scanner monthlyFileReader = null;  // This helps to scan.

            try {  // This try loop helps control existence of files.
                monthlyFileReader = new Scanner(monthlyFile);  // This code reads files.

                if (monthlyFileReader.hasNextLine()) {  // This code helps us a skipping first line because the first line in every file is "Day,Commodity,Profit".
                    monthlyFileReader.nextLine();
                }

                while (monthlyFileReader.hasNextLine()) {  // This helps to read file until file is finished.
                    String monthLine = monthlyFileReader.nextLine();  // This line help converting datas to String.

                    Scanner monthLineScanner = new Scanner(monthLine);
                    String[] parts = monthLine.split(",");  // This line helps us for split operation.

                    if (monthLineScanner.hasNext()) {
                        int day = monthLineScanner.nextInt();
                        String commodityName =  monthLineScanner.next();
                        int profit = monthLineScanner.nextInt();
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
                    monthLineScanner.close();  // This helps to close montLineScanner for clear the memory.
                }
            } catch (FileNotFoundException E) {  // This helps to program shouldn't crash if the file is missing.
                System.out.println("Error: " + monthlyFile + " not found. Skipping this month.");  // This helps to print error message.
            } if (monthlyFileReader != null) {  // This helps to check scanner status.
                monthlyFileReader.close();
            }
        }
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
        int maxProfit = Integer.MIN_VALUE;
        int mpCommodityIndex = -1;

        for (int i = 0; i < COMMS; i++) {  // This line checks every commodity one by one.
            int sum = 0;
            for (int j = 0; j < DAYS; j++) {  // This line checks every day in one month one by one.
                sum += profitData[month][i][j];
            }
            if (sum > maxProfit) {  // This checks whether the situation is more profitable than before.
                maxProfit = sum;
                mpCommodityIndex = i;
            }
        }
        return "DUMMY";
    }

    public static int totalProfitOnDay(int month, int day) {
        int tProfit = 0;
        int d = day - 1;  // This line converts datas to array index.

        for (int i = 0; i < COMMS; i++) {
            tProfit += profitData[month][d][i];
        }
        return 1234;
    }

    public static int commodityProfitInRange(String commodity, int from, int to) {
        return 1234;
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