/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.TipoProductoDAO;
import dao.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Farmacia;
import modelo.TipoProducto;
import modelo.Venta;

/**
 *
 * @author Kevin Salazar
 */
public class ControladorTipoProducto extends HttpServlet {

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
                request.getRequestDispatcher("./pages/tipo_producto/index.jsp").forward(request, response);
                break;
            case "listar":
                request.getRequestDispatcher("./pages/tipo_producto/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("./pages/tipo_producto/create.jsp").forward(request, response);
                break;
            case "paginaEditar":
                cargarPaginaEditar(request, response);
                break;
            case "paginaEliminar":
                cargarPaginaEliminar(request, response);
                break;
            case "update":
                actualizarTipo(request, response);
                break;
            case "delete":
                eliminarTipo(request, response);
                break;
            case "Guardar":
                Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
                
                String nombre = request.getParameter("txtNombre");
                TipoProducto tp = new TipoProducto(nombre);
                
                TipoProductoDAO tipoProductoDAO = new TipoProductoDAO();
                int respuesta = tipoProductoDAO.create(tp);
                if (respuesta == 1) {
                    farmacia.getTiposProductos().add(tp);
                    request.getSession().setAttribute("farmacia", farmacia);
                    request.getRequestDispatcher("./pages/tipo_producto/index.jsp").forward(request, response);
                }
                else {
                    request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
                    request.getRequestDispatcher("./pages/tipo_producto/create.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("./pages/tipo_producto/index.jsp").forward(request, response);
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
    private void cargarPaginaEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        TipoProducto tp = miFarmacia.buscarTipoProductoPorId(Integer.parseInt(id));
        
        request.getSession().setAttribute("tipoModificar", tp);
        request.getRequestDispatcher("./pages/tipo_producto/update.jsp").forward(request, response);
    }

    private void cargarPaginaEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        TipoProducto tipo = miFarmacia.buscarTipoProductoPorId(Integer.parseInt(request.getParameter("id")));
        
        request.getSession().setAttribute("tipoModificar", tipo);

        request.getRequestDispatcher("./pages/tipo_producto/delete.jsp").forward(request, response);
    }
    
    private void actualizarTipo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");

        String nombre = request.getParameter("txtNombre");
        
        TipoProducto tipo = (TipoProducto) request.getSession().getAttribute("tipoModificar");
        tipo.setNombre(nombre);
        
        TipoProductoDAO tipoDAO = new TipoProductoDAO();
        int respuesta = tipoDAO.update(tipo);

        if (respuesta == 1) {
            request.getSession().setAttribute("farmacia", farmacia);
            request.getRequestDispatcher("./pages/tipo_producto/index.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
            request.getRequestDispatcher("./pages/tipo_producto/update.jsp").forward(request, response);
        }

    }
    
    private void eliminarTipo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        TipoProducto tipo = (TipoProducto) request.getSession().getAttribute("tipoModificar");
        TipoProductoDAO tipoDAO = new TipoProductoDAO();
        int respuesta = tipoDAO.delete(tipo);
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        miFarmacia.getTiposProductos().remove(tipo);
        
        request.getSession().setAttribute("farmacia", miFarmacia);
        request.getRequestDispatcher("./pages/tipo_producto/index.jsp").forward(request, response);
    }
}
