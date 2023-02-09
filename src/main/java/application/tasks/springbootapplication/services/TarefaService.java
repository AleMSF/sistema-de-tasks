package application.tasks.springbootapplication.services;

import application.tasks.springbootapplication.domain.Tarefa;
import application.tasks.springbootapplication.domain.TarefaDTO;
import application.tasks.springbootapplication.domain.Usuario;
import application.tasks.springbootapplication.repository.TarefaRepository;
import application.tasks.springbootapplication.repository.UsuarioRepository;
import application.tasks.springbootapplication.requests.TarefaPostRequestBody;
import application.tasks.springbootapplication.requests.UsuarioPostRequestBody;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefaService {
    private final TarefaRepository tarefaRepository;
    private final UsuarioRepository usuarioRepository;

    public List<TarefaDTO> listAllById(Long usuarioId) {
        List<Tarefa> tarefas = tarefaRepository.findAll();
        List<TarefaDTO> tarefaDTOS = new ArrayList<>();

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.BAD_REQUEST), "Usuario not found"));

        for (Tarefa tarefa : tarefas) {
            if (tarefa.getUsuarioId().equals(usuarioId)){
                TarefaDTO tarefaDTO = TarefaDTO.builder()
                        .id(tarefa.getId())
                        .nome(usuario.getNome())
                        .descricao(tarefa.getDescricao())
                        .duracaoMin(tarefa.getDuracaoMin()).build();
                tarefaDTOS.add(tarefaDTO);
            }
        }
        return tarefaDTOS;
    }

    public Tarefa saveTarefa(TarefaPostRequestBody tarefaPostRequestBody) {
        return tarefaRepository.save(Tarefa.builder()
                .id(tarefaPostRequestBody.getId())
                .usuarioId(tarefaPostRequestBody.getUsuarioId())
                .data(LocalDate.parse(tarefaPostRequestBody.getData()))
                .duracaoMin(tarefaPostRequestBody.getDuracaoMin())
                .descricao(tarefaPostRequestBody.getDescricao())
                .build());
    }

    public Usuario saveUser(UsuarioPostRequestBody usuarioPostRequestBody) {
        return usuarioRepository.save(Usuario.builder()
                .usuarioId(usuarioPostRequestBody.getId())
                .nome(usuarioPostRequestBody.getNome())
                .build());
    }

    public Tarefa findByIdTarefa(Long id) {
        return tarefaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.BAD_REQUEST), "task not found"));
    }

    public Usuario findByIdUsuario(Long usuarioId) {
        return usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException((HttpStatus.BAD_REQUEST), "task not found"));
    }

    public void deleteTarefa(Long id) {
        tarefaRepository.delete(findByIdTarefa(id));
    }

    public void deleteUsuario(Long id) {
        usuarioRepository.delete(findByIdUsuario(id));
    }
}
