package application.tasks.springbootapplication.services;

import application.tasks.springbootapplication.domain.Tarefa;
import application.tasks.springbootapplication.domain.Usuario;
import application.tasks.springbootapplication.repository.TarefaRepository;
import application.tasks.springbootapplication.repository.UsuarioRepository;
import application.tasks.springbootapplication.requests.TarefaPostRequestBody;
import application.tasks.springbootapplication.requests.UsuarioPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    public List<Tarefa> listAllNonPageable() {
        return tarefaRepository.findAll();
    }

    public Tarefa saveTarefa(@RequestBody TarefaPostRequestBody tarefaPostRequestBody) {
        return tarefaRepository.save(Tarefa.builder()
                .id(tarefaPostRequestBody.getId())
                .nome(tarefaPostRequestBody.getNome())
                .data(LocalDate.parse(tarefaPostRequestBody.getData()))
                .duracaoMin(tarefaPostRequestBody.getDuracaoMin())
                .descricao(tarefaPostRequestBody.getDescricao())
                .build());
    }

    private final UsuarioRepository userRepository;

    public Usuario saveUser(@RequestBody UsuarioPostRequestBody usuarioPostRequestBody) {
        return   userRepository.save(Usuario.builder()
                .usuarioId(usuarioPostRequestBody.getId())
                .nome(usuarioPostRequestBody.getNome())
                .build());
    }

    public Tarefa findByIdOrThrowBadRequestException(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.BAD_REQUEST), "boleto not found"));
    }

    public void delete(Long id) {
        tarefaRepository.delete(findByIdOrThrowBadRequestException(id));
    }
}
