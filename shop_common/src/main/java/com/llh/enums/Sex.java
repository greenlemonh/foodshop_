package com.llh.enums;

public enum Sex {

    WOMAN(1,"woman"),

    MAN(2, "man"),

    secret(3,"secret");

    public Integer type;

    public String value;

    private Sex(Integer type, String value) {

        this.type = type;
        this.value = value;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValuse(String valuse) {
        this.value = valuse;
    }
}
