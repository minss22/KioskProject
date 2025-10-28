package kiosk1;

public enum DiscountType {
    MERIT("국가유공자",10),
    SOLDIER("군인",5),
    STUDENT("학생",3),
    GENERAL("일반",0);

    private final String krName;
    private final int rate;

    DiscountType(String krName, int rate) {
        this.krName = krName;
        this.rate = rate;
    }

    public String getKrName() {
        return krName;
    }

    public int getRate() {
        return rate;
    }
}
