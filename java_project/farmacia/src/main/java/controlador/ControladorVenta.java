/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Farmacia;

import modelo.Venta;

/**
 *
 * @author Kevin Salazar
 */
public class ControladorVenta extends HttpServlet {
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
                request.getRequestDispatcher("./pages/venta/index.jsp").forward(request, response);
                break;
            case "listar":
                request.getRequestDispatcher("./pages/venta/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("./pages/venta/create.jsp").forward(request, response);
                break;
            case "editar":
                request.getRequestDispatcher("./pages/venta/update.jsp").forward(request, response);
                break;
            case "eliminar":
                request.getRequestDispatcher("./pages/venta/delete.jsp").forward(request, response);
                break;
            case "Guardar":
                Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
                
                String fecha = request.getParameter("txtFechaEmision");
                Date laFecha= null;
                try{
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    laFecha = format.parse(fecha);
                }catch(Exception exc){
                    
                }
                
                String idCliente = request.getParameter("multiCliente");
                Cliente cliente = null;
                for(Cliente c:farmacia.getClientes()){
                    if(c.getId()==Integer.parseInt(idCliente)){
                        cliente = c;
                    }
                }
                String idEmpleado = request.getParameter("multiEmpleado");
                Empleado empleado = null;
                for(Empleado emp:farmacia.getEmpleados()){
                    if(emp.getId()==Integer.parseInt(idEmpleado)){
                        empleado = emp;
                    }
                }
                
                String total = request.getParameter("txtTotal");
                Venta venta = new Venta(laFecha, cliente, empleado, Double.parseDouble(total));
                
                VentaDAO ventaDAO = new VentaDAO();
                int respuesta = ventaDAO.create(venta);
                if (respuesta == 1) {
                    farmacia.getVentas().add(venta);
                    request.getSession().setAttribute("farmacia", farmacia);
                    request.getRequestDispatcher("./pages/venta/index.jsp").forward(request, response);
                }
                else {
                    request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
                    request.getRequestDispatcher("./pages/venta/create.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("./pages/venta/index.jsp").forward(request, response);
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
