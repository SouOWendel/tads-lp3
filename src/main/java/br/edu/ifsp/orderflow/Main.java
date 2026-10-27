package br.edu.ifsp.orderflow;


import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.events.IEventBus;
import br.edu.ifsp.orderflow.events.PagamentoAprovado;
import br.edu.ifsp.orderflow.events.SimpleEventBus;
import br.edu.ifsp.orderflow.events.handlers.*;
import br.edu.ifsp.orderflow.infra.ConsoleNotificacaoService;
import br.edu.ifsp.orderflow.infra.FakePagamentoGateway;
import br.edu.ifsp.orderflow.infra.InMemoryEstoqueService;
import br.edu.ifsp.orderflow.infra.InMemoryPedidoRepository;
import br.edu.ifsp.orderflow.service.*;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Optional;

public class Main {

    public static void main(String[] args) {

        IEstoqueService estoqueService = new InMemoryEstoqueService();
        IPagamentoGateway pagamentoGateway = new FakePagamentoGateway();
        INotificacaoService notificacaoService = new ConsoleNotificacaoService();
        IPedidoRepository pedidoRepository = new InMemoryPedidoRepository();

        IEventBus eventBus = new SimpleEventBus();
        Metricas metricas = new Metricas();

        eventBus.register(new PedidoCriadoLogHandler());
        eventBus.register(new PagamentoAprovadoNotificacaoHandler(pedidoRepository, notificacaoService));
        eventBus.register(new PagamentoRecusadoNotificacaoHandler(pedidoRepository, notificacaoService));
        eventBus.register(new PedidoCanceladoNotificacaoHandler(pedidoRepository, notificacaoService));
        eventBus.register(new PagamentoAprovadoMetricasHandler(metricas));
        eventBus.register(new PagamentoRecusadoMetricasHandler(metricas));
   
		PedidoService pedidoService = new PedidoService(
			estoqueService,
			pedidoRepository,
			pagamentoGateway,
			eventBus
		);

		Produto mouse = new Produto("SKU-1", "Mouse Sem Fio", new BigDecimal("120.00"));
		Produto teclado = new Produto("SKU-2", "Teclado Mecânico", new BigDecimal("180.00"));
		Produto monitor = new Produto("SKU-3", "Monitor 34 Curvo", new BigDecimal("1600.00"));
	
		estoqueService.adicionarEstoque(mouse, 10);
		estoqueService.adicionarEstoque(teclado, 20);
		estoqueService.adicionarEstoque(monitor, 2);

		Cliente ana = new Cliente("Ana", "ana@gmail.com");
		Cliente bruno = new Cliente("Bruno", "bruno@gmail.com");
		
		// Pedido para ser aprovado!
		Pedido pedido1 = new Pedido(ana);
		pedido1.adicionarItem(new ItemPedido(mouse, 2));
		pedido1.adicionarItem(new ItemPedido(teclado, 1));

		// Pedido não aprovado! Pagamento maior que 5 mil.
		Pedido pedido2 = new Pedido(bruno);
		pedido2.adicionarItem(new ItemPedido(monitor, 1));
		pedido2.adicionarItem(new ItemPedido(teclado, 10));

		// Pedido não aprovado! Estoque insuficiente.
		Pedido pedido3 = new Pedido(bruno);
		pedido3.adicionarItem(new ItemPedido(monitor, 2));


		// Processamento
		long inicio = System.currentTimeMillis();
		processar(pedidoService, pedido1);
		processar(pedidoService, pedido2);
		processar(pedidoService, pedido3);
		long fim = System.currentTimeMillis();

		System.out.println();
		System.out.println("Pedidos no repositório: " + pedidoRepository.findAll().size());
	}

	private static void processar(PedidoService service, Pedido pedido) {
		System.out.println();
		System.out.println("Processando" + pedido + "...");
		Pedido resultado = service.processar(pedido);
		System.out.println("Resultado:" + resultado);
	}
}
