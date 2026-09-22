package br.edu.ifsp.orderflow.events.handlers;

public class PagamentoAprovadoMetricasHandler implements IEventHandler<PagamentoAprovado> {
    private final Metricas metricas;

    PagamentoRecusadoMetricasHandler(Metrica metricas) {
        this.metricas = metricas;
    }

    @Override
    public void handle(PagamentoAprovado event) {

    }

    // eventType diz para qual tipo de evento a classe reage.
    @Override
    public void Class<PagamentoAprovado> eventType() {
        return PagamentoAprovado.class
    }
}
