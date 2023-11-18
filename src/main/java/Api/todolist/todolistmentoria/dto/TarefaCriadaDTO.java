package Api.todolist.todolistmentoria.dto;

import Api.todolist.todolistmentoria.enums.Status;

import java.time.LocalDateTime;

public record TarefaCriadaDTO(String nomeDoCliente,
                              String nomeDaTarefa,
                              String descricaoDaTarefa,
                              LocalDateTime expectativaDeConclusao,
                              Status status,
                              String responsavelPelaTarefa,
                              String solicitante) {

    public TarefaCriadaDTO(TodoDto createdTodo) {
        this(createdTodo.getNomeDoCliente(),
                createdTodo.getNomeDaTarefa(),
                createdTodo.getDescricaoDaTarefa(),
                createdTodo.getExpectativaDeConclusao(),
                createdTodo.getStatus(),
                createdTodo.getResponsavelPelaTarefa(),
                createdTodo.getSolicitante());
    }

}


