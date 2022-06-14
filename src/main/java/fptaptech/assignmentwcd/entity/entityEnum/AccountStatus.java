package fptaptech.assignmentwcd.entity.entityEnum;

public enum AccountStatus {
    ACTIVE(1),
    DEACTIVE(0),
    DELETED(-1),
    UNDEFINED(2);
    private int value;

    AccountStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return this.value;
    }

    public static AccountStatus of(int value) {
        for (AccountStatus accountStatus : AccountStatus.values()) {
            if(accountStatus.getValue() == value) {
                return accountStatus;
            }
        }
        return AccountStatus.UNDEFINED;
    }
}