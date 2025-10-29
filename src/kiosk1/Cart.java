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

                int number = safeNextInt();
                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 2) break;

                System.out.print("- 수량을 입력해주세요: ");
                int amount = safeNextInt();
                if (amount < 1) throw new RuntimeException("⚠️0 이하는 입력할 수 없습니다.");

                System.out.printf("\n🔔 %s %d개가 장바구니에 추가되었습니다.\n", itemName, amount);

                for (CartItem cartItem : cartItems) {
                    if (cartItem.getName().equals(itemName)) { // 동일한 메뉴가 있으면 수량 증가
                        cartItem.increaseAmount(amount, itemPrice * amount);
                        return;
                    }
                }
                cartItems.add(new CartItem(itemName, amount, itemPrice * amount)); // ✅ 장바구니 추가 (CartItem 객체 생성)
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
        int beforeSize = cartItems.size();
        // equalsIgnoreCase: 대소문자 상관없이 비교
        cartItems = cartItems.stream().filter(x -> !x.getName().equalsIgnoreCase(name)).collect(Collectors.toList());

        if (beforeSize == cartItems.size()) { // 제거된게 없으면 메시지 처리
            System.out.println("⚠️해당 메뉴가 장바구니에 없습니다.");
        } else {
            System.out.println("\n🔔 수정이 완료되었습니다.");
            printOrder();
        }
    }

    public void order() { // 주문하기
        System.out.println("\n💬 아래와 같이 주문 하시겠습니까?");

        printOrder(); // 장바구니 출력

        int totalPrice = cartItems.stream().mapToInt(CartItem::getPrice).sum();
        System.out.println("[total] " + totalPrice + "원");

        while (true){
            try {
                System.out.print("\n1. 예          2. 아니요\n- 입력: ");
                int number = safeNextInt();

                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 1) {
                    int rate = discountRate(); // 할인율
                    System.out.printf("\n🔔 %d원이 결제되었습니다.\n", totalPrice - totalPrice*rate/100);
                    System.out.println("   주문이 완료되었습니다. 감사합니다.");
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
                int number = safeNextInt();
                if (number > discountTypes.length || number < 1)  throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");

                return discountTypes[number-1].getRate();
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }

    private int safeNextInt() {
        if (!sc.hasNextInt()) { // 숫자가 아니면 예외처리
            sc.next();
            throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
        }
        return sc.nextInt(); // 숫자면 리턴
    }
}
