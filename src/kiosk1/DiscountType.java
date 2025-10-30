package kiosk1;

/**
 * DiscountType Enum
 * - 사용자 유형별 할인율을 관리
 */
public enum DiscountType {
    MERIT("국가유공자",10),
    SOLDIER("군인",5),
    STUDENT("학생",3),
    GENERAL("일반",0);

    private final String krName; // 한글 이름
    private final int rate; // 할인율

    DiscountType(String krName, int rate) {
        this.krName = krName;
        this.rate = rate;
    }

    /** 한글 이름 반환 */
    public String getKrName() {
        return krName;
    }

    /** 할인율 반환 */
    public int getRate() {
        return rate;
    }
}
