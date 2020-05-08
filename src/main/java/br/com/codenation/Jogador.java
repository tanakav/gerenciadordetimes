package br.com.codenation;

import java.time.LocalDate;
import java.lang.Long;
import java.math.BigDecimal;
import java.lang.Comparable;

public class Jogador implements Comparable<Jogador>{
    private Long id;
    private Long idTime;
    private String nome;
    private LocalDate dataNascimento;
    private int nivelHabilidade;
    private BigDecimal salario;

    public Jogador(Long id, Long idTime, String nome, LocalDate dataNascimento, int nivelHabilidade, BigDecimal salario) {
        setId(id);
        setIdTime(idTime);
        setNome(nome);
        setDataNascimento(dataNascimento);
        setNivelHabilidade(nivelHabilidade);
        setSalario(salario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdTime() {
        return idTime;
    }

    public void setIdTime(Long idTime) {
        this.idTime = idTime;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {

        this.dataNascimento = dataNascimento;

    }

    public int getNivelHabilidade() {
        return nivelHabilidade;
    }

    public void setNivelHabilidade(int nivelHabilidade) {
        if(nivelHabilidade>=0 && nivelHabilidade<=100){
            this.nivelHabilidade = nivelHabilidade;
        }else{
            throw new IllegalArgumentException("Nivel de habilidade deve estar entre 0 e 100");
        }
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public void setSalario(BigDecimal salario) {

        this.salario = salario;
    }

    @Override
    public int compareTo(Jogador o) {
        return this.nivelHabilidade-o.getNivelHabilidade();
    }


}