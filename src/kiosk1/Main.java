package kiosk1;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = -1;

        while (input!=0) { // 0 입력 시 종료
            System.out.println("""
                    [ SHAKESHACK MENU ]
                    1. ShackBurger   | W 6.9 | 토마토, 양상추, 쉑소스가 토핑된 치즈버거
                    2. SmokeShack    | W 8.9 | 베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거
                    3. Cheeseburger  | W 6.9 | 포테이토 번과 비프패티, 치즈가 토핑된 치즈버거
                    4. Hamburger     | W 5.4 | 비프패티를 기반으로 야채가 들어간 기본버거
                    0. 종료""");

            System.out.println("- 입력: ");
            input = sc.nextInt(); // ✅ 입력받기
        }
    }
}
