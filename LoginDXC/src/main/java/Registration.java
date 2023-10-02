import java.io.IOException;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public Registration() {
        super();
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        return md.digest(input.getBytes(StandardCharsets.UTF_8));
    }
     
    public static String toHexString(byte[] hash){
        BigInteger number = new BigInteger(1, hash);
        StringBuilder hexString = new StringBuilder(number.toString(16));
        while (hexString.length() < 64){
            hexString.insert(0, '0');
        }
        return hexString.toString();
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String accessLevel = request.getParameter("role");
		RequestDispatcher dispatcher=null;
		try {
			String hashedPassword = toHexString(getSHA(password));
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dxc","root","passwordroot");
			PreparedStatement pst = conn.prepareStatement("INSERT into useraccounts(username,password,accesslevel) values(?,?,?)");
			pst.setString(1, username);
			pst.setString(2, hashedPassword);	
			pst.setString(3, accessLevel);	
			int updateCount = pst.executeUpdate();
			if(updateCount>0) {
				request.setAttribute("status", "success");
				dispatcher = request.getRequestDispatcher("Registration.jsp");
			}
			dispatcher.forward(request, response);
			conn.close();
		}
		catch(SQLIntegrityConstraintViolationException e) 
		{
			request.setAttribute("status", "failed");				
			dispatcher = request.getRequestDispatcher("Registration.jsp");
			dispatcher.forward(request, response);
		}
		catch(Exception e){	
			e.printStackTrace();
		}
		
	}

}
