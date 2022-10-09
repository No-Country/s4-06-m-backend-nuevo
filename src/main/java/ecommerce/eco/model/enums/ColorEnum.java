package ecommerce.eco.model.enums;

public enum ColorEnum {

    LIGHT_BLUE("#ff0000"),
    ROSE("#00ff00"),
    RED("#0000ff"),
    YELLOW("#131746");

    private final String name ;
    ColorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
