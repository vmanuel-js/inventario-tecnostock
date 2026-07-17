package com.inventarioejb.dao;

import java.util.List;
import com.inventarioejb.entity.Sucursal;

public interface SucursalDAO {
    public void registrar(Sucursal sucursal);
    public List<Sucursal> listar();
    public List<Sucursal> buscarPorNombre(String nombre);
    public Sucursal buscarPorId(int id);
    public void actualizar(Sucursal sucursal);
    public void eliminar(int id);
}