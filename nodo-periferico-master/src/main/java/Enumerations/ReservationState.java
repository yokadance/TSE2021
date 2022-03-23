package Enumerations;

public enum ReservationState {
    pending(0),
    canceled(1),
    rejected(2),
    confirmed(3);

    private final int code;

    ReservationState(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}