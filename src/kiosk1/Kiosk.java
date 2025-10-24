package kiosk1;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // 속성
    private List<Menu> menus;

    // 생성자
    Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    // 기능
    public void start() { // 메서드: 입력 (반복)
        Scanner sc = new Scanner(System.in);
        int input;
        while (true) {
            // ✅ 상위 카테고리 메뉴 리스트 출력
            System.out.println("[ MAIN MENU ]");
            for (Menu menu : menus) {
                System.out.printf("%d. %s\n", menus.indexOf(menu)+1, menu.getCategory());
            }
            System.out.print("0. 종료\n- 입력: ");

            input = sc.nextInt(); // ✅ 입력받기
            if (input==0) { // 0 입력 시 종료
                System.out.println("\n프로그램을 종료합니다.");
                break;
            } else if (input < 0 || input > menus.size()) { // 유효하지 않은 입력에 대해 오류 메시지를 출력
                System.out.println("⚠️ 선택하신 메뉴는 없습니다. 다시 입력해주세요.\n");
                continue;
            }
            Menu menu = menus.get(input-1); // ✅ Menu 선택

            // ✅ MenuItems 출력
            menu.printMenuItems();

            input = sc.nextInt(); // ✅ 입력받기
            List<MenuItem> menuItems = menu.getMenuItems();
            if (input==0) { // 0 입력 시 종료
                System.out.println();
                continue;
            } else if (input < 0 ||input > menuItems.size()) { // 유효하지 않은 입력에 대해 오류 메시지를 출력
                System.out.println("⚠️ 선택하신 메뉴는 없습니다. 다시 입력해주세요.\n");
                continue;
            }

            MenuItem item = menuItems.get(input - 1); // ✅ MenuItem 선택 및 출력
            System.out.printf("\n# 선택한 메뉴: %s(%d원) - %s\n\n", item.getName(), item.getPrice(), item.getInfo());
        }
    }
}
