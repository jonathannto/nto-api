package br.eng.jonathan.ntoerp.exception_handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private OffsetDateTime timestamp;
    private String httpCodeMessage;
    private List<String> mensagem;
    private String detalhes;

}