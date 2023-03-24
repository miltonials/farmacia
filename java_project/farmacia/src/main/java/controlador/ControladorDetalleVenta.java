/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.CargoEmpleadoDAO;
import dao.DetalleVentaDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CargoEmpleado;
import modelo.DetalleVenta;
import modelo.Farmacia;
import modelo.Producto;
import modelo.Venta;

/**
 *
 * @author milto
 */
public class ControladorDetalleVenta extends HttpServlet {

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
                request.getRequestDispatcher("./pages/detalle_venta/index.jsp").forward(request, response);
                break;
            case "paginaCrear":
                request.getRequestDispatcher("./pages/detalle_venta/create.jsp").forward(request, response);
                break;
            case "paginaEditar":
                cargarPaginaEditar(request, response);
                break;
            case "paginaEliminar":
                cargarPaginaEliminar(request, response);
                break;
            case "create":
                registrarNuevoDetalle(request, response);
                break;
            case "update":
                actualizarDetalle(request, response);
                break;
            case "delete":
                eliminarDetalle(request, response);
                break;
            default:
                request.getRequestDispatcher("./pages/detalle_venta/index.jsp").forward(request, response);
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

    private void registrarNuevoDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        String idVenta = request.getParameter("multiVenta");
        String idProducto = request.getParameter("multiProducto");
        int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
        
        Venta venta = farmacia.buscarVentaPorId(Integer.parseInt(idVenta));
        Producto producto = farmacia.buscarProductoPorId(Integer.parseInt(idProducto));
        
        
        DetalleVenta detalle = new DetalleVenta(venta, producto, cantidad, producto.getPrecio());

        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
        int respuesta = detalleVentaDAO.create(detalle);

        if (respuesta == 1) {
            venta.getDetalleVenta().add(detalle);
            request.getSession().setAttribute("farmacia", farmacia);
            request.getRequestDispatcher("./pages/detalle_venta/index.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
            request.getRequestDispatcher("./pages/detalle_venta/create.jsp").forward(request, response);
        }
    }

    private void actualizarDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        DetalleVenta detalle = (DetalleVenta) request.getSession().getAttribute("detalleVentaModificar");
        double precioUnitario = Double.parseDouble(request.getParameter("txtPrecioUnitario"));
        int cantidad = Integer.parseInt(request.getParameter("txtCantidad"));
        detalle.setCantidad(cantidad);
        detalle.setPrecioUnitario(precioUnitario);
        
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
        int respuesta = detalleVentaDAO.update(detalle);

        if (respuesta == 1) {
            request.getSession().setAttribute("errorMjs", "");
            farmacia = new Farmacia(farmacia.getEmpleadoActual());
            request.getSession().setAttribute("farmacia", farmacia);
            request.getRequestDispatcher("./pages/detalle_venta/index.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
            request.getRequestDispatcher("./pages/detalle_venta/update.jsp").forward(request, response);
        }

    }

    private void cargarPaginaEditar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        Producto producto = (Producto) miFarmacia.buscarProductoPorId(Integer.parseInt(request.getParameter("idProducto")));
        Venta venta = (Venta) miFarmacia.buscarVentaPorId(Integer.parseInt(request.getParameter("idVenta")));
        DetalleVenta detalleVenta = venta.buscarDetalleVenta(producto.getId());
        System.out.println(detalleVenta.toString());
        request.getSession().setAttribute("detalleVentaModificar", detalleVenta);
        request.getRequestDispatcher("./pages/detalle_venta/update.jsp").forward(request, response);
    }

    private void cargarPaginaEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        Producto producto = (Producto) miFarmacia.buscarProductoPorId(Integer.parseInt(request.getParameter("idProducto")));
        Venta venta = (Venta) miFarmacia.buscarVentaPorId(Integer.parseInt(request.getParameter("idVenta")));
        DetalleVenta detalleVenta = venta.buscarDetalleVenta(producto.getId());
        
        System.out.println("detalle de venta eliminar: " + detalleVenta);
        
        request.getSession().setAttribute("detalleVentaEliminar", detalleVenta);

        request.getRequestDispatcher("./pages/detalle_venta/delete.jsp").forward(request, response);
    }

    private void eliminarDetalle(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        DetalleVenta detalleVenta = (DetalleVenta) request.getSession().getAttribute("detalleVentaEliminar");
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO();
        int respuesta = detalleVentaDAO.delete(detalleVenta);
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        miFarmacia.buscarVentaPorId(detalleVenta.getVenta().getId()).getDetalleVenta().remove(detalleVenta);
        
        request.getSession().setAttribute("farmacia", miFarmacia);
        request.getRequestDispatcher("./pages/detalle_venta/index.jsp").forward(request, response);
    }
}
