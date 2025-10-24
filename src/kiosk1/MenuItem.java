package kiosk1;

public class MenuItem {
    private String name;
    private int price;
    private String info;

    MenuItem(String name, int price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public String getInfo() {
        return info;
    }
}
