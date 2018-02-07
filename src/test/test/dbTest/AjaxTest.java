package dbTest;
import by.skoriyVladislav.service.ServiceFactory;
import by.skoriyVladislav.service.exception.ServiceException;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class AjaxTest {
    private Logger logger = Logger.getLogger(AjaxTest.class);
    @Test
    public void testLoginForAjaxReq_admin_in_DB() {
        String login = "admin";
        try {
            assertEquals(ServiceFactory.getInstance().getUserService().loginInDataBase(login), true);
        } catch (ServiceException ex) {
            logger.error("Cannot connect the database in test method (testLoginForAjaxReq).");
        }
    }

    @Test
    public void testLoginForAjaxReq_1123admin_not_in_DB() {
        String login = "1123admin";
        try {
            assertEquals(ServiceFactory.getInstance().getUserService().loginInDataBase(login), false);
        } catch (ServiceException ex) {
            logger.error("Cannot connect the database in test method (testLoginForAjaxReq).");
        }
    }

    @Test
    public void testBetSizeForAjaxReq_0() {
        String login = "admin";
        try {
            assertEquals(ServiceFactory.getInstance().getUserService().checkBalanceForBet(login, BigDecimal.ZERO), true);
        } catch (ServiceException ex) {
            logger.error("Cannot connect the database in test method (testLoginForAjaxReq).");
        }
    }

    @Test
    public void testBetSizeForAjaxReq_9999999() {
        BigDecimal sizeBet = BigDecimal.valueOf(Double.valueOf(9999999));
        String login = "admin";
        try {
            assertEquals(ServiceFactory.getInstance().getUserService().checkBalanceForBet(login, sizeBet), false);
        } catch (ServiceException ex) {
            logger.error("Cannot connect the database in test method (testLoginForAjaxReq).");
        }
    }
}
