<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List, com.inventarioejb.entity.Sucursal" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Gestión de Sucursales - TecnoStock</title>
</head>
<body>
    <h1>Gestión de Sucursales</h1>
    <a href="producto?opcion=formularioRegistrar">Ir a Registro de Productos</a>
    <hr/>

    <% String mensaje = request.getParameter("mensaje"); %>
    <% if ("registrado".equals(mensaje)) { %>
        <p style="color:green;">Sucursal registrada correctamente.</p>
    <% } else if ("actualizado".equals(mensaje)) { %>
        <p style="color:green;">Sucursal actualizada correctamente.</p>
    <% } else if ("eliminado".equals(mensaje)) { %>
        <p style="color:green;">Sucursal eliminada correctamente.</p>
    <% } %>

    <div>
        <label>Buscar por nombre:</label>
        <input type="text" id="txtNombre" placeholder="Escribe el nombre..."/>
        <button onclick="buscar()">Buscar</button>
        <button onclick="verTodos()">Ver todos</button>
    </div>

    <br/>
    <a href="jsp/nuevaSucursal.jsp"><button>Nueva Sucursal</button></a>
    <br/><br/>

    <table id="tablaSucursales" border="1" cellpadding="8" cellspacing="0">
        <thead>
            <tr>
                <th>ID</th>
                <th>Nombre</th>
                <th>Dirección</th>
                <th>Teléfono</th>
                <th>Estado</th>
                <th>Acciones</th>
            </tr>
        </thead>
        <tbody id="cuerpoTabla">
            <%
                List<Sucursal> lista = (List<Sucursal>) request.getAttribute("listaSucursales");
                if (lista != null && !lista.isEmpty()) {
                    for (Sucursal s : lista) {
            %>
                <tr>
                    <td><%= s.getIdSucursal() %></td>
                    <td><%= s.getNombre() %></td>
                    <td><%= s.getDireccion() %></td>
                    <td><%= s.getTelefono() %></td>
                    <td><%= s.getEstado() %></td>
                    <td>
                        <a href="sucursal?opcion=formularioActualizar&id=<%= s.getIdSucursal() %>">Editar</a>
                        &nbsp;|&nbsp;
                        <a href="sucursal?opcion=eliminar&id=<%= s.getIdSucursal() %>"
                           onclick="return confirm('¿Eliminar sucursal?')">Eliminar</a>
                    </td>
                </tr>
            <%
                    }
                } else {
            %>
                <tr><td colspan="6" style="text-align:center;">No hay sucursales registradas.</td></tr>
            <% } %>
        </tbody>
    </table>

    <script>
        function buscar() {
            var nombre = document.getElementById("txtNombre").value;
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "sucursal?opcion=buscarPorNombre&nombre=" + encodeURIComponent(nombre), true);
            xhr.onload = function() {
                if (xhr.status === 200) {
                    var data = JSON.parse(xhr.responseText);
                    var tbody = document.getElementById("cuerpoTabla");
                    tbody.innerHTML = "";
                    if (data.length === 0) {
                        tbody.innerHTML = "<tr><td colspan='6' style='text-align:center;'>No se encontraron resultados.</td></tr>";
                        return;
                    }
                    data.forEach(function(s) {
                        tbody.innerHTML += "<tr>" +
                            "<td>" + s.idSucursal + "</td>" +
                            "<td>" + s.nombre + "</td>" +
                            "<td>" + s.direccion + "</td>" +
                            "<td>" + s.telefono + "</td>" +
                            "<td>" + s.estado + "</td>" +
                            "<td>" +
                                "<a href='sucursal?opcion=formularioActualizar&id=" + s.idSucursal + "'>Editar</a>" +
                                " | " +
                                "<a href='sucursal?opcion=eliminar&id=" + s.idSucursal + "' onclick=\"return confirm('¿Eliminar sucursal?')\">Eliminar</a>" +
                            "</td>" +
                        "</tr>";
                    });
                }
            };
            xhr.send();
        }

        function verTodos() {
            document.getElementById("txtNombre").value = "";
            window.location.href = "sucursal?opcion=listar";
        }
    </script>
</body>
</html>