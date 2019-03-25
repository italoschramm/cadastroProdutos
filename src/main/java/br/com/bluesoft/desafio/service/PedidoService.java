package br.com.bluesoft.desafio.service;

import java.util.List;

import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.vo.ConsultaProdutosVO;
import br.com.bluesoft.desafio.vo.PedidoVO;

public interface PedidoService {

	public void salvarPedido(Pedido pedido);
	public List<PedidoVO> salvarPedidos(List<Pedido> pedidos);
	public List<PedidoVO> listarTodosPedidos();
	public List<PedidoVO> gerarPedidos(ConsultaProdutosVO[] produtos);
}
