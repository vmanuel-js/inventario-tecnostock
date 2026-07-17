<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.inventarioejb.entity.Categoria" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nuevo Producto - TecnoStock</title>
</head>
<body>
    <h1>Nuevo Producto</h1>
    <a href="./sucursal?opcion=listar">Volver a Gestión de Sucursales</a>
    <hr/>

    <% String mensaje = request.getParameter("mensaje"); %>
    <% if ("registrado".equals(mensaje)) { %>
        <p style="color:green;">Producto registrado correctamente.</p>
    <% } %>

    <form action="producto" method="POST">
        <input type="hidden" name="opcion" value="registrar"/>

        <div style="padding: 8px 0px;">
            <label>Nombre:</label><br/>
            <input type="text" name="nombre" maxlength="100" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Precio:</label><br/>
            <input type="number" name="precio" step="0.01" min="0" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Stock:</label><br/>
            <input type="number" name="stock" min="0" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Categoría:</label><br/>
            <select name="idCategoria" required>
                <option value="">-- Seleccione --</option>
                <%
                    List<Categoria> categorias = (List<Categoria>) request.getAttribute("listaCategorias");
                    if (categorias != null) {
                        for (Categoria c : categorias) {
                %>
                    <option value="<%= c.getIdCategoria() %>"><%= c.getNombre() %></option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <div style="padding: 12px 0px;">
            <button type="submit">Guardar</button>
            &nbsp;
            <a href="./sucursal?opcion=listar"><button type="button">Cancelar</button></a>
        </div>
    </form>
</body>
</html>