package enumerations;

public enum LogicOp {

    greaterThan(0), lesserThan(1), equalTo(2), getGreaterThanOrEqual(3), lesserThanOrEqual(4), notEqualTo(5);



    private final int code;

    LogicOp(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }


}
