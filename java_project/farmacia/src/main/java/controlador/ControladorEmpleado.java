/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controlador;

import dao.EmpleadoDAO;
import dao.VentaDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.CargoEmpleado;
import modelo.Cliente;
import modelo.Empleado;
import modelo.Farmacia;

/**
 *
 * @author Kevin Salazar
 */
public class ControladorEmpleado extends HttpServlet {

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
                Farmacia f = (Farmacia) request.getSession().getAttribute("farmacia");
                for(Empleado emp: f.getEmpleados()){
                    System.out.println(emp.getNombre());
                }
                request.getRequestDispatcher("./pages/empleado/index.jsp").forward(request, response);
                break;
            case "listar":
                request.getRequestDispatcher("./pages/empleado/listar.jsp").forward(request, response);
                break;
            case "agregar":
                request.getRequestDispatcher("./pages/empleado/create.jsp").forward(request, response);
                break;
            case "editar":
                request.getRequestDispatcher("./pages/empleado/update.jsp").forward(request, response);
                break;
            case "eliminar":
                request.getRequestDispatcher("./pages/empleado/delete.jsp").forward(request, response);
                break;
            case "Guardar":
                Farmacia farmacia = (Farmacia) request.getSession().getAttribute("farmacia");
                
                String fecha = request.getParameter("txtFechaContratacion");
                Date laFecha= null;
                try{
                    SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
                    laFecha = format.parse(fecha);
                }catch(Exception exc){
                    
                }
                String cargo = request.getParameter("multiCargo"); 
                String cedula = request.getParameter("txtCedula");
                String nombre = request.getParameter("txtNombre");
                String apellido = request.getParameter("txtApellido");
                double salario = Double.parseDouble(request.getParameter("txtSalario"));
                String clave = request.getParameter("txtClave");
                Empleado emp = new Empleado(cargo, cedula, nombre, apellido, salario, laFecha, clave);
                
                EmpleadoDAO empDAO = new EmpleadoDAO();
                int respuesta = empDAO.create(emp);
                if (respuesta == 1) {
                    farmacia.getEmpleados().add(emp);
                    request.getSession().setAttribute("farmacia", farmacia);
                    request.getRequestDispatcher("./pages/empleado/index.jsp").forward(request, response);
                }
                else {
                    request.getSession().setAttribute("errorMjs", "Código de error: " + respuesta);
                    request.getRequestDispatcher("./pages/empleado/create.jsp").forward(request, response);
                }
                break;
            default:
                request.getRequestDispatcher("./pages/empleado/index.jsp").forward(request, response);
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
