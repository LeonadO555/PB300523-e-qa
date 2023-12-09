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
        list.add(new Object[]{faker.internet().uuid(),faker.internet().uuid(),faker.lorem().sentence()});
        list.add(new Object[]{"Natalie","Kovalchuk","I am a student"});
        list.add(new Object[]{faker.internet().uuid(),"Kovalchuk","I am a student"});
        return list.iterator();

    }
    @DataProvider
    public Iterator<Object[]>invalidLoginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"newtest@gmm.com","newtest@gmail.com"});
        list.add(new Object[]{"newtest@gmail.com","newtesddt@gmail.com"});
        list.add(new Object[]{"newtest@gmdsail.com","newtessdfddt@gmail.com"});
        return list.iterator();

    }

}
