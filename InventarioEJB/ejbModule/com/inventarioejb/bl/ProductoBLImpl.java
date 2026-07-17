package com.inventarioejb.bl;

import java.math.BigDecimal;
import com.inventarioejb.dao.CategoriaDAO;
import com.inventarioejb.dao.ProductoDAO;
import com.inventarioejb.entity.Categoria;
import com.inventarioejb.entity.Producto;

public class ProductoBLImpl implements IProductoBL {

    private ProductoDAO productoDAO = new ProductoDAO();
    private CategoriaDAO categoriaDAO = new CategoriaDAO();

    @Override
    public void registrarProducto(String nombre, BigDecimal precio, int stock, int idCategoria) {
        Categoria categoria = categoriaDAO.buscarPorId(idCategoria);
        Producto producto = new Producto();
        producto.setNombre(nombre);
        producto.setPrecio(precio);
        producto.setStock(stock);
        producto.setCategoria(categoria);
        productoDAO.registrar(producto);
    }
}