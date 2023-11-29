package e2e.enums;

import lombok.Getter;
@Getter
public enum ContactInfoTabs {
    INFO("2"),             // почему тут индкс заканчивается на 5 должен на 4
    PHONES("3"),
    EMAILS("4"),
    ADRESSES("5");
    public final String value;
    ContactInfoTabs(String value){
        this.value = value;
    }
}
