package kiosk1;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.stream.IntStream;

/**
 * Menu 클래스
 * - 하나의 카테고리를 관리
 * - 여러 MenuItem을 보관
 */
public class Menu {
    // 속성: 카테고리, 메뉴 리스트
    private final String category;
    private final List<MenuItem> menuItems = new ArrayList<>();

    // 생성자
    public Menu(String category) {
        this.category = category;
    }

    // 기능
    /** 카테고리 명 반환 */
    public String getCategory() { // Getter
        return category;
    }

    /** 메뉴 리스트 반환 */
    public List<MenuItem> getMenuItems() { // Getter
        return Collections.unmodifiableList(menuItems); // 읽기 전용 리스트
    }

    /** 새로운 메뉴 추가 */
    public void addMenuItems(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    /** 메뉴 리스트 출력 - 스트림 활용 */
    public void printMenuItems() {
        System.out.printf("\n[ %s MENU ]\n", category.toUpperCase());
        IntStream.range(0, menuItems.size()).forEach(i -> System.out.printf("%d. %-13s | %4d 원 | %s\n",
                i+1, menuItems.get(i).getName(), menuItems.get(i).getPrice(), menuItems.get(i).getInfo()));
        System.out.print("0. 뒤로가기\n- 입력: ");
    }
}
