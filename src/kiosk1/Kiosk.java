package kiosk1;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // 속성
    private final List<Menu> menus;

    // 생성자
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    // 기능
    public void start() { // 메뉴 관리 및 사용자 입력 처리 (반복)
        while (true) {
            /* 메뉴 카테고리 출력 후 입력받기 */
            printMenus(); // ✅ 상위 카테고리 메뉴 리스트 출력

            int input1 = inputInt(menus); // ✅ 입력받기
            if (input1 == 0) break;
            else if (input1 == -1) continue;

            Menu menu = menus.get(input1 - 1); // ✅ Menu 선택

            /* 메뉴 출력 후 입력받기 */
            menu.printMenuItems(); // ✅ MenuItems 출력
            List<MenuItem> menuItems = menu.getMenuItems();

            int input2 = inputInt(menuItems); // ✅ 입력받기
            if (input2 == -1) continue;

            MenuItem item = menuItems.get(input2 - 1); // ✅ MenuItem 선택 및 출력
            System.out.printf("\n# 선택한 메뉴: %s(%d원) - %s\n\n", item.getName(), item.getPrice(), item.getInfo());
        }
    }

    private void printMenus() {
        System.out.println("[ MAIN MENU ]");
        for (Menu menu : menus) {
            System.out.printf("%d. %s\n", menus.indexOf(menu) + 1, menu.getCategory());
        }
        System.out.print("0. 종료\n- 입력: ");
    }

    private <S> int inputInt(List<S> list) {
        Scanner sc = new Scanner(System.in);

        int input = sc.nextInt();
        if (input == 0) { // 0 입력 시 종료
            System.out.println();
            if (list == menus) System.out.println("프로그램을 종료합니다.");
            else return -1;
        } else if (input < 0 || input > list.size()) { // 유효하지 않은 입력에 대해 오류 메시지를 출력
            System.out.println("⚠️ 선택하신 메뉴는 없습니다. 다시 입력해주세요.\n");
            return -1;
        }
        return input;
    }
}
