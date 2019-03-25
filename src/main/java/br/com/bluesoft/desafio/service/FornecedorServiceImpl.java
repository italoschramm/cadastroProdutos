package br.com.bluesoft.desafio.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.bluesoft.desafio.model.Fornecedor;
import br.com.bluesoft.desafio.repository.FornecedorRepository;

@Service
public class FornecedorServiceImpl implements FornecedorService{

	@Autowired
	private FornecedorRepository fornecedorRepository;
	
	@Override
	public void salvarFornecedor(String cnpj, String nome) {
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setCnpj(cnpj);
		fornecedor.setNome(nome);
		fornecedorRepository.save(fornecedor);
	}
	
	@Override
	public boolean existeFornecedor(String cnpj) {
		try {
			Fornecedor fornecedor = fornecedorRepository.findOne(cnpj);
			if(fornecedor != null) {
				return true;
			}else {
				return false;
			}
		}catch (Exception e) {
			return false;
		}
	}

	@Override
	public Fornecedor buscarFornecedor(String cnpj) {
		
		return fornecedorRepository.findOne(cnpj);
	}
}
