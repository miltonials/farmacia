/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.FarmaceuticaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Farmaceutica;
import modelo.Farmacia;

/**
 *
 * @author Andy Porras
 */
public class ControladorFarmaceutica extends HttpServlet {
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String accion = request.getParameter("accion");
        switch (accion){
            case "index":
                request.getRequestDispatcher("./pages/farmaceutica/index.jsp").forward(request, response);
                break;
            case "listar":
                request.getRequestDispatcher("./pages/farmaceutica/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("./pages/farmaceutica/create.jsp").forward(request, response);
                break;
            case "paginaEditar":
                request.getRequestDispatcher("./pages/farmaceutica/update.jsp").forward(request, response);
                break;
            case "paginaEliminar":
                request.getRequestDispatcher("./pages/farmaceutica/delete.jsp").forward(request, response);
                break;
            case "Guardar":
                Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
                
                String nombre = request.getParameter("txtNombreFarmaceutica");
                String telefono = request.getParameter("txtTelefono");
                String correo_electronico = request.getParameter("txtCorreoElectronico");
                Farmaceutica farmaceutica = new Farmaceutica(nombre,telefono,correo_electronico);
                
                FarmaceuticaDAO farmaceuticaDAO = new FarmaceuticaDAO();
                int respuesta = farmaceuticaDAO.create(farmaceutica);
                if (respuesta == 1 ) {
                    request.getSession().setAttribute("errorMjs","");
                    farmacia.getFarmaceuticas().add(farmaceutica);
                    request.getSession().setAttribute("farmacia", farmacia);
                    request.getRequestDispatcher("./pages/farmaceutica/index.jsp").forward(request, response);
                }
                else {
                    request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
                    request.getRequestDispatcher("./pages/farmaceutica/create.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("./pages/farmaceutica/index.jsp").forward(request, response);
                break;
        }
    } // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
