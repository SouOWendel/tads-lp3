package br.edu.ifsp.orderflow.infra;


import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Pedido;
import br.edu.ifsp.orderflow.domain.Produto;
import br.edu.ifsp.orderflow.service.IEstoqueService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryEstoqueService implements IEstoqueService {

    // chave do produto | quantidade
    private final Map<String, Integer> estoque = new HashMap<>();

    @Override
    public void adicionarEstoque(Produto produto, int quantidade) {
        int qtdAtual = this.estoque.getOrDefault(produto.getId(), 0);
        this.estoque.put(produto.getId(), quantidade + qtdAtual);
    }

    @Override
    public int quantidadeDisponivel(Produto produto) {
        return this.estoque.getOrDefault(produto.getId(), 0);
    }

    // Queremos reservar quantidades que foram marcadas no pedido, tirando elas do estoque.
    @Override
    public boolean reservar(Pedido pedido) {
        Produto produto = pedido.getItens().get();
        this.estoque.getOrDefault()
        return false;
    }

    @Override
    public void liberar(Pedido pedido) {
        ArrayList<ItemPedido> itens = (ArrayList) pedido.getItens();

    }
}
