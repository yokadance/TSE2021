package DTO;

import java.io.Serializable;

public class DTRestrictionAPI implements Serializable {

    private String elementName;
    private String logicOperator;
    private int value;

    public DTRestrictionAPI() {
    }

    public DTRestrictionAPI(String elementName, String logicOperator, int value) {
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

    public String getLogicOperator() {
        return logicOperator;
    }

    public void setLogicOperator(String logicOperator) {
        this.logicOperator = logicOperator;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
