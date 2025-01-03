import java.sql.Connection;
import java.sql.DriverManager;

public class DB {
    private static DB db;
    private Connection con;

    static DB getDb() {
        if(db == null)
            return db = new DB();
        return db;
    }

    public Connection getCon() {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mp3", "root", "naga@123");
            System.out.println("connected");
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return con;
    }
}
