package application.tasks.springbootapplication.repository;

import application.tasks.springbootapplication.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}
