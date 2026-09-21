package br.edu.ifsp.orderflow;

import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.infra.InMemoryEstoqueService;
import br.edu.ifsp.orderflow.infra.ConsoleNotificacaoService;
import br.edu.ifsp.orderflow.infra.FakePagamentoGateway;
import br.edu.ifsp.orderflow.infra.InMemoryPedidoRepository;
import br.edu.ifsp.orderflow.service.*;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

		IEstoqueService estoqueService = new InMemoryEstoqueService();
		IPedidoRepository pedidoRepository = new InMemoryPedidoRepository();
		INotificacaoService notificacaoService = new ConsoleNotificacaoService();
		IPagamentoGateway pagamentoGateway = new FakePagamentoGateway();

		PedidoService pedidoService = new PedidoService(
				estoqueService,
				pedidoRepository,
				pagamentoGateway,
				notificacaoService
		);

		Cliente bruno = new Cliente("Bruno", "bruno@email.com");
		Pedido pedido1 = new Pedido(ana);
		pedido1.adicionarItem(new ItemPedido(mouse, 2));
		pedido1.adicionarItem(new ItemPedido(teclado, 2));
		Pedido pedido = pedidoService.processar(pedido1);
		System.out.println(pedido);
    }
}
