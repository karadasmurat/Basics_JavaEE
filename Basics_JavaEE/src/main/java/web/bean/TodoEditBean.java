package web.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ejb.TodoService;
import entity.Todo;

@ManagedBean
@ViewScoped
public class TodoEditBean {

	private static final Logger LOGGER = Logger.getLogger(TodoEditBean.class.getName());

	@EJB
	TodoService todoService;

	private Todo todo;
	private long queryID;

	public TodoEditBean() {
		todo = new Todo();
		LOGGER.log(Level.INFO, "Construct done: TodoEditBean()");
	}

	//@PostConstruct
	public void initValues() {
		todo = todoService.getTodoById(queryID);
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public long getQueryID() {
		return queryID;
	}

	public void setQueryID(long queryID) {
		this.queryID = queryID;
	}

	public String update() {
		
		try{
			todo = todoService.updateTodo(todo);
		}catch (Exception e){
			LOGGER.log(Level.WARNING, "MK: Update() caused an Exception of class "+e.getClass());
			LOGGER.log(Level.WARNING, "MK: message:  "+e.getMessage());
			LOGGER.log(Level.WARNING, "MK: cause:  "+e.getCause().getClass());
			LOGGER.log(Level.WARNING, "MK: cause message: "+e.getCause().getMessage());
			
		    FacesContext.getCurrentInstance().addMessage(null, 
		            new FacesMessage(FacesMessage.SEVERITY_INFO, e.getCause().getMessage(), null));
		    
			return null;
		}
		
		return "todos";		
	}



}
