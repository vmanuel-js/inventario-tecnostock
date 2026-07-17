package com.inventarioejb.bl;

import com.inventarioejb.entity.Categoria;
import com.inventarioejb.entity.Producto;
import java.math.BigDecimal;

public interface IProductoBL {
    public void registrarProducto(String nombre, BigDecimal precio, int stock, int idCategoria);
}