package ejb;

import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import entity.Todo;

@Stateless
public class TodoServiceImpl implements TodoServiceRemote, TodoService {

	private static final Logger LOGGER = Logger.getLogger(TodoServiceImpl.class
			.getName());

	// The entity manager is similar to the Hibernate Session class
	// Applications use it to create/read/update/delete data

	// Injecting an EntityManagerInstance
	@PersistenceContext(unitName = "Basics_JavaEE_PU")
	EntityManager em;

	// Configuration configuration;
	// private SessionFactory sessionFactory;

	public TodoServiceImpl() {

		LOGGER.log(Level.FINE, "MK: Constructing TodoServiceImpl ");

		// configuration = (new Configuration()).configure();
		// StandardServiceRegistryBuilder builder = (new
		// StandardServiceRegistryBuilder())
		// .applySettings(configuration.getProperties());
		// sessionFactory = configuration.buildSessionFactory(builder.build());

	}

	@Override
	public List<Todo> getAllTodos() {

		// Session session = sessionFactory.openSession();
		// session.beginTransaction();
		// List todoList = session.createQuery("from Todo").list();

		// Query query = em.createQuery("SELECT t FROM Todo t");
		Query query = em.createNamedQuery("Todo.findAll");

		// query.setLockMode(LockModeType.OPTIMISTIC);
		List<Todo> todoList = query.getResultList();

		for (Todo todo : todoList) {
			LOGGER.log(Level.FINE, "MK: Todo - " + todo.getTitle());
		}

		// session.getTransaction().commit();
		// session.close();
		return todoList;
	}

	public String findTodoByTitle(String arg) {
		
		Todo result= (Todo) em.createNamedQuery("Todo.findByTitle")
				.setParameter("title", arg).getSingleResult();
		return result.getTitle();
	}

	@Override
	public void persistTodo(Todo todo) {

		/**
		 * Session session = sessionFactory.openSession();
		 * session.beginTransaction(); session.save(todo);
		 * session.getTransaction().commit(); session.close();
		 **/

		LOGGER.log(Level.INFO, "Using The entity manager ");
		// em.lock(todo, LockModeType.OPTIMISTIC);
		em.persist(todo);
	}

	@Override
	public Todo findTodo(long tid) {
		return getTodoById(tid);
	}

	@Override
	public Todo updateTodo(Todo todo) {

		Todo result = em.merge(todo);
		LOGGER.log(Level.WARNING, "MK: Done merging todo.");

		flushChanges();
		LOGGER.log(Level.WARNING, "MK: Done flushChanges.");

		return result;

		/*
		 * Session session; Transaction tx; session =
		 * sessionFactory.openSession(); tx = null; try { tx =
		 * session.beginTransaction(); Todo currentTodo = (Todo)
		 * session.get(Todo.class, Long.valueOf(todo.getId()));
		 * currentTodo.setTitle(todo.getTitle());
		 * currentTodo.setDescription(todo.getDescription());
		 * session.update(currentTodo); tx.commit();
		 * 
		 * } catch (RuntimeException e) { try { tx.rollback(); } catch
		 * (RuntimeException rbe) { LOGGER.log(Level.SEVERE,
		 * "Couldn’t roll back transaction", rbe); } throw e; } finally { if
		 * (session != null) { session.close(); } }
		 */
	}

	@Override
	public Todo getTodoById(long tid) {

		// Session session = sessionFactory.openSession();
		// session.beginTransaction();
		// Todo t = (Todo) session.get(Todo.class, Long.valueOf(tid));
		// session.getTransaction().commit();
		// session.close();

		return em.find(Todo.class, tid);
	}

	public void removeTodo(long tid) {

		Todo todo = em.find(Todo.class, tid);
		if (todo != null) {
			em.remove(todo);
		}

	}

	protected void flushChanges() {

		try {
			em.flush();
		} catch (OptimisticLockException e) {
			LOGGER.log(Level.WARNING,
					"MK: The operation encountered an OptimisticLockException!");
			throw e;

		}
	}

}
