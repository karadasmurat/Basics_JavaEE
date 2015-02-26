package ejb;

import java.util.List;

import javax.ejb.Local;

import entity.Todo;

@Local
public interface TodoService {

	public abstract List getAllTodos();

	public abstract void persistTodo(Todo todo);

	public abstract Todo findTodo(long l);

	public abstract Todo updateTodo(Todo todo);

	public abstract Todo getTodoById(long l);
}
