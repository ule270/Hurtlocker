import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemParserTest {
    ItemParser IP;

    @Before
    public void beforeInit() throws Exception{
        IP = new ItemParser();
    }
    @Test
    public void stringSplitTest() throws Exception {
        String expected = "Milk";
        String actual = IP.stringSplit(Main.readRawDataToString())[1];
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void testItemCounter() throws Exception {
        try {
            String expected = "name:\tCookies\t\tseen: 8 times\n" +
                    "=============\t\t=============\n" +
                    "Price:\t2.25\t\tseen: 8 times\n" +
                    "-------------\t\t-------------\n";
            IP.createItem(Main.readRawDataToString());
            String actual = IP.countItems("Cookies");
            Assert.assertEquals(expected, actual);
        } catch (Exception e) {
            System.err.println(e);
        }
    }

    @Test
    public void nameCleanTest() throws Exception{
        String expected = "Apples";
        String actual = IP.nameClean("apples");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void addItemTest() throws Exception{
        int expLength = 1;
        IP.addItem("Milk", "3.23", "Food", "04/17/2023");
        int actLength = IP.getListItems().size();
        Assert.assertEquals(expLength, actLength);
    }

    @Test
    public void findItemTest() throws Exception{
        String expected = "Milk";
        String actual = IP.findItem(Main.readRawDataToString(), 1);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void findItemErrCountTest() throws Exception{
        int errBeforeCounter = 1;
        String expected = "Cookies";
        String actual = IP.findItem("naMe:;price:2.25;type:Food;expiration:3/22/2016##", 1);
        int errAfterCounter = IP.getErrCount();
        Assert.assertEquals(errBeforeCounter, errAfterCounter);
    }

    @Test
    public void testGroceryListConstructor() throws Exception {
        String expected =
                "name:\tMilk\t\tseen: 6 times\n" +
                "=============\t\t=============\n" +
                "Price:\t3.23\t\tseen: 5 times\n" +
                "-------------\t\t-------------\n" +
                "Price:\t1.23\t\tseen: 1 times\n" +
                "\n" +
                "name:\tBread\t\tseen: 6 times\n" +
                "=============\t\t=============\n" +
                "Price:\t1.23\t\tseen: 6 times\n" +
                "-------------\t\t-------------\n" +
                "\n" +
                "name:\tCookies\t\tseen: 8 times\n" +
                "=============\t\t=============\n" +
                "Price:\t2.25\t\tseen: 8 times\n" +
                "-------------\t\t-------------\n" +
                "\n" +
                "name:\tApples\t\tseen: 4 times\n" +
                "=============\t\t=============\n" +
                "Price:\t0.25\t\tseen: 2 times\n" +
                "-------------\t\t-------------\n" +
                "Price:\t0.23\t\tseen: 2 times\n" +
                "\n" +
                "Errors\t\t\tseen: 4 times";
        IP.createItem(Main.readRawDataToString());
        String actual = IP.groceryListConstructor();
        Assert.assertEquals(expected, actual);
    }

}
