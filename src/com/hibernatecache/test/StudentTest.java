package com.hibernatecache.test;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernatecache.entity.Student;

@SuppressWarnings("deprecation")
public class StudentTest {

	@Test
	public void save(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Student student=new Student();
		student.setName("发射点发生");
		
		session.save(student);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
