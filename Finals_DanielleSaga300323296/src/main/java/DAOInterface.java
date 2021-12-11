
import java.sql.SQLException;

public interface DAOInterface {

    public void add(Customer cust) throws ClassNotFoundException, SQLException;
    public Customer edit(Customer cust, String ccust) throws SQLException, ClassNotFoundException;
    public void delete(String custno) throws SQLException;
    public void display() throws ClassNotFoundException, SQLException;
    public Customer search(String custno) throws SQLException;
}
