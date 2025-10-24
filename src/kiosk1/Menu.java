package kiosk1;
import java.util.List;
import java.util.ArrayList;

public class Menu {
    private String category;
    private List<MenuItem> menuItems = new ArrayList<>();

    Menu(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }

    public List<MenuItem> getMenuItems() { // List를 리턴하는 함수
        return menuItems;
    }
}
