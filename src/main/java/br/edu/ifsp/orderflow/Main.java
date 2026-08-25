package br.edu.ifsp.orderflow;

import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.infra.InMemoryEstoqueService;
import br.edu.ifsp.orderflow.service.IEstoqueService;

import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {

			IEstoqueService estoqueService = new InMemoryEstoqueService();

			Produto mouse = new Produto("SKU-1", "Mouse sem fio", new BigDecimal("150.00"));

			Produto teclado = new Produto("SKU-2", "Teclado mecânico", new BigDecimal("350.00"));

			Produto monitor = new Produto("SKU-3", "Monitor", new BigDecimal("550.00"));

			Cliente ana = new Cliente("Ana", "Ana@Ana.com");
			Cliente bruno = new Cliente("Bruno", "Bruno@Bruno.com");

			Pedido pedido1 = new Pedido(ana);
			pedido1.adicionarItem(new ItemPedido(mouse, 2));
			pedido1.adicionarItem(new ItemPedido(teclado, 3));

			Pedido pedido2 = new Pedido(bruno);
			pedido2.adicionarItem(new ItemPedido(monitor, 4));
			pedido2.adicionarItem(new ItemPedido(teclado, 6));

			estoqueService.adicionarEstoque(mouse, 10);
			estoqueService.adicionarEstoque(teclado, 10);
			estoqueService.adicionarEstoque(monitor, 10);
			boolean reserva1 = estoqueService.reservar(pedido1);
			if(reserva1) {
				System.out.println("Pedido 1 reservado com sucesso!");
			} else {
				System.out.println("Não foi possível reservar o pedido 1.");
			}
			System.out.println(estoqueService.quantidadeDisponivelEstoque(monitor));
			System.out.println(estoqueService.quantidadeDisponivelEstoque(teclado));
			System.out.println(estoqueService.quantidadeDisponivelEstoque(mouse));

			estoqueService.liberar(pedido1);

			System.out.println(estoqueService.quantidadeDisponivelEstoque(monitor));
			System.out.println(estoqueService.quantidadeDisponivelEstoque(teclado));
			System.out.println(estoqueService.quantidadeDisponivelEstoque(mouse));

			System.out.println(pedido1);
			System.out.println(pedido2);
    }
}
