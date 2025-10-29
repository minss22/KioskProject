package kiosk1;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // 속성
    private final List<Menu> menus;
    private final Cart cart = new Cart();
    Scanner sc = new Scanner(System.in);

    // 생성자
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    // 기능
    public void start() { // 메뉴 관리 및 사용자 입력 처리 (반복)
        while (true) {
            /* ======= 메뉴 카테고리 출력 후 입력받기 ======= */
            printMenus(); // 상위 카테고리 Menu 리스트 출력

            int menuNum = inputInt(menus); // 입력받기
            if (menuNum == 0) break;
            else if (menuNum == -1) continue;
            else if (menuNum > menus.size()) {
                if (menuNum == menus.size() + 1) cart.order(); // 주문하기
                else if (menuNum == menus.size() + 2) cart.modifyOrder(); // 장바구니 수정
                else if (menuNum == menus.size() + 3) cart.clearOrder(); // 주문 취소
                System.out.println();
                continue;
            }

            Menu menu = menus.get(menuNum - 1); // Menu(카테고리) 선택

            /* ========= 메뉴 출력 후 입력받기 ========= */
            menu.printMenuItems(); // MenuItem 리스트 출력
            List<MenuItem> menuItems = menu.getMenuItems(); // MenuItem(메뉴) 리스트

            int itemNum = inputInt(menuItems); // 입력받기
            if (itemNum == -1) continue;

            MenuItem item = menuItems.get(itemNum - 1); // MenuItem(메뉴) 선택
            String itemName = item.getName();
            int itemPrice = item.getPrice();
            String itemInfo = item.getInfo();
            System.out.printf("\n🔔 선택한 메뉴: %s(%d원) - %s\n", itemName, itemPrice, itemInfo); // 선택한 메뉴 출력

            /* ============ 장바구니 담기 ============ */
            cart.addOrder(itemName, itemPrice);
            System.out.println();
        }
    }

    private void printMenus() {
        System.out.println("=========================================");
        System.out.println("[ MAIN MENU ]");
        int idx = 1;
        for (Menu menu : menus) {
            System.out.printf("%d. %s\n", idx++, menu.getCategory());
        }
        System.out.println("0. 종료");

        if (!cart.getOrder().isEmpty()) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println((menus.size() + 1) + ". Orders     | 장바구니를 확인 후 주문합니다.");
            System.out.println((menus.size() + 2) + ". Modify     | 장바구니를 수정합니다.");
            System.out.println((menus.size() + 3) + ". Cancel     | 진행중인 주문을 취소합니다.");
        }
        System.out.print("- 번호 선택: ");
    }

    private <S> int inputInt(List<S> list) {
        int input = sc.nextInt();
        if (input == 0) { // 0 입력 시 종료
            System.out.println();
            if (list == menus) System.out.println("프로그램을 종료합니다.");
            else return -1;
        } else if (input < 0 || input > list.size() + (list == menus ? 3 : 0)) { // 유효하지 않은 입력에 대해 오류 메시지를 출력
            System.out.println("⚠️ 선택하신 메뉴는 없습니다. 다시 입력해주세요.\n");
            return -1;
        }
        return input;
    }
}
