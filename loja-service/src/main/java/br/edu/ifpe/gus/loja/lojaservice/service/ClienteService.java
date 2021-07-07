package br.edu.ifpe.gus.loja.lojaservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.edu.ifpe.gus.loja.lojaservice.entity.Cliente;
import br.edu.ifpe.gus.loja.lojaservice.repository.ClienteRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	public void registrarCliente(Cliente cliente) {
		clienteRepository.save(cliente);
	}
	
	public List<Cliente> listarClientes(){
		return clienteRepository.findAll();
	}

	public Cliente pegaPorId(Long clienteId) {
	
		return clienteRepository.findById(clienteId).get();
	}
}
