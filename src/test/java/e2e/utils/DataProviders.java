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
        list.add(new Object[]{"Oleksandr","Rashevchenko","QaPro"});
        list.add(new Object[]{"Oleksandr","Rashevchenko","QaPro"});
        return list.iterator();

    }

}
