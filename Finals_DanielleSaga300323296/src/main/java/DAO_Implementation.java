
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DAO_Implementation implements DAOInterface {

    public Connection123 con3;

    public DAO_Implementation(Connection123 con3) throws SQLException {
        this.con3 = con3;
    }


    @Override
    public void add(Customer cust) throws ClassNotFoundException, SQLException {
        String quer1 = "INSERT INTO Customer VALUES ( ?, ?, ?, ?, ? )";
        PreparedStatement query = con3.connect().prepareStatement(quer1);

        query.setString(1,cust.getCustno());
        query.setString(2,cust.getCustname());
        query.setString(3,cust.getCdep());
        query.setString(4,cust.getNyears());
        query.setString(5,cust.getSavtype());

        query.executeUpdate();

        System.out.println("One record added");
    }

    @Override
    public Customer edit(Customer cust, String ccust) throws SQLException, ClassNotFoundException{
        PreparedStatement query;
        query = con3.connect().prepareStatement("Update customer set custno=?, custname=?, cdep=?, nyears=?, savtype=? where custno = ?");
        query.setString(1, cust.getCustno());
        query.setString(2,cust.getCustname());
        query.setString(3,cust.getCdep());
        query.setString(4,cust.getNyears());
        query.setString(5,cust.getSavtype());
        query.setString(6,ccust);

        query.executeUpdate();

        System.out.println("One record edited");

        return cust;
    }

    @Override
    public void delete(String custno) throws SQLException{

        String quer1 = "Delete from Customer where custno = ?";
        PreparedStatement query = null;
        try {
            query = con3.connect().prepareStatement(quer1);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        query.setString(1,custno);
        query.executeUpdate();

        System.out.println("One record deleted");

    }

    @Override
    public void display() throws ClassNotFoundException, SQLException {
        String quer1 = "Select * from customer";
        PreparedStatement query = con3.connect().prepareStatement(quer1);

        ResultSet rs = query.executeQuery();

        Customer obj1;

        while (rs.next()) {
            obj1 = new Customer(rs.getString("custno"), rs.getString("custname"));

            System.out.print("Projected table of customer number " + obj1.getCustno() + " ");
            System.out.print("Name: " + obj1.getCustname());
        }
    }

    @Override
    public Customer search(String custno) throws SQLException {
        String quer1 = "Select * from savings where custno = ?";
        PreparedStatement query = null;
        try {
            query = con3.connect().prepareStatement(quer1, ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        query.setString(1,custno);

        ResultSet rs = query.executeQuery();

        if (!rs.first()) {
            System.out.print("Record not existing");
            return null;
        }

        Customer obj1=null;
        obj1 = new Customer(rs.getString("custno"), rs.getString("custname"), rs.getString("cdep"), rs.getString("nyears"), rs.getString("savtype"));

        return obj1;
    }
}
