package com.inventarioejb.dao;

import java.util.List;
import com.inventarioejb.entity.Sucursal;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

public class SucursalDAOImpl implements SucursalDAO {

    private EntityManagerFactory emf;

    public SucursalDAOImpl() {
        emf = Persistence.createEntityManagerFactory("InventarioPU");
    }

    @Override
    public void registrar(Sucursal sucursal) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(sucursal);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public List<Sucursal> listar() {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Sucursal> query = em.createNamedQuery("Sucursal.listar", Sucursal.class);
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Sucursal> buscarPorNombre(String nombre) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Sucursal> query = em.createNamedQuery("Sucursal.buscarPorNombre", Sucursal.class);
            query.setParameter("nombre", "%" + nombre + "%");
            return query.getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public Sucursal buscarPorId(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            TypedQuery<Sucursal> query = em.createNamedQuery("Sucursal.buscarPorId", Sucursal.class);
            query.setParameter("id", id);
            return query.getSingleResult();
        } finally {
            em.close();
        }
    }
    
    @Override
    public void actualizar(Sucursal sucursal) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Sucursal.actualizar")
              .setParameter("nombre", sucursal.getNombre())
              .setParameter("direccion", sucursal.getDireccion())
              .setParameter("telefono", sucursal.getTelefono())
              .setParameter("estado", sucursal.getEstado())
              .setParameter("id", sucursal.getIdSucursal())
              .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public void eliminar(int id) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.createNamedQuery("Sucursal.eliminar")
              .setParameter("id", id)
              .setParameter("estado", "Inactivo")
              .executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
}