package br.com.codenation;

import java.time.LocalDate;
import java.lang.Long;
import java.util.Objects;

import br.com.codenation.desafio.exceptions.CapitaoNaoInformadoException;


public class Time{
    private Long id;
    private String nome;
    private LocalDate dataCriacao;
    private String corUniformePrincipal;
    private String corUniformeSecundario;
    private Long idCapitao;

    public Time(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        setId(id);
        setNome(nome);
        setDataCriacao(dataCriacao);
        setCorUniformePrincipal(corUniformePrincipal);
        setCorUniformeSecundario(corUniformeSecundario);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome.trim();
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {

        this.dataCriacao = dataCriacao;

    }

    public String getCorUniformePrincipal() {
        return corUniformePrincipal;
    }

    public void setCorUniformePrincipal(String corUniformePrincipal) {

        this.corUniformePrincipal = corUniformePrincipal;

    }

    public String getCorUniformeSecundario() {
        return corUniformeSecundario;
    }

    public void setCorUniformeSecundario(String corUniformeSecundario) {
        this.corUniformeSecundario = corUniformeSecundario;
    }

    public Long getCapitao() {
        if(idCapitao==null || idCapitao.equals("")){
            throw new CapitaoNaoInformadoException("Capitao nao informado");
        }else{
            return idCapitao;
        }

    }

    public void setCapitao(Long idCapitao) {
        this.idCapitao = idCapitao;
    }


}