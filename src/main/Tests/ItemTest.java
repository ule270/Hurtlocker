import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ItemTest {
    Item item;
    @Before
    public void beforeInit(){
        item = new Item("Milk", "3.23", "Food", "01/25/2016");
    }

    @Test
    public void getNameTest() throws Exception{
        String expected = "Milk";
        String actual = item.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setNameTest() throws Exception{
        String expected = "Eggs";
        item.setName("Eggs");
        String actual = item.getName();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getPriceTest() throws Exception{
        String expected = "3.23";
        String actual = item.getPrice();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setPriceTest() throws Exception{
        String expected = "4.50";
        item.setPrice("4.50");
        String actual = item.getPrice();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getTypeTest() throws Exception{
        String expected = "Food";
        String actual = item.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setTypeTest() throws Exception{
        String expected = "Toys";
        item.setType("Toys");
        String actual = item.getType();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void getExpirationTest() throws Exception{
        String expected = "01/25/2016";
        String actual = item.getExpiration();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void setExpirationTest() throws Exception{
        String expected = "04/17/2023";
        item.setExpiration("04/17/2023");
        String actual = item.getExpiration();
        Assert.assertEquals(expected, actual);
    }

}
