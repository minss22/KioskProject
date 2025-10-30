package kiosk1;
import java.util.List;
import java.util.ArrayList;

/**
 * Main 클래스
 * - 프로그램 시작점
 * - 여러 Menu 객체를 생성하고, 각 카테고리에 MenuItem을 추가해서 메뉴 세팅
 * - Kiosk 실행
 */
public class Main {
    public static void main(String[] args) {
        // Menu 객체 생성을 통해 카테고리 이름 설정
        Menu burgers = new Menu("Burgers");
        Menu drinks = new Menu("Drinks");
        Menu desserts = new Menu("Desserts");

        // List 선언 후, 생성된 Menu 추가
        List<Menu> menus = new ArrayList<>();
        menus.add(burgers);
        menus.add(drinks);
        menus.add(desserts);

        // 카테고리별 메뉴 세팅 - Menu 클래스 내 있는 List<MenuItem> 에 MenuItem 객체 생성하면서 삽입
        burgers.addMenuItems(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        burgers.addMenuItems(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        burgers.addMenuItems(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        burgers.addMenuItems(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));

        // Kiosk 객체 생성 및 함수 실행
        Kiosk kiosk = new Kiosk(menus);
        kiosk.start();
    }
}
