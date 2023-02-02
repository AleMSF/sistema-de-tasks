package application.tasks.springbootapplication.controller;

import application.tasks.springbootapplication.domain.Tarefa;
import application.tasks.springbootapplication.domain.Usuario;
import application.tasks.springbootapplication.requests.TarefaPostRequestBody;
import application.tasks.springbootapplication.requests.UsuarioPostRequestBody;
import application.tasks.springbootapplication.services.TarefaService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tarefas")
@Log4j2
@RequiredArgsConstructor
public class TasksController {
    private final TarefaService tarefaService;

    @GetMapping
    public ResponseEntity<List<Tarefa>> listAll() {
        return ResponseEntity.ok(tarefaService.listAllNonPageable());
    }

    @PostMapping(path = "/tarefa")
    public ResponseEntity<Tarefa> saveTarefa(@RequestBody TarefaPostRequestBody tarefaPostRequestBody) {
        return new ResponseEntity<>(tarefaService.saveTarefa(tarefaPostRequestBody), HttpStatus.CREATED);
    }

    @PostMapping(path = "/user")
    public ResponseEntity<Usuario> saveUser(@RequestBody UsuarioPostRequestBody usuarioPostRequestBody) {
        return new ResponseEntity<>(tarefaService.saveUser(usuarioPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id) {
        tarefaService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        tarefaService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
