
import java.io.IOException;
import java.sql.DriverManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import com.google.gson.*;
import java.sql.*;
import model.Student;

/**
 * Servlet implementation class StudentAddServlet
 */
@WebServlet("/StudentAddServlet")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentAddServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String addStudent(Student s) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/classicmodels?serverTimezone=CST&useUnicode=true&characterEncoding=utf8",
					"root", "1234");
			String sql = "INSERT INTO classicmodels.student(sid,name,age)VALUES(?,?,?);";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, s.getSid());
			st.setString(2, s.getName());
			st.setInt(3, s.getAge());
			int rs = st.executeUpdate();
			if (rs > 0)
				return "success";
			else
				return "Failed";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				conn.close();
			} catch (SQLException ex) {
				System.out.println("Connection error:" + ex.getMessage());
				return ex.getMessage();
			}
		}
		return "Failed";
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String jsstr = request.getParameter("sdata");
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		Student student = gson.fromJson(jsstr, Student.class);
		String rs = addStudent(student);
		response.getWriter().append("Served at: ").append(rs);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
