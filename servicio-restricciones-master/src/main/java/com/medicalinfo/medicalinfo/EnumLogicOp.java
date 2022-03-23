package com.medicalinfo.medicalinfo;

public enum EnumLogicOp {

    greaterThan(0), lesserThan(1), equalTo(2), getGreaterThanOrEqual(3), lesserThanOrEqual(4), notEqualTo(5);

    private final int code;

    EnumLogicOp(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
