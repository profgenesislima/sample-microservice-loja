package br.edu.ifpe.gus.loja.lojaservice.entity;

import br.edu.ifpe.gus.loja.lojaservice.exceptions.DomainException;


public enum StatusPedido {

	
	EM_ANALISE {
		@Override
		public StatusPedido proximo(){
			
			return ENVIADO;
		}

		@Override
		public StatusPedido anterior() throws DomainException {
			
		 throw new DomainException("N�o h� etapa anterior.");
		}
	}, SEPARACAO {
		@Override
		public StatusPedido proximo()  {
			
			return ENVIADO;
		}

		@Override
		public StatusPedido anterior() {
			
			return EM_ANALISE;
		}
	}, ENVIADO {
		@Override
		public StatusPedido proximo() {
			
			return SAIU_ENTREGA;
		}

		@Override
		public StatusPedido anterior()  {
			return SEPARACAO;
		}
	}, SAIU_ENTREGA {
		@Override
		public StatusPedido proximo()  {
			
			return ENTREGUE;
		}

		@Override
		public StatusPedido anterior()  {
			
			return RETORNADO;
		}
	}, RETORNADO {
		@Override
		public StatusPedido proximo()  {
			
			return ENTREGUE;
		}

		@Override
		public StatusPedido anterior() {
			
			return EM_ANALISE;
		}
	}, ENTREGUE {
		@Override
		public StatusPedido proximo() {
			
			return FINALIZADO;
		}

		@Override
		public StatusPedido anterior() {
			
			return RETORNADO;
		}
	}, FINALIZADO {
		@Override
		public StatusPedido proximo() throws DomainException {
			
			throw new DomainException("Etapa: Pedido Finalizado. N�o h� etapa posterior.");
		}

		@Override
		public StatusPedido anterior(){
			return ENTREGUE;
		}
	}, CANCELADO	
 {
		@Override
		public StatusPedido proximo() throws DomainException {
			
			throw new DomainException("Pedido Cancelado.");
		}

		@Override
		public StatusPedido anterior() {
			
			return EM_ANALISE;
		}
		
	};
	public abstract StatusPedido proximo() throws DomainException;
	public abstract StatusPedido anterior() throws DomainException;
	
}
