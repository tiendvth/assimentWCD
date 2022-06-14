package fptaptech.assignmentwcd.entity.entityEnum;

public enum FormAction {
    CREATE(1),
    UPDATE(2),
    UNDEFINED(3);
    private int value;

    FormAction(int value) { this.value = value;  }

    public int getValue() { return this.value; }

    public static FormAction of(int value) {
        for(FormAction formAction : FormAction.values()) {
            if(formAction.getValue() == value) {
                return formAction;
            }
        }
        return FormAction.UNDEFINED;
    }
}
