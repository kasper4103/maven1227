
import java.io.IOException;
import java.util.List;
import java.util.function.Supplier;
import javax.persistence.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

/**
 * Servlet implementation class SupplierServlet
 */
@WebServlet("/JPAQueryParamServlet")
public class JPAQueryParamServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public JPAQueryParamServlet() {
		super();
	}

/**
* @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
response)
*/
protected void doGet(HttpServletRequest request, HttpServletResponse
response) throws ServletException, IOException {
EntityManagerFactory entityManagerFactory =
Persistence.createEntityManagerFactory("MVC1227");
EntityManager entityManager =
entityManagerFactory.createEntityManager();
entityManager.getTransaction().begin();

String jpql="select e from Supplier e where e.supId >= :arg1";
Query query = entityManager.createQuery(jpql, Supplier.class);
query.setParameter("arg1", 150);
List<Supplier> result = query.getResultList();

request.setAttribute("supplier", result);
request.getRequestDispatcher("View.jsp").forward(request,
response);
entityManager.getTransaction().commit();
entityManager.close();
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