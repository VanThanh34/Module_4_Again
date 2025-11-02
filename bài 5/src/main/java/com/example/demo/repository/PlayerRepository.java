package com.example.demo.repository;

import com.example.demo.entity.Player;
import com.example.demo.util.JPAUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;

public class PlayerRepository {

    public List<Player> findAll() {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        List<Player> players = em.createQuery("from Player", Player.class).getResultList();
        em.close();
        return players;
    }

    public void save(Player player) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.persist(player);
        tx.commit();
        em.close();
    }

    public Player findById(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        Player player = em.find(Player.class, id);
        em.close();
        return player;
    }

    public void update(Player player) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        em.merge(player);
        tx.commit();
        em.close();
    }

    public void delete(int id) {
        EntityManager em = JPAUtil.getEntityManagerFactory().createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        Player player = em.find(Player.class, id);
        if (player != null) {
            em.remove(player);
        }
        tx.commit();
        em.close();
    }
}
