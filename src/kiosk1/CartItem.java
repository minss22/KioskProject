package kiosk1;

public class CartItem {
    // 속성: 메뉴명, 수량, 가격 정보
    private final String name;
    private int amount;
    private int price;

    // 생성기
    public CartItem(String name, int amount, int price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    // 기능
    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public int getPrice() {
        return price;
    }

    public void increaseAmount(int amount, int price) {
        this.amount += amount;
        this.price += price;
    }
}
