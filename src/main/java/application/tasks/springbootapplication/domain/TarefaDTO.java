package application.tasks.springbootapplication.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TarefaDTO {
    private Long id;
    private String nome;
    private String descricao;
    private Integer duracaoMin;
}
