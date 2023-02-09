package application.tasks.springbootapplication.controller;

import application.tasks.springbootapplication.domain.Tarefa;
import application.tasks.springbootapplication.domain.TarefaDTO;
import application.tasks.springbootapplication.domain.Usuario;
import application.tasks.springbootapplication.requests.TarefaPostRequestBody;
import application.tasks.springbootapplication.requests.UsuarioPostRequestBody;
import application.tasks.springbootapplication.services.TarefaService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@Log4j2
@RequiredArgsConstructor
public class TasksController {
    private final TarefaService tarefaService;

    @PostMapping(path = "/usuario/{usuarioId}")
    public ResponseEntity<List<TarefaDTO>> listTodasTarefasDoUsuario(@PathVariable @NotNull Long usuarioId) {
        return new ResponseEntity<>(tarefaService.listAllById(usuarioId), HttpStatus.OK);
    }

    @PostMapping(path = "/tarefa")
    public ResponseEntity<Tarefa> saveTarefa(@RequestBody TarefaPostRequestBody tarefaPostRequestBody) {
        return new ResponseEntity<>(tarefaService.saveTarefa(tarefaPostRequestBody), HttpStatus.CREATED);
    }

    @PostMapping(path = "/usuario")
    public ResponseEntity<Usuario> saveUser(@RequestBody UsuarioPostRequestBody usuarioPostRequestBody) {
        return new ResponseEntity<>(tarefaService.saveUser(usuarioPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/tarefa/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable @NotNull Long id) {
        tarefaService.deleteTarefa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/usuario/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        tarefaService.deleteTarefa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
