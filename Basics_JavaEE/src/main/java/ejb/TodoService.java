package ejb;

import java.util.List;

import javax.ejb.Local;

import entity.Todo;

@Local
public interface TodoService {

	public List<Todo> getAllTodos();

	public void persistTodo(Todo todo);

	public Todo findTodo(long id);

	public Todo updateTodo(Todo todo);

	public Todo getTodoById(long id);
	
	public void removeTodo(long id);
}
