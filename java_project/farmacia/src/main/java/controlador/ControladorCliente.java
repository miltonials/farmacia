/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.ClienteDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Cliente;
import modelo.Farmacia;

/**
 *
 * @author Andy Porras
 */
public class ControladorCliente extends HttpServlet {

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
                request.getRequestDispatcher("./pages/cliente/index.jsp").forward(request, response);
                break;
            case "listar":
                request.getRequestDispatcher("./pages/cliente/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("./pages/cliente/create.jsp").forward(request, response);
                break;
            case "paginaEditar":
                request.getRequestDispatcher("./pages/cliente/update.jsp").forward(request, response);
                break;
            case "paginaEliminar":
                cargarPaginaEliminar(request, response);
                break;
            case "delete":
                eliminarCliente(request, response);
                break;
            case "Guardar":
                Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
                String fecha_nacimiento = request.getParameter("txtFechaNacimiento");
                Date laFecha = null;
                try {
                    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
                    laFecha = formato.parse(fecha_nacimiento);
                } catch (Exception e) {
                    System.out.println("Error al convertir la fecha");
                }

                String nombre = request.getParameter("txtNombre");
                String apellido = request.getParameter("txtApellido");
                String telefono = request.getParameter("txtTelefono");
                String correo_electronico = request.getParameter("txtCorreoElectronico");
                //String fecha_nacimiento = request.getParameter("txtFechaNacimiento");
                String genero = request.getParameter("txtGenero");

                Cliente cliente = new Cliente(nombre,apellido,telefono,correo_electronico,laFecha,genero);                
                ClienteDAO clienteDAO = new ClienteDAO();
                int respuesta = clienteDAO.create(cliente);
                if (respuesta == 1 ) {
                    request.getSession().setAttribute("errorMjs","");
                    farmacia.getClientes().add(cliente);
                    request.getSession().setAttribute("farmacia", cliente);
                    request.getRequestDispatcher("./pages/cliente/index.jsp").forward(request, response);
                }
                else {
                    request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
                    request.getRequestDispatcher("./pages/cliente/create.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("./pages/cliente/index.jsp").forward(request, response);
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
    private void cargarPaginaEliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        Cliente cliente = miFarmacia.buscarClientePorId(Integer.parseInt(id));

        request.getSession().setAttribute("clienteModificar", cliente);

        request.getRequestDispatcher("./pages/cliente/delete.jsp").forward(request, response);
    }
    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cliente cliente = (Cliente) request.getSession().getAttribute("clienteModificar");
        ClienteDAO clienteDAO = new ClienteDAO();
        int respuesta = clienteDAO.delete(cliente);
        Farmacia miFarmacia = (Farmacia) request.getSession().getAttribute("farmacia");
        miFarmacia.getClientes().remove(cliente);
        
        request.getSession().setAttribute("farmacia", miFarmacia);
        request.getRequestDispatcher("./pages/cliente/index.jsp").forward(request, response);
    }
}
