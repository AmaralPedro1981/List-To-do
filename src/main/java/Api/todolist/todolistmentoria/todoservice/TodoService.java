package Api.todolist.todolistmentoria.todoservice;

import Api.todolist.todolistmentoria.dto.TarefaCriadaDTO;
import Api.todolist.todolistmentoria.dto.TodoDto;
import Api.todolist.todolistmentoria.enums.Status;
import Api.todolist.todolistmentoria.todorepository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Component
@AllArgsConstructor
public class TodoService {

    private TodoRepository todoRepository;

    public TodoDto createTodo(TarefaCriadaDTO todo){
        return todoRepository.save(new TodoDto(todo));
    }

    public List<TodoDto> listAllTodo(){
        return todoRepository.findAll();
    }

    public ResponseEntity<TodoDto> findTodoById(Long id){
        return  todoRepository.findById(id)
                .map(todo -> ResponseEntity.ok().body(todo))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<TodoDto> updateTodoById(TodoDto todo, Long id) {
        return todoRepository.findById(id)
                .map(todoToUpdate -> {


                    if (todo.getStatus() == Status.CONCLUIDO && todoToUpdate.getStatus() != Status.CONCLUIDO
                            && todo.getConcluidoEm() != null) {
                        todoToUpdate.setConcluidoEm(todo.getConcluidoEm());

                    } else if (todo.getStatus() == Status.CONCLUIDO && todoToUpdate.getStatus() != Status.CONCLUIDO) {
                        todoToUpdate.setConcluidoEm(LocalDateTime.now());
                    } else {
                        todoToUpdate.setConcluidoEm(null);
                    }


                    todoToUpdate.setNomeDoCliente(todo.getNomeDoCliente());
                    todoToUpdate.setDescricaoDaTarefa(todo.getDescricaoDaTarefa());
                    todoToUpdate.setAtualizadoEm(todo.getAtualizadoEm());
                    todoToUpdate.setStatus(todo.getStatus());
                    todoToUpdate.setResponsavelPelaTarefa(todo.getResponsavelPelaTarefa());
                    todoToUpdate.setSolicitante(todo.getSolicitante());
                    todoToUpdate.setNomeDaTarefa(todo.getNomeDaTarefa());
                    TodoDto updated = todoRepository.save(todoToUpdate);


                    return ResponseEntity.ok().body(updated);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById (Long id){
        return todoRepository.findById(id)
                .map(todoToDelete ->{
                    todoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());

    }

}
