package com.klu.JFSD_Hibernate;


import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;

import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.List;

public class ClientDemo {
    public static void main(String[] args) {
        Configuration cfg = new Configuration().configure();
        SessionFactory sf = cfg.buildSessionFactory();
        Session s = sf.openSession();

        Transaction t = s.beginTransaction();
        Project p1 = new Project();
        p1.setProjectName("ERP");
        p1.setDuration(12);
        p1.setBudget(100000.0);
        p1.setTeamLead("Deepak");

        

        s.save(p1);

        t.commit();

        Criteria criteria = s.createCriteria(Project.class);

        criteria.setProjection(Projections.rowCount());
        List countResult = criteria.list();
        System.out.println("Count: " + countResult.get(0));

        criteria.setProjection(Projections.max("budget"));
        List maxResult = criteria.list();
        System.out.println("Max Budget: " + maxResult.get(0));

        criteria.setProjection(Projections.min("budget"));
        List minResult = criteria.list();
        System.out.println("Min Budget: " + minResult.get(0));

        criteria.setProjection(Projections.sum("budget"));
        List sumResult = criteria.list();
        System.out.println("Sum of Budgets: " + sumResult.get(0));

        criteria.setProjection(Projections.avg("budget"));
        List avgResult = criteria.list();
        System.out.println("Average Budget: " + avgResult.get(0));

        s.close();
        sf.close();
    }
}


