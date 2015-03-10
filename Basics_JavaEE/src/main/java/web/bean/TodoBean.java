package web.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import ejb.TodoService;
import entity.Todo;

@Named
@RequestScoped
public class TodoBean {

	@EJB
	TodoService todoService;

	private Todo todo;
	
	private String titleKey;

	public TodoBean() {
	}

	@PostConstruct
	public void init() {
		todo = new Todo();
	}

	public List getTodos() {
		return todoService.getAllTodos();
	}

	public String findTodoByTitle(){
		return todoService.findTodoByTitle(titleKey);
	}
	
	public String persist() {
		todoService.persistTodo(todo);
		return "todos";
	}

	public void delete() {
	}

	public void update() {
		todoService.updateTodo(todo);
	}

	public Todo getTodo() {
		return todo;
	}

	public void setTodo(Todo todo) {
		this.todo = todo;
	}

	public String getTitleKey() {
		return titleKey;
	}

	public void setTitleKey(String titleKey) {
		this.titleKey = titleKey;
	}

	

}
