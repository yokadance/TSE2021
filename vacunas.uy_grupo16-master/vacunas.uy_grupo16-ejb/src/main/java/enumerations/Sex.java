package enumerations;

public enum Sex {
    feminine(0), masculine(1), other(2);

    private final int code;

    Sex(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }

}
