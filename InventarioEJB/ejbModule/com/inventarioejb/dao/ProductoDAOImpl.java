package com.inventarioejb.dao;

import com.inventarioejb.entity.Producto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ProductoDAOImpl implements ProductoDAO{

    private EntityManagerFactory emf;

    public ProductoDAOImpl() {
        emf = Persistence.createEntityManagerFactory("InventarioPU");
    }

    @Override
    public void registrar(Producto producto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(producto);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}