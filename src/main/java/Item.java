public class Item {
    private String name;
    private String price;
    private String type;
    private String expiration;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice() {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType() {
        this.type = type;
    }

    public String getExpiration() {
        return expiration;
    }

    public void setExpiration() {
        this.expiration = expiration;
    }

    public Item(String name, String price, String type, String expiration) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return String.format("Item{name='%s', price='%s', type='%s', expiration ='%s'");
    }
}
