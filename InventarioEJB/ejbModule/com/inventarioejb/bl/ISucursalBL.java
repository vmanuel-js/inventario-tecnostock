package com.inventarioejb.bl;

import java.sql.SQLException;
import java.util.*;
import com.inventarioejb.entity.Sucursal;

public interface ISucursalBL {
	public void registrar(String nombre, String direccion, String telefono, String estado) throws SQLException;
	public List<Sucursal> listar() throws SQLException;
    public List<Sucursal> buscarPorNombre(String nombre) throws SQLException;
    public Sucursal buscarPorId(int id) throws SQLException;
    public void actualizar(int id, String nombre, String direccion, String telefono, String estado) throws SQLException;
    public void eliminar(int id) throws SQLException;
}
