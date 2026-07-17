package com.inventarioweb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.inventarioejb.bl.SucursalBLImpl;
import com.inventarioejb.entity.Sucursal;

@WebServlet("/sucursal")
public class SucursalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SucursalBLImpl sucursalBL = new SucursalBLImpl();

    public SucursalController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion == null) opcion = "";
		switch(opcion) {
			case "listar":
				listar(request,response);
				break;
			case "formularioActualizar":
				formularioActualizar(request,response);
				break;
			case "eliminar":
				eliminar(request,response);
				break;
			case "buscarPorNombre":
				buscarPorNombreAjax(request,response);
				break;
			default:
			    listar(request, response);
			    break;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String opcion = request.getParameter("opcion");
		if (opcion == null) opcion = "";
		
		switch(opcion) {
			case "registrar":
				registrar(request,response);
				break;
			case "actualizar":
				actualizar(request,response);
				break;
		}
	}
	
	private void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			List<Sucursal> lista = sucursalBL.listar();
			request.setAttribute("listaSucursales", lista);
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/gestionSucursales.jsp");
            rd.forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void formularioActualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            Sucursal sucursal = sucursalBL.buscarPorId(id);
            request.setAttribute("sucursal", sucursal);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/actualizarSucursal.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void eliminar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            sucursalBL.eliminar(id);
            response.sendRedirect("sucursal?opcion=listar");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre    = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono  = request.getParameter("telefono");
            String estado    = request.getParameter("estado");
            sucursalBL.registrar(nombre, direccion, telefono, estado);
            response.sendRedirect("sucursal?opcion=listar&mensaje=registrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void actualizar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int id           = Integer.parseInt(request.getParameter("id"));
            String nombre    = request.getParameter("nombre");
            String direccion = request.getParameter("direccion");
            String telefono  = request.getParameter("telefono");
            String estado    = request.getParameter("estado");
            sucursalBL.actualizar(id, nombre, direccion, telefono, estado);
            response.sendRedirect("sucursal?opcion=listar&mensaje=actualizado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buscarPorNombreAjax(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombre");
            List<Sucursal> lista = sucursalBL.buscarPorNombre(nombre);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < lista.size(); i++) {
                Sucursal s = lista.get(i);
                json.append("{")
                    .append("\"idSucursal\":").append(s.getIdSucursal()).append(",")
                    .append("\"nombre\":\"").append(s.getNombre()).append("\",")
                    .append("\"direccion\":\"").append(s.getDireccion()).append("\",")
                    .append("\"telefono\":\"").append(s.getTelefono()).append("\",")
                    .append("\"estado\":\"").append(s.getEstado()).append("\"")
                    .append("}");
                if (i < lista.size() - 1) json.append(",");
            }
            json.append("]");
            response.getWriter().write(json.toString());
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(500);
        }
    }

}
