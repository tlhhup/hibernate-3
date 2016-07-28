package com.hibernatecache.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernatecache.entity.Role;
import com.hibernatecache.entity.User;

@SuppressWarnings("deprecation")
public class UserTest {

	@Test
	public void save(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Set<Role> roles=new HashSet<>();
		Role role=new Role();
		role.setId(1);
		roles.add(role);
		
		role=new Role();
		role.setId(2);
		roles.add(role);
		
		User user=new User();
		user.setName("张三");
		user.setRoles(roles);
		
		session.save(user);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void delete(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		User user=new User();
		user.setId(2);
		
		session.delete(user);
		
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
