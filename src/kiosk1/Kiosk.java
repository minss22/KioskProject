package kiosk1;
import java.util.List;
import java.util.Scanner;

public class Kiosk {
    List<MenuItem> menuItems; // 필드

    Kiosk (List<MenuItem> menuItems) { // 생성자
        this.menuItems = menuItems;
    }

    public void start() { // 메서드: 입력 (반복)
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
