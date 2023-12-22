package e2e.utils;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class DataProviders {

    Faker faker = new Faker();

    //[{"Georgiy","Manolov","QA Pro curs"},{"Georgiy","Manolov","QA Pro curs"},{"Georgiy","Manolov","QA Pro curs"}
    @DataProvider
    public Iterator<Object[]> newContact(){

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{faker.name().firstName(),faker.name().lastName(),faker.lorem().paragraph()});
        list.add(new Object[]{faker.internet().uuid(),faker.internet().uuid(),faker.lorem().sentence()});
        list.add(new Object[]{faker.internet().uuid(),"Manolov","QA pro course"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> userCanNotLoginTest(){

        List<Object[]> list = new ArrayList<>();

        list.add(new Object[]{"newtestgmail.com", "newtest@gmail.com"});
        list.add(new Object[]{"newtest@gmail.com", "newtestgmail.com"});
        list.add(new Object[]{"newtestgmail.com", "newtestgmail.com"});
        list.add(new Object[]{"newtestgmail.com", "newtest@gmail.com"});

        return list.iterator();
    }
}
