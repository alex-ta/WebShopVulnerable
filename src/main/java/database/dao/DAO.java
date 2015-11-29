package database.dao;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import classmapper.Mapper;
import database.extension.DatabaseException;

@Repository
public abstract class DAO <T>{
	
	// implement hibernate pooling
	// add it to the container
	// handle exceptions & validation
		
	// look after character length for fields(annotation)
	// check getId extra class for user ?
	// create one DAO class and save all Entites with it.
	// check multithreading spring annotation
	// check foreignkey with hibernate
	// check if everything works
	// check sessionmanagement
	
	private Class<T> current;
	private Mapper mapper;
	private HashMap<String,Map<String,String>> selects;
	private String hqlDelete;
	
	public DAO(Class<T> current,String keyColumn, String table){
		this.current = current;
		this.mapper = new Mapper(current);
		this.selects = new HashMap<String,Map<String,String>>();
		hqlDelete = "delete from "+table+" where "+keyColumn+"= :column ";
	}
	
	public DAO(Class<T> current,String keyColumn){
		this(current,keyColumn,current.getName());
	}
	
	public DAO(Class<T> current){
		this(current,"id",current.getName());
	}

	public Mapper getMapper(){
		return new Mapper(mapper);
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void save(T obj) throws DatabaseException{
		if(obj != null){
			Session session = this.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.save(obj);
			transaction.commit();
		}
	}
	
	public void delete(T obj) throws DatabaseException{
		if(obj != null){	
			Session session = this.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.delete(obj);
			transaction.commit();
		}
	}
	
	protected void deleteString(String key) throws DatabaseException{
		if(key != null){	
			Session session = this.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery(hqlDelete);	
			query.setString("column", key);
			query.executeUpdate();
			transaction.commit();
		}
	}
	
	protected void deleteInteger(Integer key) throws DatabaseException{
		if(key != null){	
			Session session = this.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery(hqlDelete);	
			query.setInteger("column", key);
			query.executeUpdate();
			transaction.commit();
		}
	}
	
	public abstract void delete(Serializable key) throws DatabaseException;
	
	public void update(T obj) throws DatabaseException{
		if(obj != null){
			Session session = this.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			session.update(obj);
			transaction.commit();
		}
	}
	@SuppressWarnings("unchecked")
	public List<T> selectAll() throws DatabaseException{
		List<T> results = null;
		Session session = this.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();	
		results = session.createCriteria(current).list();
		transaction.commit();
		return results;
	}
	
	public T select(Serializable key) throws DatabaseException{
		if(key != null){
			Session session = this.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			@SuppressWarnings("unchecked")
			T result = ((T) session.get(current, key));
			transaction.commit();
			return result;
		}
		return null;
	}
	
	protected void addSelect(String key, Map<String,String> map){
		selects.put(key, map);
	}
	
	public Map<String,Map<String,String>> selectSelects(){
		updateSelect();
		return selects;
	}
	
	protected void updateSelect(){}
}
