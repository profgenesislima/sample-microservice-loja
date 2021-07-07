package br.edu.ifpe.gus.loja.lojaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.loja.lojaservice.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long>{

}
