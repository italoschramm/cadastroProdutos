package br.com.bluesoft.desafio.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.vo.ConsultaProdutosVO;
import br.com.bluesoft.desafio.vo.PedidoVO;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;
	
	@GetMapping("/listarProdutos")
    public List<PedidoVO> listarProdutos() {
        return pedidoService.listarTodosPedidos();
    }
	
	@RequestMapping("/gerarPedidos")
	@ResponseBody
	public List<PedidoVO> gerarPedidos(@RequestBody ConsultaProdutosVO[] produtos) throws ApiExceptionHandler {
		List<PedidoVO> pedidos = pedidoService.gerarPedidos(produtos);
		if(!pedidos.isEmpty()) {
			return pedidos;
		}else {
			throw new ApiExceptionHandler("Nenhum fornecedor encontrado!");
		}
	}
}
