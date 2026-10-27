package br.edu.ifsp.orderflow.events.handlers;

public class Metricas {

    private int qtdAprovado = 0;
    private int qtdReprovado = 0;

    public void incrementarQtdAprovado() {
        this.qtdAprovado++;
    }

    public void incrementarQtdReprovado() {
        this.qtdReprovado++;
    }

    public int getQtdAprovado() {
        return qtdAprovado;
    }

    public int getQtdReprovado() {
        return qtdReprovado;
    }

    public String resumo() {
        return "Métricas:"
                + "\n- Pedidos Aprovados: " + this.qtdAprovado
                + "\n- Pedidos Recusados: " + this.qtdReprovado
                + "\n\n- Pagamentos Aprovados: " + this.qtdAprovado
                + "\n- Pagamentos Recusados: " + this.qtdReprovado
                + "\n\n- Pedidos Rec. Estoque Insuf.: " + this.qtdReprovado;

    }
}

/** Desafio
 * Pedidos Aprovados: m/N
 * Pedidos Recusados: m/N
 *
 * Pagamentos Aprovados: m/N
 * Pagamentos Recusados: m/N
 *
 * Pedidos Rec. Estoque Insuf.: m/N
 */