package br.edu.ifsp.orderflow.infra;


import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.service.IEstoqueService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEstoqueService implements IEstoqueService {

    // chave do produto | quantidade
    private final Map<String, Integer> estoque = new HashMap<>();

    @Override
    public void adicionarEstoque(Produto produto, int quantidade) {
        int qtdAtual = this.quantidadeDisponivelEstoque(produto);
        this.estoque.put(produto.getId(), quantidade + qtdAtual);
    }

    @Override
    /**
     * Quantidade disponível para um produto.
     * @param produto
     * @return a quantidade disponível do produto, ou null se o produto não estiver no estoque.
     */
    public Integer quantidadeDisponivelEstoque(Produto produto) {
        return this.estoque.getOrDefault(produto.getId(), 0);
    }

	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	// Queremos reservar quantidades que foram marcadas no pedido, tirando elas do estoque.
    @Override
    public boolean reservar(Pedido pedido) {
        List<ItemPedido> itens = pedido.getItens();
				for (ItemPedido item : itens) {
						Integer disponivel = this.quantidadeDisponivelEstoque(item.getProduto());
						// Se a quantidade do item no pedido for maior que a quantidade disponível
						// no estoque, não conseguimos reservar.
						if (item.getQuantidade() > disponivel) {
							System.out.println("Não há estoque suficiente para o produto " + item.getProduto().getNome() + ". Quantidade disponível: " + disponivel + ", quantidade solicitada: " + item.getQuantidade());
							return false;
						}
				}

				this.sleep(50);
				for (ItemPedido item : itens) {
					Produto produto = item.getProduto();
					String produtoId = produto.getId();
					Integer disponivel = this.quantidadeDisponivelEstoque(produto);
					this.estoque.put(produtoId, disponivel - item.getQuantidade());
				}
				// pedido.foiReservado = true;
				System.out.println("Estoque reservado para o pedido do cliente " + pedido.getCliente().getNome());
				return true;
    }

		// Queremos liberar quantidades que foram marcadas no pedido, devolvendo elas ao estoque.
    @Override
    public void liberar(Pedido pedido) {
			for (ItemPedido item : pedido.getItens()) {
				this.adicionarEstoque(item.getProduto(), item.getQuantidade());
			}
    }

		public void funcao() {
			System.out.println("Função de teste");
		}
}
