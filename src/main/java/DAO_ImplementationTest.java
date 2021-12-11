import jdk.jfr.Category;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class DAO_ImplementationTest {

    @InjectMocks
    private DAO_Implementation panelDao;

    @Mock
    private Connection connection;

    @Mock
    private ResultSet result;

    @Mock
    private PreparedStatement stmt;

    Customer cust;

    @BeforeEach
    public void setup() throws SQLException, ClassNotFoundException {
        MockitoAnnotations.openMocks(this);

        when(connection.prepareStatement(any(String.class))).thenReturn(stmt);

        cust = new Customer("112","Jasper Diaz", "15000", "2", "Savings-Deluxe");
    }

    @org.junit.jupiter.api.Test
    void add() throws SQLException, ClassNotFoundException {
        panelDao.add(cust);

        Mockito.verify(stmt).executeUpdate();
    }

    @org.junit.jupiter.api.Test
    void delete() {

        panelDao.add(cust);

        panelDao.delete(cust.getCustno());
        Mockito.verify(stmt,times(2)). executeUpdate();
    }

    @org.junit.jupiter.api.Test
    void display() throws SQLException, ClassNotFoundException {
        when(result.next()).thenReturn(true, false);
        when(result.getString("custno")).thenReturn("112");
        when(result.getString("custname")).thenReturn("Jasper Diaz");
        when(result.getString("cdep")).thenReturn("15000");
        when(result.getString("nyears")).thenReturn("2");
        when(result.getString("savtype")).thenReturn("Savings-Deluxe");

        Mockito.display();
        Mockito.verify(stmt).executeQuery();
    }

    @Test
    void Searchtest() throws SQLException, ClassNotFoundException {


        Mockito.when(connection.prepareStatement("Select * from customer where custno = ?")).thenReturn(stmt);

        Mockito.when(stmt.executeQuery()).thenReturn(result);
        Mockito.when(result.next()).thenReturn(true);
        Mockito.when(result.getString("custno")).thenReturn("112");
        Mockito.when(result.getString("custname")).thenReturn("Jasper Diaz");
        Mockito.when(result.first()).thenReturn(true);



        DAOImplements dao = new DAOImplements(connection);


        Category r = dao.search("112");
        assertEquals("112", r.getCatcode());







    }





    @Test
    void Searchtestnorecord() throws SQLException, ClassNotFoundException {


        Mockito.when(connection.prepareStatement("Select * from customer where custno = ?")).thenReturn(stmt);

        Mockito.when(stmt.executeQuery()).thenReturn(result);
        Mockito.when(result.next()).thenReturn(true);
        Mockito.when(result.getString("custno")).thenReturn("Norecord");
        Mockito.when(result.getString("custname")).thenReturn("Norecord");




        //DAOImplements dao = new DAOImplements(connection);

        Category r = panelDao.search("Norecord");

        // Category r = dao.search("Norecord");

        Assertions.assertNull(r);








    }


    @org.junit.jupiter.api.Test
    void display() throws SQLException, ClassNotFoundException {


        when(result.next()).thenReturn(true,false);
        when(result.getString("custno")).thenReturn("112");
        when(result.getString("custname")).thenReturn("Jasper Diaz");




        Mockito.doReturn(result).when(stmt).executeQuery();



        panelDao.display();



        Mockito.verify(stmt).executeQuery();





    }

}