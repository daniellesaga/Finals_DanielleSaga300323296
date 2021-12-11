import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.SQLException;

@Controller
public class InventoryController {
    @RequestMapping(value = "/customer", method = RequestMethod.GET)
    public String showCustomerpage(ModelMap model) throws ClassNotFoundException, SQLException {
        return "customer";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showCustomerPage2(ModelMap model) throws ClassNotFoundException, SQLException {
        return "category";
    }
}
