package br.com.atividade.model;

import java.time.LocalDate;

public class Matricula {

    private int id;
    private int alunoId;
    private int cursoId;
    private LocalDate dataMatricula;
    private double valor;

    public Matricula() {
    }

    public Matricula(int id, int alunoId, int cursoId, LocalDate dataMatricula, double valor) {
        this.id = id;
        this.alunoId = alunoId;
        this.cursoId = cursoId;
        this.dataMatricula = dataMatricula;
        this.valor = valor;
    }

    public Matricula(int alunoId, int cursoId, LocalDate dataMatricula, double valor) {
        this.alunoId = alunoId;
        this.cursoId = cursoId;
        this.dataMatricula = dataMatricula;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public int getCursoId() {
        return cursoId;
    }

    public void setCursoId(int cursoId) {
        this.cursoId = cursoId;
    }

    public LocalDate getDataMatricula() {
        return dataMatricula;
    }

    public void setDataMatricula(LocalDate dataMatricula) {
        this.dataMatricula = dataMatricula;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Matricula{" +
                "id=" + id +
                ", alunoId=" + alunoId +
                ", cursoId=" + cursoId +
                ", dataMatricula=" + dataMatricula +
                ", valor=" + valor +
                '}';
    }
}