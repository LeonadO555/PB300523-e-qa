package e2e;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.net.MalformedURLException;

public class TestBase {
        protected static ApplicationManager app = new ApplicationManager();

        @BeforeMethod
        public void setAppTest() {
            app.init(false);
        }

        @AfterMethod
    public void tearDown(){
        app.stop();
    }

}
