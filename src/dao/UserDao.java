package dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.JDBCException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import tool.MD5;

import entity.User;
import factory.HibernateUtil;

public class UserDao {
	HibernateUtil util = new HibernateUtil();
	
	
	//保存用户
	public void saveUser(User user) throws SQLException{
		Session session = util.getSession();
		Transaction tr = null;
		try{
			tr = session.beginTransaction();
			session.save(user);
			tr.commit();
		}catch(JDBCException ex){
			if(ex != null){
				tr.rollback();
				throw ex.getSQLException();
			}
		}finally{
			util.closeSession(session);
		}
	}
	
	
	//修改用户
	public void updateUser(User user) throws SQLException{
		Session session = util.getSession();
		Transaction tr = null;
		try{
			tr = session.beginTransaction();
			session.update(user);
			tr.commit();
		}catch(JDBCException ex){
			if(ex != null){
				tr.rollback();
				throw ex.getSQLException();
			}
		}finally{
			util.closeSession(session);
		}
	}
	
	//用户登录
	public User login(String email,String password){
		password = new MD5(password).compute();
		Session session = util.getSession();
		try {
			Query query = session.createQuery("from User m where m.email = ? and password = ?");
			query.setString(0, email);
			query.setString(1, password);
			List<User> list = query.list();
			if (null == list || 0 == list.size()) {
				return null;
			}
			
			return list.get(0); 
		} catch (Exception e) {
			System.out.println("boolean login 方法发生异常:");
			e.printStackTrace();
			return null;
		} finally {
			util.closeSession(session);
		}	
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u = new User();
		u.setPassword("123123");
		u.setRegtime("20121026");
		u.setEmail("yjy910323@gmail.com");
		System.out.println(new UserDao().login("yjy910323@gmail.com", "123123123"));
		;
	}

}
