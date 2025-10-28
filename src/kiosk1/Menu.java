package kiosk1;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    // 속성
    private final String category;
    private final List<MenuItem> menuItems = new ArrayList<>();

    // 생성자
    public Menu(String category) {
        this.category = category;
    }

    // 기능
    public String getCategory() { // Getter: 카테고리 리턴
        return category;
    }

    public List<MenuItem> getMenuItems() { // Getter: List를 리턴
        return Collections.unmodifiableList(menuItems); // 읽기 전용 리스트
    }

    public void addMenuItems(MenuItem menuItem) { // Setter: List에 menuItem 추가
        menuItems.add(menuItem);
    }

    public void printMenuItems() {
        System.out.printf("\n[ %s MENU ]\n", category.toUpperCase());
        for (MenuItem item : menuItems) {
            int idx = menuItems.indexOf(item)+1;
            System.out.printf("%d. %-13s | %4d 원 | %s\n", idx, item.getName(), item.getPrice(), item.getInfo());
        }
        System.out.print("0. 뒤로가기\n- 입력: ");
    }
}
