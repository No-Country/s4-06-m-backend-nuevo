package ecommerce.eco.model.enums;

public enum SizeEnum {
        XS("XS"),S("S"),M("M"),L("L"),NULL("NULL");
        //XS = 0, S = 1, M = 2, L = 3, NULL = 4
        private final String name ;
        SizeEnum(String name) {
                this.name = name;
        }

        public String getName() {
                return name;
        }

}
