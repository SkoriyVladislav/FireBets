package validationTest;
import by.skoriyVladislav.service.ServiceFactory;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServiceTest {
    @Test
    public void testValidName_Влад() {
        String validName = "Влад";
        assertEquals(ServiceFactory.isValidName(validName), true);
    }

    @Test
    public void testInvalidName_vlad123() {
        String validName = "vlad123";
        assertEquals(ServiceFactory.isValidName(validName), false);
    }
}
