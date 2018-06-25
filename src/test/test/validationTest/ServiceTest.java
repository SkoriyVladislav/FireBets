package validationTest;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.ServiceValidator;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ServiceTest {
    @Test
    public void testValidName_Влад() {
        String validName = "Влад";
        assertEquals(ServiceValidator.isValidName(validName), true);
    }

    @Test
    public void testInvalidName_vlad123() {
        String validName = "vlad123";
        assertEquals(ServiceValidator.isValidName(validName), false);
    }
}
