<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.inventarioejb.entity.Sucursal" %>
<%
    Sucursal sucursal = (Sucursal) request.getAttribute("sucursal");
    if (sucursal == null) {
        response.sendRedirect("../sucursal?opcion=listar");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Actualizar Sucursal - TecnoStock</title>
</head>
<body>
    <h1>Actualizar Sucursal</h1>
    <a href="../sucursal?opcion=listar">← Volver a Gestión de Sucursales</a>
    <hr/>

    <form action="../sucursal" method="POST">
        <input type="hidden" name="opcion" value="actualizar"/>
        <input type="hidden" name="id" value="<%= sucursal.getIdSucursal() %>"/>

        <div style="padding: 8px 0px;">
            <label>ID:</label><br/>
            <input type="text" value="<%= sucursal.getIdSucursal() %>" disabled/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Nombre:</label><br/>
            <input type="text" name="nombre" value="<%= sucursal.getNombre() %>" maxlength="100" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Dirección:</label><br/>
            <input type="text" name="direccion" value="<%= sucursal.getDireccion() %>" maxlength="200" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Teléfono:</label><br/>
            <input type="text" name="telefono" value="<%= sucursal.getTelefono() %>" maxlength="15" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Estado:</label><br/>
            <select name="estado">
                <option value="Activo" <%= "Activo".equals(sucursal.getEstado()) ? "selected" : "" %>>Activo</option>
                <option value="Inactivo" <%= "Inactivo".equals(sucursal.getEstado()) ? "selected" : "" %>>Inactivo</option>
            </select>
        </div>

        <div style="padding: 12px 0px;">
            <button type="submit">Guardar</button>
            &nbsp;
            <a href="../sucursal?opcion=listar"><button type="button">Cancelar</button></a>
        </div>
    </form>
</body>
</html>