package br.com.atividade.model;

public class OrdemServico {

    private int id;
    private int veiculoId;
    private String descricao;
    private double valor;
    private String status;

    public OrdemServico() {
    }

    public OrdemServico(int id, int veiculoId, String descricao, double valor, String status) {
        this.id = id;
        this.veiculoId = veiculoId;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public OrdemServico(int veiculoId, String descricao, double valor, String status) {
        this.veiculoId = veiculoId;
        this.descricao = descricao;
        this.valor = valor;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(int veiculoId) {
        this.veiculoId = veiculoId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrdemServico{" +
                "id=" + id +
                ", veiculoId=" + veiculoId +
                ", descricao='" + descricao + '\'' +
                ", valor=" + valor +
                ", status='" + status + '\'' +
                '}';
    }
}