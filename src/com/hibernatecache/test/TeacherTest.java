package com.hibernatecache.test;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import com.hibernatecache.entity.Student;
import com.hibernatecache.entity.Teacher;

@SuppressWarnings("deprecation")
public class TeacherTest {

	@Test
	public void save(){
		Configuration configuration=new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		Set<Student> students=new HashSet<>();
		
		Student student=new Student();
		student.setId(1);
		students.add(student);
		
		student=new Student();
		student.setId(2);
		students.add(student);
		
		
		Teacher teacher=new Teacher();
		teacher.setName("张老师");
		teacher.setStudents(students);
		
		session.save(teacher);
		
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

		Teacher teacher=new Teacher();
		teacher.setId(5);
		// TODO 一对多级联删除
		session.delete(teacher);
			
		tx.commit();
		session.close();
		sessionFactory.close();
	}
	
}
