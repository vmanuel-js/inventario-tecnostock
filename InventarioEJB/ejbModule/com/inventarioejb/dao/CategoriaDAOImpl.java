package com.inventarioejb.dao;

import java.util.List;
import com.inventarioejb.entity.Categoria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class CategoriaDAOImpl implements CategoriaDAO {

    private EntityManagerFactory emf;

    public CategoriaDAOImpl() {
        emf = Persistence.createEntityManagerFactory("InventarioPU");
    }

    @Override
    public List<Categoria> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Categoria> query = em.createNamedQuery("Categoria.listar", Categoria.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }
    
    @Override
    public Categoria buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Categoria.class, id);
        } finally {
            em.close();
        }
    }
}