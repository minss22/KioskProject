package kiosk1;

public class MenuItem {
    // 속성
    private final String name;
    private final int price;
    private final String info;

    // 생성자
    MenuItem(String name, int price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }

    // 기능
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
