package fptaptech.assignmentwcd.entity.entityEnum;

public enum MessageType {
    SUCCESS(1),
    ERROR(2),
    WARNING(3),
    UNDEFINED(0);
    private int value;

    MessageType(int value) { this.value = value; }

    public int getValue() {
        return value;
    }

    public static MessageType of(int value) {
        for (MessageType messageType : MessageType.values()) {
            if(messageType.getValue() == value) {
                return messageType;
            }
        }
        return MessageType.UNDEFINED;
    }
}
