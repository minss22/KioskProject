package kiosk1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    // 속성
    private final List<Menu> menus;
    private final List<Order> orders = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    // 생성자
    public Kiosk(List<Menu> menus) {
        this.menus = menus;
    }

    // 기능
    public void start() { // 메뉴 관리 및 사용자 입력 처리 (반복)
        while (true) {
            /* ======= 메뉴 카테고리 출력 후 입력받기 ======= */
            printMenus(); // ✅ 상위 카테고리 메뉴 리스트 출력

            int menuNum = inputInt(menus); // ✅ 입력받기
            if (menuNum == 0) break;
            else if (menuNum == -1) continue;
            else if (menuNum == menus.size() + 1) { // 주문하기
                order();
                System.out.println();
                continue;
            } else if (menuNum == menus.size() + 2) { // 주문 취소
                orders.clear(); // 장바구니 초기화
                System.out.println();
                continue;
            }

            Menu menu = menus.get(menuNum - 1); // ✅ Menu 선택

            /* ========= 메뉴 출력 후 입력받기 ========= */
            menu.printMenuItems(); // ✅ MenuItems 출력
            List<MenuItem> menuItems = menu.getMenuItems();

            int itemNum = inputInt(menuItems); // ✅ 입력받기
            if (itemNum == -1) continue;

            MenuItem item = menuItems.get(itemNum - 1); // ✅ MenuItem 선택
            String itemName = item.getName();
            int itemPrice = item.getPrice();
            String itemInfo = item.getInfo();
            System.out.printf("\n🔔 선택한 메뉴: %s(%d원) - %s\n", itemName, itemPrice, itemInfo); // 선택한 메뉴 출력

            /* ============ 장바구니 담기 ============ */
            addOrder(itemName, itemPrice);
            System.out.println();
        }
    }

    private void printMenus() {
        System.out.println("[ MAIN MENU ]");
        for (Menu menu : menus) {
            System.out.printf("%d. %s\n", menus.indexOf(menu) + 1, menu.getCategory());
        }
        System.out.println("0. 종료");

        if (!orders.isEmpty()) {
            System.out.println("\n[ ORDER MENU ]");
            System.out.println((menus.size()+1) + ". Orders     | 장바구니를 확인 후 주문합니다.");
            System.out.println((menus.size()+2) + ". Cancel     | 진행중인 주문을 취소합니다.");
        }
        System.out.print("- 번호 선택: ");
    }

    private <S> int inputInt(List<S> list) {
        int input = sc.nextInt();
        if (input == 0) { // 0 입력 시 종료
            System.out.println();
            if (list == menus) System.out.println("프로그램을 종료합니다.");
            else return -1;
        } else if (input < 0 || input > list.size() + (list == menus ? 2:0)) { // 유효하지 않은 입력에 대해 오류 메시지를 출력
            System.out.println("⚠️ 선택하신 메뉴는 없습니다. 다시 입력해주세요.\n");
            return -1;
        }
        return input;
    }

    private void addOrder(String itemName, int itemPrice) {
        while (true) {
            try {
                System.out.println("\n💬 위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.print("1. 확인       2. 취소\n- 입력: ");

                int number = sc.nextInt();
                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 2) break;

                System.out.print("- 수량을 입력해주세요: ");
                int amount = sc.nextInt();
                if (amount < 1) throw new RuntimeException("⚠️0 이하는 입력할 수 없습니다.");

                System.out.printf("\n🔔 %s %d개가 장바구니에 추가되었습니다.\n", itemName, amount);
                orders.add(new Order(itemName, amount, itemPrice * amount));
                break;
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }

    private void order() {
        System.out.println("\n💬 아래와 같이 주문 하시겠습니까?");

        for (Order order : orders) { // 주문 확인
            String name = order.getName();
            int amount = order.getAmount();
            int price = order.getPrice();
            System.out.printf("%-13s | %3d 개 | %6d 원\n", name, amount, price);
        }

        int totalPrice = orders.stream().mapToInt(Order::getPrice).sum();
        System.out.println("[total] " + totalPrice + "원");

        while (true){
            try {
                System.out.print("\n1. 예        2. 아니요\n- 입력: ");
                int number = sc.nextInt();

                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 1) {
                    int rate = discountRate(); // 할인율
                    System.out.printf("\n🔔 주문이 완료되었습니다. %d원이 결제되었습니다.\n", totalPrice - totalPrice*rate/100);
                    orders.clear(); // 장바구니 초기화
                }
                break;
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }

    private int discountRate() {
        while (true){
            try {
                System.out.println("\n💬 할인 정보를 입력해주세요.");
                DiscountType[] discountTypes = DiscountType.values();

                for (DiscountType d : discountTypes) {
                    System.out.printf("%d. %-5s : %d", d.ordinal() + 1, d.getKrName(), d.getRate());
                    System.out.println("%");
                }

                System.out.print("- 입력: ");
                int input = sc.nextInt();
                if (input > discountTypes.length || input < 0)  throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");

                return discountTypes[input-1].getRate();
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }
}
