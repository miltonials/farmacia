/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Empleado;
import modelo.EmpleadoDAO;

/**
 *
 * @author milto
 */
public class Controlador extends HttpServlet {

    private EmpleadoDAO dao = new EmpleadoDAO();
    private Empleado empleado;
    private int resultado;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.getRequestDispatcher("./index.jsp").forward(request, response);
        dao.cerrarConexion();
}
    
/**
 * Handles the HTTP <code>POST</code> method.
 *
 * @param request servlet request
 * @param response servlet response
 * @throws ServletException if a servlet-specific error occurs
 * @throws IOException if an I/O error occurs
 */
@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        String usuario = request.getParameter("txt_usuario");
        String contrasena = request.getParameter("txt_contrasena");

        if (accion.equals("btn_iniciarSesion")) {
            empleado = new Empleado(usuario, contrasena);
            resultado = dao.validar(empleado);
            System.out.println(resultado);
            if (resultado > 0) {
                request.getSession().setAttribute("empleado", empleado);
                request.getRequestDispatcher("./pages/home.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("./index.jsp").forward(request, response);
                dao.cerrarConexion();
            }
        }
    }
}
