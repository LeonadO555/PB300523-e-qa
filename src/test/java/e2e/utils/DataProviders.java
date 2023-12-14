package e2e.utils;

import com.github.javafaker.Faker;
import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProviders {

    Faker faker = new Faker();
    @DataProvider
    public Iterator<Object[]> newContact(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{faker.name().firstName(),faker.name().lastName(),faker.lorem().paragraph()});
        list.add(new Object[]{faker.internet().uuid(),faker.internet().uuid(),faker.lorem().sentence()});
        list.add(new Object[]{faker.internet().uuid(),"Daniel","Hello"});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> invalidDataForLogin(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"newtest@gmail.com","newtest@gmail.com"});
        list.add(new Object[]{"ne1111wtest@gmail.com","newtest@gmail.com"});
        list.add(new Object[]{"newtest@gmail.com","new11111est@gmail.com"});
        list.add(new Object[]{"123123newtest@gmail.com","new11111est@gmail.com"});
        return list.iterator();
    }

}
