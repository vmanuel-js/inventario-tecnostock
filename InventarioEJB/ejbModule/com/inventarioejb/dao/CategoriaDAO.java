package com.inventarioejb.dao;

import java.util.List;
import com.inventarioejb.entity.Categoria;

public interface CategoriaDAO {
    public List<Categoria> listar();
    public Categoria buscarPorId(int id);
}