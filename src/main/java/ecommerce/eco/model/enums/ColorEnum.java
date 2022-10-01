package ecommerce.eco.model.enums;

public enum ColorEnum {
    LIGHT_BLUE("LIGHT_BLUE"),
    ROSE("ROSE")
    ,RED("RED"),
    YELLOW("YELLOW");
    private final String name ;
    ColorEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }



}
