package com.inventarioejb.bl;

import java.sql.SQLException;
import java.util.List;
import com.inventarioejb.dao.SucursalDAO;
import com.inventarioejb.entity.Sucursal;

public class SucursalBLImpl implements ISucursalBL {

    private SucursalDAO dao = new SucursalDAO();

    @Override
    public void registrar(String nombre, String direccion, String telefono, String estado) throws SQLException {
        Sucursal sucursal = new Sucursal();
        sucursal.setNombre(nombre);
        sucursal.setDireccion(direccion);
        sucursal.setTelefono(telefono);
        sucursal.setEstado(estado);
        dao.registrar(sucursal);
    }

    @Override
    public List<Sucursal> listar() throws SQLException {
        return dao.listar();
    }

    @Override
    public List<Sucursal> buscarPorNombre(String nombre) throws SQLException {
        return dao.buscarPorNombre(nombre);
    }

    @Override
    public Sucursal buscarPorId(int id) throws SQLException {
        return dao.buscarPorId(id);
    }

    @Override
    public void actualizar(int id, String nombre, String direccion, String telefono, String estado) throws SQLException {
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(id);
        sucursal.setNombre(nombre);
        sucursal.setDireccion(direccion);
        sucursal.setTelefono(telefono);
        sucursal.setEstado(estado);
        dao.actualizar(sucursal);
    }

    @Override
    public void eliminar(int id) throws SQLException {
        dao.eliminar(id);
    }
}