<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Nueva Sucursal - TecnoStock</title>
</head>
<body>
    <h1>Nueva Sucursal</h1>
    <a href="../sucursal?opcion=listar">Volver a Gestión de Sucursales</a>
    <hr/>

    <form action="../sucursal" method="POST">
        <input type="hidden" name="opcion" value="registrar"/>

        <div style="padding: 8px 0px;">
            <label>Nombre:</label><br/>
            <input type="text" name="nombre" maxlength="100" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Dirección:</label><br/>
            <input type="text" name="direccion" maxlength="200" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Teléfono:</label><br/>
            <input type="text" name="telefono" maxlength="15" required/>
        </div>
        <div style="padding: 8px 0px;">
            <label>Estado:</label><br/>
            <select name="estado">
                <option value="Activo">Activo</option>
                <option value="Inactivo">Inactivo</option>
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