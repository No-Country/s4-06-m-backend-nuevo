package ecommerce.eco.model.enums;

public enum ColorEnum {
    A("#ff0000"),
    B("#00ff00"),
    C("#0000ff"),
    D("YELLOW");
    private final String name ;
    ColorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
