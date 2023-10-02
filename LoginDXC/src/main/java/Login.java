import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public Login() {
        super();
    }
    //Password hashing
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException
    {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash)
    {
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64)
        {
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession();
		RequestDispatcher dispatcher=null;
		try {
			String hashedPassword = toHexString(getSHA(password));
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dxc","root","passwordroot");
			PreparedStatement pst = conn.prepareStatement("SELECT * from useraccounts where username = ? and password =?");
			pst.setString(1, username);
			pst.setString(2, hashedPassword);
			
			ResultSet rs = pst.executeQuery();
			if(rs.next()) 
			{
				if(rs.getString("accesslevel").equals("User")){
					session.setAttribute("role","User");
				}
				if(rs.getString("accesslevel").equals("Manager")){
					session.setAttribute("role","Manager");
				}
				session.setAttribute("name",rs.getString("username"));
				dispatcher = request.getRequestDispatcher("WelcomePage.jsp");
				dispatcher.forward(request, response);
			}
			else {
				dispatcher = request.getRequestDispatcher("LoginPage.jsp");
				request.setAttribute("status", "failed");
				dispatcher.forward(request, response);
			}
			conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}