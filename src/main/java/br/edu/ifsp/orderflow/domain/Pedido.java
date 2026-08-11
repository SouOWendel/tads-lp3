package br.edu.ifsp.orderflow.domain;

import br.edu.ifsp.orderflow.domain.ItemPedido;

public class Pedido {
    private final ItemPedido[] itemPedido;

    public Pedido (ItemPedido itemPedido) {
        this.itemPedido = itemPedido;
    }

    public ItemPedido[] getItemPedido() {
        return itemPedido;
    }


}
