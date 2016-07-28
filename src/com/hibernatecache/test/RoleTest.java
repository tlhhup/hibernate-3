package com.hibernatecache.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernatecache.entity.Role;

public class RoleTest {

	@SuppressWarnings("deprecation")
	@Test
	public void save(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Role role=new Role();
		role.setName("管理员");
		session.save(role);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
