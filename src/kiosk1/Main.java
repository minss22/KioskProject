package kiosk1;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        List<MenuItem> menuItems = new ArrayList<>();
        // 햄버거 메뉴 세팅
        menuItems.add(new MenuItem("ShackBurger", 6900, "토마토, 양상추, 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("SmokeShack", 8900, "베이컨, 체리 페퍼에 쉑소스가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Cheeseburger", 6900, "포테이토 번과 비프패티, 치즈가 토핑된 치즈버거"));
        menuItems.add(new MenuItem("Hamburger", 5400, "비프패티를 기반으로 야채가 들어간 기본버거"));

        Scanner sc = new Scanner(System.in);
        int input;
        while (true) { // 0 입력 시 종료
            // 햄버거 메뉴 출력
            System.out.println("[ SHAKESHACK MENU ]");
            for (int i=0;i<menuItems.size();i++) {
                MenuItem menu = menuItems.get(i);
                System.out.printf("%d. %-13s | %4d 원 | %s\n", i+1, menu.name, menu.price, menu.info);
            }
            System.out.print("0. 종료\n- 입력: ");

            input = sc.nextInt(); // 입력받기
            if (input==0) {
                System.out.println("프로그램을 종료합니다.");
                break;
            } else if (input<=menuItems.size()) {
                MenuItem menu = menuItems.get(input - 1);
                System.out.printf("- 선택한 메뉴: %s, %d원 (%s)\n\n", menu.name, menu.price, menu.info);
            } else {
                System.out.println("⚠️선택하신 메뉴는 없습니다. 다시 입력해주세요.\n");
            }
        }
    }
}
