package br.edu.ifpe.gus.loja.lojaservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.edu.ifpe.gus.loja.lojaservice.entity.Pedido;


public interface PedidoRepository extends JpaRepository<Pedido, Long>{

}
