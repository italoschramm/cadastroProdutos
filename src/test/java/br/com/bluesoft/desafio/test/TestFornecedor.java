package br.com.bluesoft.desafio.test;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.bluesoft.desafio.service.FornecedorService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFornecedor {
	
	@Autowired
	private FornecedorService fornecedorService;
	
	@Test
	public void existeFornecedor() {
		fornecedorService.salvarFornecedor("30.690.920/0001-40", "Fornecedor1");
		boolean resultado = fornecedorService.existeFornecedor("30.690.920/0001-40");
		Assert.assertTrue(resultado);
	}

}
