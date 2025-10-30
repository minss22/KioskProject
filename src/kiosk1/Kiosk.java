package kiosk1;
import java.util.List;
import java.util.Scanner;

/**
 * Kiosk 클래스
 * - 프로그램의 전체 흐름을 제어하는 컨트롤러 역할
 * - 메뉴 관리 및 사용자 입력 처리
 * - 장바구니 및 주문 기능 호출
 */
public class Kiosk {
    // 속성
    private final List<Menu> menus; // 카테고리 리스트
    private final Cart cart = new Cart(); // 장바구니 관리 객체
    Scanner sc = new Scanner(System.in);

    /**
     * Kiosk 생성자
     * @param menus 카테고리 리스트
     */
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    // 기능

    /**
     * 프로그램 메인 루프
     * - 카테고리(Menu)/메뉴(MenuItem) 출력 및 사용자 입력에 따른 로직 처리
     */
    public void start() {
        while (true) {
            printMenus(); // Menu(카테고리) 리스트 출력
            int menuNum = inputInt(menus); // 사용자 입력 검증 및 값 반환
            if (menuNum == 0) break; // 종료
            if (menuNum == -1) continue; // 잘못된 값 입력시 다시 시작

            if (menuNum <= menus.size()) { // [ MAIN MENU ]에서 선택한 경우
                // 선택한 Menu(카테고리)의 MenuItem(메뉴) 리스트 출력
                Menu menu = menus.get(menuNum - 1); // Menu(카테고리) 선택
                menu.printMenuItems(); // MenuItem(메뉴) 리스트 출력

                List<MenuItem> menuItems = menu.getMenuItems(); // MenuItem(메뉴) 리스트
                int itemNum = inputInt(menuItems); // 사용자 입력 검증 및 값 반환
                if (itemNum == -1) continue; // 0 또는 잘못된 값 입력시 다시 시작

                // 선택한 MenuItem(메뉴) 출력
                MenuItem item = menuItems.get(itemNum - 1); // 메뉴 선택
                String itemName = item.getName();
                int itemPrice = item.getPrice();
                String itemInfo = item.getInfo();
                System.out.printf("\n🔔 선택한 메뉴: %s(%d원) - %s\n", itemName, itemPrice, itemInfo); // 선택한 메뉴 출력

                // 장바구니 담기
                cart.addOrder(itemName, itemPrice);

            } else { // [ ORDER MENU ] 선택한 경우 - 주문/수정/취소 기능 처리
                int n = menuNum - menus.size();
                if (n == 1) cart.order(); // 주문하기
                else if (n == 2) cart.modifyOrder(); // 장바구니 수정
                else if (n == 3) cart.clearOrder(); // 주문 취소
            }
            System.out.println();
        }
    }

    /**
     * 메인 메뉴 출력
     * - Menu(카테고리) 출력
     * - 장바구니 및 주문 기능 메뉴 출력
     */
    private void printMenus() {
        System.out.println("=========================================");
        System.out.println("[ MAIN MENU ]");
        int idx = 1;
        for (Menu menu : menus) {
            System.out.printf("%d. %s\n", idx++, menu.getCategory());
        }
        System.out.println("0. 종료");

        if (!cart.getOrder().isEmpty()) { // 장바구니가 들어있으면 출력
            System.out.println("\n[ ORDER MENU ]");
            System.out.println((menus.size() + 1) + ". Orders     | 장바구니를 확인 후 주문합니다.");
            System.out.println((menus.size() + 2) + ". Modify     | 장바구니를 수정합니다.");
            System.out.println((menus.size() + 3) + ". Cancel     | 진행중인 주문을 취소합니다.");
        }
        System.out.print("- 번호 선택: ");
    }

    /**
     * 사용자 입력 검증
     * - 리스트 타입에 따라 로직이 달라짐
     * - 문자열이나 잘못된 값이 입력되면 -1 반환
     * @param list 선택할 리스트
     * @param <S> 선택할 리스트 타입
     * @return 입력값 또는 -1(잘못된 값)
     */
    private <S> int inputInt(List<S> list) {
        if (!sc.hasNextInt()) { // 숫자 아니면 예외처리
            System.out.println("⚠️번호를 정확히 입력해주세요.\n");
            sc.next();
            return -1;
        }
        int input = sc.nextInt();

        if (input == 0) { // 0 입력 시 종료
            System.out.println();
            if (list == menus) System.out.println("프로그램을 종료합니다.");
            else return -1;
        } else if (input < 0 || input > list.size() + (list == menus && !cart.getOrder().isEmpty() ? 3 : 0)) {
            System.out.println("⚠️번호를 정확히 입력해주세요.\n"); // 유효하지 않은 입력에 대해 오류 메시지를 출력
            return -1;
        }
        return input;
    }
}
