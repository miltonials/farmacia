/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.ProductoDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Farmaceutica;
import modelo.Farmacia;
import modelo.Producto;
import modelo.TipoProducto;

/**
 *
 * @author milto
 */
public class ControladorProducto extends HttpServlet {
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
                request.getRequestDispatcher("./pages/producto/index.jsp").forward(request, response);
                break;
            case "listar":
                request.getRequestDispatcher("./pages/producto/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("./pages/producto/create.jsp").forward(request, response);
                break;
            case "editar":
                request.getRequestDispatcher("./pages/producto/update.jsp").forward(request, response);
                break;
            case "eliminar":
                request.getRequestDispatcher("./pages/producto/delete.jsp").forward(request, response);
                break;
            case "Guardar":
                Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
                
                String nombre = request.getParameter("txtNombreProducto");
                String idTipoProducto = request.getParameter("multiTipoProducto");
                //nombreTipoProducto = nombreTipoProducto.toLowerCase();
                TipoProducto tipoProducto = farmacia.buscarTipoProductoPorId(Integer.parseInt(idTipoProducto));
                String descripcion = request.getParameter("txtDescripcion");
                String precio = request.getParameter("txtPrecio");
                String nombreFarmaceutica = request.getParameter("multiFarmaceutica");
                Farmaceutica farmaceutica = farmacia.buscarFarmaceuticaPorId(Integer.parseInt(nombreFarmaceutica));
                String cantidad = request.getParameter("txtCantidad");
                // int id, Farmaceutica farmaceutica, TipoProducto tipo, String nombre, String descripcion, double precio, int cantidadStock
                Producto producto = new Producto(farmaceutica, tipoProducto, nombre, descripcion, Double.parseDouble(precio), Integer.parseInt(cantidad));
                
                ProductoDAO productoDAO = new ProductoDAO();
                int respuesta = productoDAO.create(producto);
                if (respuesta == 1) {
                    farmacia.getProductos().add(producto);
                    request.getSession().setAttribute("farmacia", farmacia);
                    request.getRequestDispatcher("./pages/producto/index.jsp").forward(request, response);
                }
                else {
                    request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
                    request.getRequestDispatcher("./pages/producto/create.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("./pages/producto/index.jsp").forward(request, response);
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

}
