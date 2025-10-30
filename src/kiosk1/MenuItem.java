package kiosk1;

/**
 * MenuItem 클래스
 * - 개별 메뉴 항목(메뉴명, 가격, 설명)을 표현
 */
public class MenuItem {
    // 속성: 메뉴명, 가격, 정보
    private final String name;
    private final int price;
    private final String info;

    // 생성자
    public MenuItem(String name, int price, String info) {
        this.name = name;
        this.price = price;
        this.info = info;
    }

    // 기능 (Getter)
    /** 메뉴명 반환 */
    public String getName() {
        return name;
    }

    /** 가격 반환 */
    public Integer getPrice() {
        return price;
    }

    /** 정보 반환 */
    public String getInfo() {
        return info;
    }
}
