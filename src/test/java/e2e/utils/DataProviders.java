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
        list.add(new Object[]{faker.internet().uuid(),faker.name().lastName(),faker.lorem().paragraph()});
//        list.add(new Object[]{faker.internet().uuid(),faker.internet().uuid(),faker.lorem().sentence()});
//        list.add(new Object[]{faker.internet().uuid(),"Mikhailov","I am a teacher"});
        return list.iterator();
    }

    @DataProvider
    public Iterator<Object[]> invalidLoginData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"Steven","newtest@gmail.com", "with_invalid_email"});
        list.add(new Object[]{"newtest@gmail.com",faker.internet().uuid(), "with_invalid_password"});
        list.add(new Object[]{"213123","234234", "with_invalid_data"});
        return list.iterator();
    }
    @DataProvider
    public Iterator<Object[]> newUserData(){
        List<Object[]> list = new ArrayList<>();
        list.add(new Object[]{"new10t@gmail.com", "new14t@gmail.com"});
        return list.iterator();
    }
}
