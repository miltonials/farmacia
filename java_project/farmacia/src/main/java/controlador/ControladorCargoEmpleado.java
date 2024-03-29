/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.CargoEmpleadoDAO;
import dao.ProductoDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CargoEmpleado;
import modelo.Farmaceutica;
import modelo.Farmacia;
import modelo.Producto;
import modelo.TipoProducto;

/**
 *
 * @author milto
 */
public class ControladorCargoEmpleado extends HttpServlet {

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
        switch (accion) {
            case "index":
                request.getRequestDispatcher("./pages/cargo_empleado/index.jsp").forward(request, response);
                break;
            case "paginaCrear":
                request.getRequestDispatcher("./pages/cargo_empleado/create.jsp").forward(request, response);
                break;
            case "paginaEditar":
                cargarPaginaEditar(request, response);
                break;
            case "paginaEliminar":
                cargarPaginaEliminar(request, response);
                break;
            case "create":
                registrarNuevoCargoEmpleado(request, response);
                break;
            case "update":
                actualizarCargoEmpleado(request, response);
                break;
            case "delete":
                eliminarCargoEmpleado(request, response);
                break;
            default:
                request.getRequestDispatcher("./pages/cargo_empleado/index.jsp").forward(request, response);
                break;
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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

    private void registrarNuevoCargoEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");

        String nombre = request.getParameter("txtNombreCargoEmpleado");
        CargoEmpleado cargoEmpleado = new CargoEmpleado(nombre);

        CargoEmpleadoDAO cargoEmpleadoDAO = new CargoEmpleadoDAO();
        int respuesta = cargoEmpleadoDAO.create(cargoEmpleado);

        if (respuesta == 1) {
            farmacia.getCargosEmpleados().add(cargoEmpleado);
            request.getSession().setAttribute("farmacia", farmacia);
            request.getRequestDispatcher("./pages/cargo_empleado/index.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
            request.getRequestDispatcher("./pages/cargo_empleado/create.jsp").forward(request, response);
        }
    }

    private void actualizarCargoEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");

        String nombre = request.getParameter("txtNombreCargoEmpleado");

        CargoEmpleado cargoEmpleado = (CargoEmpleado) request.getSession().getAttribute("cargoEmpleadoModificar");
        cargoEmpleado.setNombre(nombre);
        
        CargoEmpleadoDAO cargoEmpleadoDAO = new CargoEmpleadoDAO();
        int respuesta = cargoEmpleadoDAO.update(cargoEmpleado);

        if (respuesta == 1) {
            request.getSession().setAttribute("errorMjs", "");
            request.getSession().setAttribute("farmacia", farmacia);
            request.getRequestDispatcher("./pages/cargo_empleado/index.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
            request.getRequestDispatcher("./pages/cargo_empleado/update.jsp").forward(request, response);
        }

    }

    private void cargarPaginaEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        CargoEmpleado cargoEmpleado = miFarmacia.buscarCargoEmpleadoPorId(Integer.parseInt(id));

        request.getSession().setAttribute("cargoEmpleadoModificar", cargoEmpleado);
        request.getRequestDispatcher("./pages/cargo_empleado/update.jsp").forward(request, response);
    }

    private void cargarPaginaEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        CargoEmpleado cargoEmpleado = miFarmacia.buscarCargoEmpleadoPorId(Integer.parseInt(id));

        request.getSession().setAttribute("cargoEmpleadoModificar", cargoEmpleado);

        request.getRequestDispatcher("./pages/cargo_empleado/delete.jsp").forward(request, response);
    }

    private void eliminarCargoEmpleado(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        CargoEmpleado cargoEmpleado = (CargoEmpleado) request.getSession().getAttribute("cargoEmpleadoModificar");
        CargoEmpleadoDAO cargoEmpleadoDAO = new CargoEmpleadoDAO();
        int respuesta = cargoEmpleadoDAO.delete(cargoEmpleado);
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        miFarmacia.getCargosEmpleados().remove(cargoEmpleado);
        
        request.getSession().setAttribute("farmacia", miFarmacia);
        request.getRequestDispatcher("./pages/cargo_empleado/index.jsp").forward(request, response);
    }
}
