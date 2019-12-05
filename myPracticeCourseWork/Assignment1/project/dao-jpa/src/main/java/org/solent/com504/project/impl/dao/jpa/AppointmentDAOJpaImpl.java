/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.solent.com504.project.impl.dao.jpa;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.solent.com504.project.model.dao.AppointmentDAO;
import org.solent.com504.project.model.dto.Appointment;
import org.solent.com504.project.model.dto.Person;

/**
 *
 * @author cgallen
 */
public class AppointmentDAOJpaImpl implements AppointmentDAO {

    final static Logger LOG = LogManager.getLogger(PersonDAOJpaImpl.class);

    private EntityManager entityManager;

    public AppointmentDAOJpaImpl(EntityManager em) {
        this.entityManager = em;
    }

    @Override
    public Appointment findById(Long id) {
        Appointment appointment = entityManager.find(Appointment.class, id);
        return appointment;
    }

    @Override
    public Appointment save(Appointment appointment) {
        entityManager.getTransaction().begin();
        entityManager.persist(appointment);  // NOTE merge(appointment) differnt semantics
        // entityManager.flush() could be used
        entityManager.getTransaction().commit();
        return appointment;
    }

    @Override
    public List<Appointment> findAll() {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a", Appointment.class);
        List<Appointment> appointmentList = q.getResultList();
        return appointmentList;
    }

    @Override
    public Appointment delete(Appointment appointment) {
        entityManager.getTransaction().begin();
        entityManager.remove(appointment);
        entityManager.getTransaction().commit();
        return null;
    }

    @Override
    public void deleteById(Long id) {
        entityManager.getTransaction().begin();
        Query q = entityManager.createQuery("DELETE FROM Appointment a WHERE a.id=:id");
        q.setParameter("id", id);
        q.executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public void deleteAll() {
        entityManager.getTransaction().begin();
        entityManager.createQuery("DELETE FROM Appointment ").executeUpdate();
        entityManager.getTransaction().commit();
    }

    @Override
    public List<Appointment> findByPersonA(Person personA) {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.personA=:personA", Appointment.class);
        q.setParameter("personA", personA);
        List<Appointment> appointmentList = q.getResultList();

        return appointmentList;
    }

    @Override
    public List<Appointment> findByPersonB(Person personB) {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT b FROM Appointment b WHERE b.personB=:personB", Appointment.class);
        q.setParameter("personB", personB);
        List<Appointment> appointmentList = q.getResultList();

        return appointmentList;
    }

    @Override
    public List<Appointment> findByDate(Date startDate, Date endDate) {
        TypedQuery<Appointment> q = entityManager.createQuery("SELECT a FROM Appointment a WHERE a.date BETWEEN :startDate AND :endDate", Appointment.class);
        q.setParameter("startDate", startDate);
        q.setParameter("endDate", endDate);
        List<Appointment> appointmentList = q.getResultList();

        return appointmentList;
    }

}
