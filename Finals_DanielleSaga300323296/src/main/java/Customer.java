public class Customer {

    private String custno;
    private String custname;
    private String cdep;
    private String nyears;
    private String savtype;


    Customer(String custno, String custname, String cdep, String nyears, String savtype){
        this.custno = custno;
        this.custname = custname;
        this.cdep = cdep;
        this.nyears = nyears;
        this.savtype = savtype;
    }

    public Customer(String custno, String custname) {
    }

    public String getCustno() { return custno; }

    public void setCustno(String custno) { this.custno = custno; }

    public String getCustname() {return custname; }

    public void setCustname() {this.custname = custname;}

    public String getCdep() { return cdep; }

    public void setCdep() { this.cdep = cdep; }

    public String getNyears() { return nyears; }

    public void setNyears() {this.nyears = nyears; }

    public String getSavtype() {return savtype; }

    public void setSavtype() {this.savtype = savtype;}
}
