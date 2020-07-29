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
import models.Report;
import utils.DBUtil;

/**
 * Servlet implementation class FollowCreateServlet
 */
@WebServlet("/follow/create")
public class FollowCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FollowCreateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String _token = (String)request.getParameter("_token");
        if(_token != null && _token.equals(request.getSession().getId())) {
            EntityManager em = DBUtil.createEntityManager();

            Follow f = new Follow();

            request.getSession().setAttribute("employee_id", f.getId());

            f.setEmployee((Employee)request.getSession().getAttribute("login_employee"));
            f.setReport((Report)request.getSession().getAttribute("report_id"));

            em.getTransaction().begin();
            em.persist(f);
            em.getTransaction().commit();

            em.close();

            response.sendRedirect(request.getContextPath() + "/reports/index");

            }
        }
    }

