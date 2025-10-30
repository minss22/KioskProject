package kiosk1;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Cart 클래스
 * - 장바구니 데이터 관리
 * - 장바구니 및 주문 관련 기능 담당
 */
public class Cart {
    // 속성: 장바구니 항목 리스트
    private List<CartItem> cartItems = new ArrayList<>();
    Scanner sc = new Scanner(System.in);

    /** 장바구니 항목 리스트 반환 */
    public List<CartItem> getOrder() { // Getter
        return cartItems;
    }

    /** 장바구니 출력 - 스트림활용 */
    public void printOrder() {
        System.out.println("[ Orders ]");
        cartItems.forEach(c
                -> System.out.printf("%-13s | %3d 개 | %6d 원\n", c.getName(), c.getAmount(), c.getPrice()));
    }

    /** 장바구니 초기화 */
    public void clearOrder() {
        cartItems.clear();
        System.out.println("\n🔔 장바구니가 비워졌습니다.");
    }

    /**
     * 장바구니에 항목 추가
     * - 동일한 메뉴 있으면 수량 및 가격 누적
     * @param itemName 추가할 메뉴 이름
     * @param itemPrice 추가할 메뉴 가격
     */
    public void addOrder(String itemName, int itemPrice) {
        while (true) {
            try {
                System.out.println("\n💬 위 메뉴를 장바구니에 추가하시겠습니까?");
                System.out.print("1. 확인          2. 취소\n- 입력: ");

                int number = safeNextInt(); // 사용자 입력 검증 및 값 반환
                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 2) break; // 2 입력 시 종료

                System.out.print("- 수량을 입력해주세요: ");
                int amount = safeNextInt(); // 사용자 입력 검증 및 값 반환
                if (amount < 1) throw new RuntimeException("⚠️0 이하는 입력할 수 없습니다.");

                System.out.printf("\n🔔 %s %d개가 장바구니에 추가되었습니다.\n", itemName, amount);

                for (CartItem cartItem : cartItems) {
                    if (cartItem.getName().equals(itemName)) { // 동일한 메뉴가 있으면, 수량 및 가격 누적
                        cartItem.increaseAmount(amount, itemPrice * amount);
                        return;
                    }
                }
                // 동일한 메뉴 없으면, 장바구니 추가
                cartItems.add(new CartItem(itemName, amount, itemPrice * amount));
                break; // 정상적으로 실행 후 종료
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 장바구니 수정
     * - 특정 메뉴를 제거
     * - stream().filter() 사용
     */
    public void modifyOrder() {
        System.out.println("\n💬 제거할 메뉴 이름을 입력해주세요.");
        printOrder(); // 장바구니 출력

        System.out.print("- 입력: ");
        String name = sc.next();

        int beforeSize = cartItems.size(); // 이전 크기
        cartItems = cartItems.stream().filter(x -> !x.getName().equalsIgnoreCase(name)).collect(Collectors.toList());

        if (beforeSize == cartItems.size()) { // 크기를 비교해서 제거된게 없으면 메시지 처리
            System.out.println("⚠️해당 메뉴가 장바구니에 없습니다.");
        } else {
            System.out.println("\n🔔 수정이 완료되었습니다.");
            printOrder(); // 장바구니 출력
        }
    }

    /**
     * 주문하기
     * - 총 금액 계산 후 할인 적용
     */
    public void order() { // 주문하기
        System.out.println("\n💬 아래와 같이 주문 하시겠습니까?");
        printOrder(); // 장바구니 출력

        int totalPrice = cartItems.stream().mapToInt(CartItem::getPrice).sum(); // 총 금액
        System.out.println("[total] " + totalPrice + "원");

        while (true){
            try {
                System.out.print("\n1. 예          2. 아니요\n- 입력: ");
                int number = safeNextInt();

                if (number != 1 && number != 2) throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
                else if (number == 1) {
                    int rate = discountRate(); // 할인율 선택
                    System.out.printf("\n🔔 %d원이 결제되었습니다.\n", totalPrice - totalPrice*rate/100);
                    System.out.println("   주문이 완료되었습니다. 감사합니다.");
                    cartItems.clear(); // 장바구니 초기화
                }
                break;
            } catch (RuntimeException e) { // 예외 처리
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 사용자 유형별 할인율 선택
     * @return 선택된 할인율(%)
     */
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

    /** 사용자 입력 검증 - 숫자만 허용 */
    private int safeNextInt() {
        if (!sc.hasNextInt()) { // 숫자가 아니면 예외처리
            sc.next();
            throw new RuntimeException("⚠️번호를 정확히 입력해주세요.");
        }
        return sc.nextInt(); // 숫자면 리턴
    }
}
