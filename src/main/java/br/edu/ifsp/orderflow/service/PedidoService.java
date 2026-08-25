package br.edu.ifsp.orderflow.service;

import br.edu.ifsp.orderflow.domain.Pedido;

public class PedidoService {
	private IEstoqueService estoqueService;

	public PedidoService(IEstoqueService estoqueService) {
		this.estoqueService = estoqueService;
	}

	public Pedido processar(Pedido pedido) {
		if (estoqueService.reservar(pedido)) {
			pedido.marcarComoCriado();
		} else {
			pedido.marcarComoCancelado();
		}
		return pedido;
	}
}
