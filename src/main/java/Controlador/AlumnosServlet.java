/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.Alumno;
import Modelo.Utilidades;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author User
 */
public class AlumnosServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private String rutaFichero;
    private ArrayList<Alumno> daw_a2 = null;
    private ArrayList<Alumno> daw_b2 = null;
    private ArrayList<String> grupos = null;
    
    public void init(ServletConfig config) throws ServletException {
        daw_a2 = new ArrayList<Alumno>();
        daw_b2 = new ArrayList<Alumno>();
        grupos = new ArrayList<String>();
        rutaFichero = config.getServletContext().getRealPath("").concat(File.separator).concat("ficheros");
        grupos.add("2daw_a");
        grupos.add("2daw_b");
        try {
            daw_a2 = Utilidades.getProductos(rutaFichero.concat(File.separator).concat("2daw_a.txt"));
            daw_b2 = Utilidades.getProductos(rutaFichero.concat(File.separator).concat("2daw_b.txt"));
        } catch (IOException ex) {
            Logger.getLogger(AlumnosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AlumnosServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AlumnosServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
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
        String grupoSeleccionado;
        if(request.getParameter("grupo")!=null)grupoSeleccionado=request.getParameter("grupo");
        else grupoSeleccionado=grupos.get(0);
        if(grupoSeleccionado.equals("2daw_a"))request.setAttribute("daw_a2", daw_a2);
        else request.setAttribute("daw_b2", daw_b2);
        request.setAttribute("grupos",grupos);
        request.setAttribute("grupo", grupoSeleccionado);
        request.getRequestDispatcher("vista/Alumnos.jsp").forward(request, response);
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
        ArrayList<Alumno> seleccionados = new ArrayList<Alumno>();
        String grupoSeleccionado=request.getParameter("grupo");
        Alumno a=null;
        switch(grupoSeleccionado){
            case "2daw_a":
                for(Alumno al: daw_a2){
                    if(request.getParameter(String.valueOf(al.getId()))!=null){
                        a = new Alumno(al.getId(),al.getNombre(),al.getApellidos(),al.getEmail());
                        seleccionados.add(a);
                    }
                }
                break;
            case "2daw_b":
                for(Alumno al: daw_b2){
                    if(request.getParameter(String.valueOf(al.getId()))!=null){
                        a = new Alumno(al.getId(),al.getNombre(),al.getApellidos(),al.getEmail());
                        seleccionados.add(a);
                    }
                }
                break;
        }
        request.setAttribute("grupo", grupoSeleccionado);
        request.setAttribute("seleccionados", seleccionados);
        request.getRequestDispatcher("vista/mensajeAlumnos.jsp").forward(request, response);
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
