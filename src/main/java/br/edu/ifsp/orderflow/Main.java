package br.edu.ifsp.orderflow;

import br.edu.ifsp.orderflow.domain.Cliente;
import br.edu.ifsp.orderflow.domain.ItemPedido;
import br.edu.ifsp.orderflow.domain.Produto;
import java.math.BigDecimal;

public class Main {

    public static void main(String[] args) {
        Produto p1 = new Produto("SKU-1", "Mouse sem fio", new BigDecimal("150.00"));

        Produto p2 = new Produto("SKU-2", "Teclado mecânico", new BigDecimal("350.00"));

        Produto p3 = new Produto("SKU-3", "Monitor", new BigDecimal("550.00"));

        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);

        Cliente c1 = new Cliente("CLIENT-1", "Wendel Henrique", "souowendel@gmail.com");

        System.out.println(c1);

        ItemPedido ip1 = new ItemPedido(p1, 2);
        ItemPedido ip2 = new ItemPedido(p2, 3);
        ItemPedido ip3 = new ItemPedido(p3, 4);

        System.out.println(ip1);
        System.out.println(ip2);
        System.out.println(ip3);

    }
}
