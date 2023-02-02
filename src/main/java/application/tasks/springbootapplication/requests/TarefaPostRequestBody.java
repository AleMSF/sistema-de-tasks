package application.tasks.springbootapplication.requests;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TarefaPostRequestBody {
    @NotEmpty
    private Long id;
    private String nome;
    private String descricao;
    private Integer duracaoMin;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private String data = String.valueOf(LocalDate.now());
}
