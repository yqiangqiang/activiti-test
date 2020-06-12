import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpaht：")
public class MyTest {

    @Test
    public void testPrint(){
        System.out.println("path:   "+this.getClass().getResource(""));
    }


}
