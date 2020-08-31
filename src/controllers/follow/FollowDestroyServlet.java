package controllers.follow;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.Employee;
import models.Follow;
import utils.DBUtil;

/**
 * Servlet implementation class FollowDestroyServlet
 */
@WebServlet("/follow/destroy")
public class FollowDestroyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowDestroyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


            EntityManager em = DBUtil.createEntityManager();



            Employee e = em.find(Employee.class, (Integer.parseInt(request.getParameter("id"))));
            Employee le = (Employee) request.getSession().getAttribute("login_employee");
            Follow f = (Follow) em.createNamedQuery("dropFollow", Follow.class)
                    .setParameter("femployee", e)
                    .setParameter("login", le)
                    .getSingleResult();


            em.getTransaction().begin();
            em.remove(f);
            em.getTransaction().commit();
            em.close();


            response.sendRedirect(request.getContextPath() + "/reports/index");

    }




}
