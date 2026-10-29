
package ProjekUAS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author sigit
 */
public class DBconfig {
    private static Connection con;
    
    public static Connection sambungkan(){
        try {
            
            String url = "jdbc:mysql://localhost:3306/penggajian_karyawan";
            String user = "root";
            String pw = "";
            con = DriverManager.getConnection(url, user, pw);
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        
        return con;
    }
}
