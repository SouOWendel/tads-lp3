package br.edu.ifsp.orderflow.domain;

import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.EStatusPedido;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Pedido {
    private final String id;
    private final Cliente cliente;
    private final List<ItemPedido> itens;
		// private boolean foiReservado;
    private final EStatusPedido status;


    public Pedido (Cliente cliente) {
        this.id = (UUID.randomUUID().toString());
        this.cliente = cliente;
        this.itens = new ArrayList<ItemPedido>();
        this.status = EStatusPedido.CRIADO;
				// this.foiReservado = false;
    }

    public String getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public EStatusPedido getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Pedido [" + this.id + ", " + this.status + ", Total: R$ " + this.calcularTotal() + "]";
    }

    public void adicionarItem (ItemPedido itemPedido) {
        this.itens.add(itemPedido);
    }

    public BigDecimal calcularTotal() {
        BigDecimal total = BigDecimal.ZERO;
        for ( ItemPedido i : itens) {
            total = total.add(i.getSubtotal());
        }
        return total;
    }

    public void marcarComoPago() { this.status = EStatusPedido.PAGO; };
		public void marcarComoCriado() { this.status = EStatusPedido.CRIADO; };
    public void marcarComoCancelado() { this.status = EStatusPedido.CANCELADO; };

}
