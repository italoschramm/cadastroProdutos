package br.com.bluesoft.desafio.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItens;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.service.FornecedorService;
import br.com.bluesoft.desafio.service.PedidoService;
import br.com.bluesoft.desafio.vo.ConsultaProdutosVO;
import br.com.bluesoft.desafio.vo.PedidoVO;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestPedidos {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Test
	public void gerarPedidos() {
		ConsultaProdutosVO[] produto = new ConsultaProdutosVO[1];
		
		produto[0] = new ConsultaProdutosVO();
		
		produto[0].setGtin("7894900011517");
		produto[0].setQuantidade(10);
		
		List<PedidoVO> lista = pedidoService.gerarPedidos(produto);
		
		Assert.assertNotNull(lista);
	}

	@Test
	public void listarTodosPedidos(){
		List<PedidoVO> lista = pedidoService.listarTodosPedidos();
		Assert.assertNotNull(lista);
	}
	
	@Test
	public void salvarPedidos() {
		
		fornecedorService.salvarFornecedor("30.690.920/0001-40", "Fornecedor1");
		Fornecedor fornecedor = fornecedorService.buscarFornecedor("30.690.920/0001-40");
		
		Produto produto = new Produto();
		produto.setGtin("7894900011517");
		produto.setNome("REFRIGERANTE COCA-COLA 2LT");
		
		Pedido pedido = new Pedido();
		pedido.setFornecedor(fornecedor);
		
		BigDecimal valor = new BigDecimal(10);
		
		PedidoItens pedidoItens = new PedidoItens();
		pedidoItens.setPreco(valor);
		pedidoItens.setProduto(produto);
		pedidoItens.setQuantidade(1);
		pedidoItens.setTotal(valor);
		pedidoItens.setPedido(pedido);
		
		List<PedidoItens> listaPedidoItens = new ArrayList<PedidoItens>();
		listaPedidoItens.add(pedidoItens);
		
		pedido.setItens(listaPedidoItens);
		
		List<Pedido> pedidos = new ArrayList<Pedido>();
		pedidos.add(pedido);
		
		List<PedidoVO> lista = pedidoService.salvarPedidos(pedidos);

		Assert.assertNotNull(lista);
	}
}
