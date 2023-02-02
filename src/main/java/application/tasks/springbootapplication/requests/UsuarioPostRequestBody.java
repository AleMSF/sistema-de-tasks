package application.tasks.springbootapplication.requests;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioPostRequestBody {
    @NotEmpty
    private Long id;
    private String nome;
}
