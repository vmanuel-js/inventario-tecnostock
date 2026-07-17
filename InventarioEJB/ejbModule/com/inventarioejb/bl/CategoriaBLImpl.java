package com.inventarioejb.bl;

import java.util.List;
import com.inventarioejb.dao.CategoriaDAO;
import com.inventarioejb.entity.Categoria;

public class CategoriaBLImpl implements ICategoriaBL {

    private CategoriaDAO dao = new CategoriaDAO();

    @Override
    public List<Categoria> listarCategorias() {
        return dao.listar();
    }
}