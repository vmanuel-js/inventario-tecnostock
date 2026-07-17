package com.inventarioweb.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

import com.inventarioejb.bl.CategoriaBLImpl;
import com.inventarioejb.bl.ProductoBLImpl;
import com.inventarioejb.entity.Categoria;

@WebServlet("/producto")
public class ProductoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ProductoBLImpl productoBL = new ProductoBLImpl();
    private CategoriaBLImpl categoriaBL = new CategoriaBLImpl();

    public ProductoController() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        if (opcion == null) opcion = "";
        switch (opcion) {
            case "formularioRegistrar":
                formularioRegistrar(request, response);
                break;
            default:
                response.sendRedirect("jsp/nuevoProducto.jsp");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        if (opcion == null) opcion = "";
        switch (opcion) {
            case "registrar":
                registrar(request, response);
                break;
        }
    }

    private void formularioRegistrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            List<Categoria> categorias = categoriaBL.listarCategorias();
            request.setAttribute("listaCategorias", categorias);
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/jsp/nuevoProducto.jsp");
            rd.forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void registrar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre      = request.getParameter("nombre");
            BigDecimal precio  = new BigDecimal(request.getParameter("precio"));
            int stock          = Integer.parseInt(request.getParameter("stock"));
            int idCategoria    = Integer.parseInt(request.getParameter("idCategoria"));
            productoBL.registrarProducto(nombre, precio, stock, idCategoria);
            response.sendRedirect("producto?opcion=formularioRegistrar&mensaje=registrado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
