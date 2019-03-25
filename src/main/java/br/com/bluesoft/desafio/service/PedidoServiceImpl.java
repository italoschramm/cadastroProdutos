package br.com.bluesoft.desafio.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.dto.FornecedorDTO;
import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.model.Pedido;
import br.com.bluesoft.desafio.model.PedidoItens;
import br.com.bluesoft.desafio.model.Produto;
import br.com.bluesoft.desafio.repository.PedidoRepository;
import br.com.bluesoft.desafio.vo.ConsultaProdutosVO;
import br.com.bluesoft.desafio.vo.FornecedorPrecosVO;
import br.com.bluesoft.desafio.vo.ItemFornecedorVO;
import br.com.bluesoft.desafio.vo.ItemProdutoVO;
import br.com.bluesoft.desafio.vo.ItemVO;
import br.com.bluesoft.desafio.vo.PedidoVO;
import br.com.bluesoft.desafio.vo.PrecoVO;

@Service
public class PedidoServiceImpl implements PedidoService{

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Override
	public void salvarPedido(Pedido pedido) {
		pedidoRepository.save(pedido);
	}
	
	@Override
	public List<PedidoVO> gerarPedidos(ConsultaProdutosVO[] produtos) {
		List<Pedido> pedidos = new ArrayList<Pedido>();
		for(ConsultaProdutosVO produto : produtos) {
			
			if(produto.getQuantidade() != 0) {
			
			FornecedorPrecosVO[] fornecedorVO = produtoService.consultarProduto(produto.getGtin());			
			
			FornecedorDTO fornecedorDTO = menorPreco(fornecedorVO, produto.getQuantidade());
	
			if(fornecedorDTO != null){
				Produto produtoPedido = produtoService.buscarProduto(produto.getGtin());
				
				if (!fornecedorService.existeFornecedor(fornecedorDTO.getCnpj())) {
					fornecedorService.salvarFornecedor(fornecedorDTO.getCnpj(), fornecedorDTO.getNome());
				}
				Fornecedor fornecedor = fornecedorService.buscarFornecedor(fornecedorDTO.getCnpj());
				
				if(existeFornecedorPedido(pedidos, fornecedorDTO)) {
					PedidoItens pedidoItens = new PedidoItens();
					int index = 0;
					
					for(Pedido pedido : pedidos) {
						if(pedido.getFornecedor().getCnpj().equals(fornecedorDTO.getCnpj())) {
							index = pedidos.indexOf(pedido);
							
							pedidoItens.setProduto(produtoPedido);
							pedidoItens.setPreco(fornecedorDTO.getPreco());
							pedidoItens.setQuantidade(produto.getQuantidade());
							pedidoItens.setTotal(fornecedorDTO.getPreco().multiply(new BigDecimal(produto.getQuantidade())));
							pedidoItens.setPedido(pedido);
								
							List<PedidoItens> listaPedidoItens = pedido.getItens();
							listaPedidoItens.add(pedidoItens);
							pedidos.get(index).setItens(listaPedidoItens);	
							
						}
					}
				}else {
					Pedido pedido = new Pedido();
						
					PedidoItens pedidoItens = new PedidoItens();
					pedidoItens.setProduto(produtoPedido);
					pedidoItens.setPreco(fornecedorDTO.getPreco());
					pedidoItens.setQuantidade(produto.getQuantidade());
					pedidoItens.setTotal(fornecedorDTO.getPreco().multiply(new BigDecimal(produto.getQuantidade())));
					pedidoItens.setPedido(pedido);
						
					List<PedidoItens> listaPedidoItens = new ArrayList<PedidoItens>();
					listaPedidoItens.add(pedidoItens);
					pedido.setItens(listaPedidoItens);
					pedido.setFornecedor(fornecedor);
					pedidos.add(pedido);
				}
			}
		}
		}
		return salvarPedidos(pedidos);
	}
	
	@Override
	public List<PedidoVO> listarTodosPedidos(){
		List<PedidoVO> listaPedidosVO = new ArrayList<PedidoVO>();
		List<Pedido> listaPedidos = (List<Pedido>) pedidoRepository.findAll();
		for(Pedido pedido : listaPedidos) {
			PedidoVO pedidoVO = new PedidoVO();
			pedidoVO = preencherPedidoVO(pedido);
			listaPedidosVO.add(pedidoVO);
		}
		return listaPedidosVO;
	}
	
	@Override
	public List<PedidoVO> salvarPedidos(List<Pedido> pedidos) {
		List<PedidoVO> listaPedidosVO = new ArrayList<PedidoVO>();
		for(Pedido pedido : pedidos) {
			salvarPedido(pedido);
			
			PedidoVO pedidoVO = preencherPedidoVO(pedido);
			listaPedidosVO.add(pedidoVO);
		}
		return listaPedidosVO;
	}
	
	private PedidoVO preencherPedidoVO(Pedido pedido){
		
		PedidoVO pedidoVO = new PedidoVO();
		ItemFornecedorVO itemFornecedor = new ItemFornecedorVO();
		itemFornecedor.setNome(pedido.getFornecedor().getNome());
		
		pedidoVO.setFornecedor(itemFornecedor);
		pedidoVO.setId(pedido.getIdPedido());
		List<ItemVO> listaItemVO = new ArrayList<ItemVO>();
		
		for(PedidoItens pedidoItens : pedido.getItens()) {
			
			ItemVO itemVO = new ItemVO();
			itemVO.setPreco(pedidoItens.getPreco());
			itemVO.setQuantidade(pedidoItens.getQuantidade());
			itemVO.setTotal(pedidoItens.getTotal());
			
			ItemProdutoVO itemProdutoVO = new ItemProdutoVO();
			itemProdutoVO.setNome(pedidoItens.getProduto().getNome());
			
			itemVO.setProduto(itemProdutoVO);
			
			listaItemVO.add(itemVO);
			pedidoVO.setItens(listaItemVO);

		}
		
		return pedidoVO;
	}
	
	private FornecedorDTO menorPreco(FornecedorPrecosVO[] listaFornecedores, Integer quantidade) {		
		BigDecimal menorPreco = new BigDecimal(0);
		FornecedorDTO fornecedorDTO = new FornecedorDTO();
		for(FornecedorPrecosVO fornecedorPreco : listaFornecedores){
			for(PrecoVO precoVO : fornecedorPreco.getPrecos()) {
				if(precoVO.getQuantidade_minima() <= quantidade) {
					if(menorPreco.compareTo(BigDecimal.ZERO) == 0) {
						menorPreco = precoVO.getPreco();
						fornecedorDTO.setCnpj(fornecedorPreco.getCnpj());
						fornecedorDTO.setNome(fornecedorPreco.getNome());
						fornecedorDTO.setPreco(menorPreco);
						fornecedorDTO.setQuantidade(quantidade);
					}else if (menorPreco.compareTo(precoVO.getPreco()) > 0) {
						menorPreco = precoVO.getPreco();
						fornecedorDTO.setCnpj(fornecedorPreco.getCnpj());
						fornecedorDTO.setNome(fornecedorPreco.getNome());
						fornecedorDTO.setPreco(menorPreco);
						fornecedorDTO.setQuantidade(quantidade);
					}
				}
			}
		}
		return fornecedorDTO;
	}
	
	private Boolean existeFornecedorPedido(List<Pedido> pedidos, FornecedorDTO fornecedor) {
		if (pedidos.isEmpty()) {
			return false;
		}else {
			for(Pedido pedido : pedidos) {
				if(pedido.getFornecedor().getCnpj().equals(fornecedor.getCnpj())) {
					return true;
				}
			}
			
		}	return false;
	}

}
