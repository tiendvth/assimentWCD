package fptaptech.assignmentwcd.entity.entityEnum;

public enum FoodStatus {
    ON_SALE(1),
    STOP_SELLING(2),
    DELETED(0),
    UNDEFINED(-1);
    private int value;

    FoodStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static FoodStatus of(int value) {
        for (FoodStatus foodStatus : FoodStatus.values()) {
            if(foodStatus.getValue() == value) {
                return foodStatus;
            }
        }
        return FoodStatus.UNDEFINED;
    }
}