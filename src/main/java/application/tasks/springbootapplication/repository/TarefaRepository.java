package application.tasks.springbootapplication.repository;

import application.tasks.springbootapplication.domain.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
