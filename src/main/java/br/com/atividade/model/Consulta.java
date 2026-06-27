package br.com.atividade.model;

import java.time.LocalDate;

public class Consulta {

    private int id;
    private int animalId;
    private LocalDate data;
    private String motivo;
    private double valor;

    public Consulta() {
    }

    public Consulta(int id, int animalId, LocalDate data, String motivo, double valor) {
        this.id = id;
        this.animalId = animalId;
        this.data = data;
        this.motivo = motivo;
        this.valor = valor;
    }

    public Consulta(int animalId, LocalDate data, String motivo, double valor) {
        this.animalId = animalId;
        this.data = data;
        this.motivo = motivo;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnimalId() {
        return animalId;
    }

    public void setAnimalId(int animalId) {
        this.animalId = animalId;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Consulta{" +
                "id=" + id +
                ", animalId=" + animalId +
                ", data=" + data +
                ", motivo='" + motivo + '\'' +
                ", valor=" + valor +
                '}';
    }
}