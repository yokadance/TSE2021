package enumerations;

public enum VaccinationState {
    Used(0), Destroyed(1), Available(2);

    private final int code;

    VaccinationState(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
