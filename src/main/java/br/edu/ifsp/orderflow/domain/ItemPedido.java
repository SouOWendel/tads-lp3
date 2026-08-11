package br.edu.ifsp.orderflow.domain;

import br.edu.ifsp.orderflow.domain.Produto;

public class ItemPedido {
    private final Produto produto;
    private final int quantidade;

    public ItemPedido(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    @Override
    public String toString() {
        return "Item do Pedido X: " + this.produto + ", X" + this.quantidade;
    }
}
