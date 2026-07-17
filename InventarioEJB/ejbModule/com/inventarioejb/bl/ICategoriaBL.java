package com.inventarioejb.bl;

import java.util.List;
import com.inventarioejb.entity.Categoria;

public interface ICategoriaBL {
    public List<Categoria> listarCategorias();
}