package kiosk1;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    // 속성
    private String category;
    private List<MenuItem> menuItems = new ArrayList<>();

    // 생성자
    Menu(String category) {
        this.category = category;
    }

    // 기능
    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() { // List를 리턴하는 함수
        return menuItems;
    }

    public void setMenuItems(MenuItem menuItem) {
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
