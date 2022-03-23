package com.medicalinfo.medicalinfo;

public class Restrictions {
    private String elementName;
    private EnumLogicOp logicOperator;
    private int value;

    public Restrictions() {
    }

    public Restrictions(String elementName, EnumLogicOp logicOperator, int value) {
        this.elementName = elementName;
        this.logicOperator = logicOperator;
        this.value = value;
    }

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }

    public EnumLogicOp getLogicOperator() {
        return logicOperator;
    }

    public void setLogicOperator(EnumLogicOp logicOperator) {
        this.logicOperator = logicOperator;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
