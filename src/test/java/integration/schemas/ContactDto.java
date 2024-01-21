package integration.schemas;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
// shemas - типизация входных данных
@Data // лежат данные с типизацией
@AllArgsConstructor // могут быть все парам. и будут конструироваться в единую схему
@NoArgsConstructor // делает аннотацию выше опционально. не выдаст ошибку если прам. не обязателен
@JsonInclude(JsonInclude.Include.NON_DEFAULT) //дефолтные прам могут быть не обязат

public class ContactDto {
    @JsonProperty("id")
    private  int id;

    @JsonProperty("firstName")
    private  String firstName;

    @JsonProperty("lastName")
    private  String lastName;

    @JsonProperty("description")
    private  String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    // достает обьекты
    public String getFirstName() {
        return firstName;
    }
    //собирает оьекты из боди
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
