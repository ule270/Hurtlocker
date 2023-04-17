import javax.management.AttributeNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    private int errCount = 0;
    public int getErrCount(){
        return errCount;
    }

    private String fieldPattern = "([A-Za-z0-9.]+)";
    //checks for all letters whether upper or lower, digits, and periods.
    private String stringSplitPattern = "([;:^@%*!])";
    //these are what splits the strings, whether name, price, type, expiration;
    private String itemSplitPattern = "((##))";
    //this splits the entire item;

    private Pattern fieldName = Pattern.compile(fieldPattern);
    Pattern split = Pattern.compile(stringSplitPattern);
    Pattern itemSplit = Pattern.compile(itemSplitPattern);
    private ArrayList<Item> listItems = new ArrayList<>();
    public ArrayList<Item> getListItems(){
        return listItems;
    }

    public String[] stringSplit(String string) {
        String[] splitStrings = string.split(stringSplitPattern);
        //splitting the strings based on the ;@% etc;
        return splitStrings;
    }

    public String countItems(String string){
        StringBuilder chart = new StringBuilder();
        LinkedHashMap<String, Integer> priceCount = new LinkedHashMap<>();
        int strCount = 0;
        for (Item i: listItems){
            if(i.getName().equals(string)){
                strCount ++;
                if (priceCount.containsKey(i.getPrice())){
                    priceCount.put(i.getPrice(), priceCount.get(i.getPrice())+1);
                } else if (i.getPrice().equals("blank")) {
                    strCount --;
                    continue;
                } else {
                    priceCount.put(i.getPrice(), 1);
                }
            }
        }
        chart.append("name:\t" + string + "\t\t" + "seen: " + strCount + " times\n");
        chart.append("=============\t\t=============\n");
        int counter  = 0;
        for(String key: priceCount.keySet()) {
            chart.append("Price:\t" + key + "\t\t" + "seen: " + priceCount.get(key) + " times");
            if(counter == 0) {
                chart.append("\n-------------\t\t-------------\n");
            }
            counter ++;
        }
        return chart.toString();
    }

    public void addItem(String name, String price, String type, String exp){
        listItems.add(new Item(name, price, type, exp));
    }

    public String nameClean(String string){
        if(string.toLowerCase().charAt(0) == 'm') {
            return "Milk";
        } else if (string.toLowerCase().charAt(0) == 'a') {
            return "Apples";
        } else if (string.toLowerCase().charAt(0) == 'b') {
            return "Bread";
        } else if (string.toLowerCase().charAt(0) == 'c') {
            return "Cookies";
        } else {
            return string;
        }
    }

    public String findItem(String string, int index) throws AttributeNotFoundException{
        try{
            Matcher mat = fieldName.matcher((stringSplit(string)[index]));
            if(mat.find()){
                return nameClean(mat.group());
            } else {
                errCount++;
                throw new AttributeNotFoundException();
            }
        } catch (AttributeNotFoundException e){
            System.err.println("Error was thrown: " + e);
            //System.err prints out standard error;
            return "blank";
        }
    }

    public void createItem(String input) throws AttributeNotFoundException {
        String[] itemArr = input.split(itemSplitPattern);
        for (int i = 0; i < itemArr.length; i++){
            addItem(findItem(itemArr[i], 1), findItem(itemArr[i], 3),
                    findItem(itemArr[i], 5), findItem(itemArr[i], 7));
        }
    }

    public String groceryListConstructor() {
        StringBuilder groceryList = new StringBuilder();
        groceryList.append(countItems("Milk"));
        groceryList.append("\n\n");
        groceryList.append(countItems("Bread"));
        groceryList.append("\n");
        groceryList.append(countItems("Cookies"));
        groceryList.append("\n");
        groceryList.append(countItems("Apples"));
        groceryList.append("\n\n");
        groceryList.append("Errors\t\t\tseen: " + errCount + " times");
        return groceryList.toString();
    }
}
