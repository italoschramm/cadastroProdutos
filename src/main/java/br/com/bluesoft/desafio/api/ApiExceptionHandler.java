package br.com.bluesoft.desafio.api;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.view.ErroView;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@ControllerAdvice
public class ApiExceptionHandler extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7418917543347173460L;

	public ApiExceptionHandler() {
		super();
	}
	
	public ApiExceptionHandler(String pMensagem) {
		super(pMensagem);
	}

	@ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErroView handleException(Exception ex) {
        return new ErroView(ex.getMessage());
    }

}
