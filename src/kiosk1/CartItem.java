package kiosk1;

/**
 * CartItem 클래스
 * - 장바구니에 담긴 메뉴(메뉴명, 수량, 가격)를 관리
 * - 가격은 기존 가격에 수량이 곱해진 값
 */
public class CartItem {
    // 속성: 메뉴명, 수량, 가격
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
    /** 메뉴명 반환 */
    public String getName() {
        return name;
    }

    /** 수량 반환 */
    public int getAmount() {
        return amount;
    }

    /** 가격 반환 */
    public int getPrice() {
        return price;
    }

    /** 같은 항목이 추가될 때 수량 및 가격 누적 */
    public void increaseAmount(int amount, int price) {
        this.amount += amount;
        this.price += price;
    }
}
