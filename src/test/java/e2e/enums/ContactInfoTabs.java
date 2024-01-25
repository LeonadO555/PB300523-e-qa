package e2e.enums;


import lombok.Getter;

@Getter
public enum ContactInfoTabs {
    INFO("2"),
    PHONES("3"),
    EMAILS("4"),
    ADDRESSES("5");
    public final String value;
        ContactInfoTabs(String value){
            this.value = value;
        }

}
