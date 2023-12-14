package e2e.utils;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DataProviders {
    Faker faker = new Faker();
    @DataProvider
    public Iterator<Object[]>newContact(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{faker.name().firstName(),faker.name().lastName(),faker.lorem().paragraph()});
        list.add(new Object[]{"Nata","Koval","QaPro"});
        list.add(new Object[]{"Olena","Rev","QaPro"});
        return list.iterator();

    }
    @DataProvider
    public Iterator<Object[]> invalidLoginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"newtest","newtest@gmail.com", "with_invalid_email"});
        list.add(new Object[]{"newtest@gmail.com","newtesddt@gmail.com","with_invalid_password"});
        list.add(new Object[]{"1234556","344565677","with_invalid_data"});
        return list.iterator();
    }

}
