import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemParser {
    public static void main(String[] args){
        String inputData ="naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##\n" +
                "naME:BreaD;price:1.23;type:Food;expiration:1/02/2016##\n" +
                "NAMe:BrEAD;price:1.23;type:Food;expiration:2/25/2016##\n" +
                "naMe:MiLK;price:3.23;type:Food^expiration:1/11/2016##\n" +
                "naMe:Cookies;price:2.25;type:Food%expiration:1/25/2016##\n" +
                "naMe:CoOkieS;price:2.25;type:Food*expiration:1/25/2016##\n" +
                "naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##\n" +
                "naMe:COOkieS;price:2.25;type:Food;expiration:1/25/2016##\n" +
                "NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##\n" +
                "naMe:MilK;price:1.23;type:Food!expiration:4/25/2016##\n" +
                "naMe:apPles;price:0.25;type:Food;expiration:1/23/2016##\n" +
                "naMe:apPles;price:0.23;type:Food;expiration:5/02/2016##\n" +
                "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##\n" +
                "naMe:;price:3.23;type:Food;expiration:1/04/2016##\n" +
                "naMe:Milk;price:3.23;type:Food;expiration:1/25/2016##\n" +
                "naME:BreaD;price:1.23;type:Food@expiration:1/02/2016##\n" +
                "NAMe:BrEAD;price:1.23;type:Food@expiration:2/25/2016##\n" +
                "naMe:MiLK;priCe:;type:Food;expiration:1/11/2016##\n" +
                "naMe:Cookies;price:2.25;type:Food;expiration:1/25/2016##\n" +
                "naMe:Co0kieS;pRice:2.25;type:Food;expiration:1/25/2016##\n" +
                "naMe:COokIes;price:2.25;type:Food;expiration:3/22/2016##\n" +
                "naMe:COOkieS;Price:2.25;type:Food;expiration:1/25/2016##\n" +
                "NAME:MilK;price:3.23;type:Food;expiration:1/17/2016##\n" +
                "naMe:MilK;priCe:;type:Food;expiration:4/25/2016##\n" +
                "naMe:apPles;prIce:0.25;type:Food;expiration:1/23/2016##\n" +
                "naMe:apPles;pRice:0.23;type:Food;expiration:5/02/2016##\n" +
                "NAMe:BrEAD;price:1.23;type:Food;expiration:1/25/2016##\n" +
                "naMe:;price:3.23;type:Food^expiration:1/04/2016##";

        List<Item> items = parseData(inputData);
        for (Item item: items) {
            System.out.println(item);
        }
    }

    public static List<Item> parseData(String inputData){
        String rawRegex = "(?i)na(me)?\\s*:(?<name>[^;]+);\\" +
                "s*pri(ce)?\\s*:(?<price>[^;]+);\\" +
                "s*type\\s*:(?<type>[^@]+)@\\" +
                "s*expiration\\s*:(?<expiration>[^#]+)##";
        Pattern pattern = Pattern.compile(rawRegex);
        Matcher matcher = pattern.matcher(inputData);

        List<Item> items = new ArrayList<>();
        while (matcher.find()) {
            String name = matcher.group("name");
            String price = matcher.group("price");
            String type = matcher.group("type");
            String expiration = matcher.group("expiration");

            Item item = new Item(name, price, type, expiration);
            items.add(item);
        }
        return items;
    }
}
