package kiosk1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Cart {
    private List<CartItem> cartItems = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    public List<CartItem> getOrder() { // Getter
        return cartItems;
    }

    public void printOrder() { // 장바구니 출력
        System.out.println("[ Orders ]");
        for (CartItem cartItem : cartItems) { // 주문 확인
            String name = cartItem.getName();
            int amount = cartItem.getAmount();
            int price = cartItem.getPrice();
            System.out.printf("%-13s | %3d 개 | %6d 원\n", name, amount, price);
        }
    }

    public void clearOrder() { // 장바구니 초기화
        cartItems.clear();
    }

    public void addOrder(String itemName, int itemPrice) { // 장바구니 추가
        while (true) {
            try {
                System.out.println("\n💬 위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.print("1. 확인          2. 취소\n- 입력: ");

                int number = sc.nextInt();
                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 2) break;

                System.out.print("- 수량을 입력해주세요: ");
                int amount = sc.nextInt();
                if (amount < 1) throw new RuntimeException("⚠️0 이하는 입력할 수 없습니다.");

                System.out.printf("\n🔔 %s %d개가 장바구니에 추가되었습니다.\n", itemName, amount);
                cartItems.add(new CartItem(itemName, amount, itemPrice * amount));
                break;
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }

    public void modifyOrder() { // 장바구니 수정
        System.out.println("\n💬 제거할 메뉴 이름을 입력해주세요.");
        printOrder();

        System.out.print("- 입력: ");
        String name = sc.next();

        cartItems = cartItems.stream().filter(x -> !x.getName().equals(name)).collect(Collectors.toList());
        System.out.println("\n🔔 수정이 완료되었습니다.");
        printOrder();
    }

    public void order() { // 주문하기
        System.out.println("\n💬 아래와 같이 주문 하시겠습니까?");

        printOrder(); // 장바구니 출력

        int totalPrice = cartItems.stream().mapToInt(CartItem::getPrice).sum();
        System.out.println("[total] " + totalPrice + "원");

        while (true){
            try {
                System.out.print("\n1. 예          2. 아니요\n- 입력: ");
                int number = sc.nextInt();

                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 1) {
                    int rate = discountRate(); // 할인율
                    System.out.printf("\n🔔 주문이 완료되었습니다. %d원이 결제되었습니다.\n", totalPrice - totalPrice*rate/100);
                    clearOrder(); // 장바구니 초기화
                }
                break;
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }

    private int discountRate() { // 할인율
        while (true){
            try {
                System.out.println("\n💬 할인 정보를 입력해주세요.");
                DiscountType[] discountTypes = DiscountType.values();

                for (DiscountType d : discountTypes) {
                    System.out.printf("%d. %-6s: %d", d.ordinal() + 1, d.getKrName(), d.getRate());
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
