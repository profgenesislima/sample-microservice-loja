package br.edu.ifpe.gus.loja.lojaservice.entity;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Gênesis Lima
 * @email profgenesislima@gmail.com
 *
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pedido implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 3731030186843339638L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_produto"))
	private Produto produto;
	@OneToOne
	@JoinColumn(foreignKey = @ForeignKey(name = "fk_pedido_cliente"))
	private Cliente cliente;
	@Column(name="valor_total")
	private BigDecimal valorTotal;
	private int quantidade;
	
	private StatusPedido statusPedido;

}
