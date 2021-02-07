package com.herms.enums;

public enum Condition {

    NOT_NULL("NON", "!= ''"),
    BIGGER_THAN("BIT", ">"),
    SMALLER_THAN("SMT", "<"),
    MAX_LENGTH("MAL", ".length >"),
    MIN_LENGTH("MIL", ".length <");

    private String code;
    private String operator;

    Condition(String code, String operator) {
        this.code = code;
        this.operator = operator;
    }

    public String getCode() {
        return code;
    }

    public String getOperator() {
        return operator;
    }

    public static Condition toEnum(String code){
        for(Condition condition : values()){
            if(condition.getCode().equals(code)) {
                return condition;
            }
        }
        return null;
    }
}
