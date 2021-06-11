package org.nagarro.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.nagarro.model.ImageFile;
import org.nagarro.model.User;

public class ImagesDAOImpl implements ImagesDAO {

	private static SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public ImagesDAOImpl() {
	}

	/* (non-Javadoc)
	 * @see org.nagarro.DAO.ImagesDAO#saveUser(org.nagarro.model.User)
	 */
	@Override
	public void saveUser(User user) {
		try {
			Session session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(user);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.nagarro.DAO.ImagesDAO#getUserByEmail(java.lang.String)
	 */
	@Override
	public User getUserByEmail(String email) {
		User user = null;
		try {
			Session session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery("from User where email = :email");
			query.setParameter("email", email);
			List<User> users = (List<User>) query.list();
			if (users.size() > 0) {
				user = users.get(0);
			}

			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see org.nagarro.DAO.ImagesDAO#saveImageFile(org.nagarro.model.ImageFile)
	 */
	@Override
	public void saveImageFile(ImageFile image) {
		try {
			Session session = sessionFactory.openSession();

			session.beginTransaction();

			session.saveOrUpdate(image);
			session.getTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see org.nagarro.DAO.ImagesDAO#getImages(org.nagarro.model.User)
	 */
	@Override
	public List<ImageFile> getImages(User user) {
		List<ImageFile> images = null;
		try {

			Session session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery("from ImageFile where user = :user");
			query.setParameter("user", user);
			images = query.list();
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return images;
	}

	/* (non-Javadoc)
	 * @see org.nagarro.DAO.ImagesDAO#deleteImg(int)
	 */
	@Override
	public void deleteImg(int imageId) {
		// TODO Auto-generated method stub
		try {

			Session session = sessionFactory.openSession();

			session.beginTransaction();
			ImageFile image = session.get(ImageFile.class, imageId);
			session.delete(image);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* (non-Javadoc)
	 * @see org.nagarro.DAO.ImagesDAO#getImageFileById(int)
	 */
	@Override
	public ImageFile getImageFileById(int currentImageId) {
		ImageFile image = null;
		try {

			Session session = sessionFactory.openSession();

			session.beginTransaction();
			image = session.get(ImageFile.class, currentImageId);
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return image;
	}

	/* (non-Javadoc)
	 * @see org.nagarro.DAO.ImagesDAO#getUsedCap(org.nagarro.model.User)
	 */
	@Override
	public long getUsedCap(User user) {
		long size = 0;
		try {

			Session session = sessionFactory.openSession();

			session.beginTransaction();
			Query query = session.createQuery("select sum(size) from ImageFile where user = :user");
			query.setParameter("user", user);
			size = (long) query.getSingleResult();
			session.getTransaction().commit();
			session.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

}
