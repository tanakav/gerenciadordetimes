package br.com.codenation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import br.com.codenation.desafio.annotation.Desafio;
import br.com.codenation.desafio.app.MeuTimeInterface;

import br.com.codenation.desafio.exceptions.*;


public class DesafioMeuTimeApplication implements MeuTimeInterface {
    HashMap<Long,Time> times = new HashMap<>();
    HashMap<Long,Jogador> jogadores = new HashMap<>();

    @Desafio("incluirTime")
    public void incluirTime(Long id, String nome, LocalDate dataCriacao, String corUniformePrincipal, String corUniformeSecundario) {
        if (times.containsKey(id)) {
            throw new IdentificadorUtilizadoException("Identificador de Time ja inserido.");
        } else {
            Time novoTime = new Time(id, nome, dataCriacao, corUniformePrincipal, corUniformeSecundario);
            times.put(id,novoTime);
        }

    }

    @Desafio("incluirJogador")
    public void incluirJogador(Long id, Long idTime, String nome, LocalDate dataNascimento, Integer nivelHabilidade, BigDecimal salario) {

        if(jogadores.containsKey(id)){
            throw new IdentificadorUtilizadoException("Identificador de Jogador ja inserido.");
        }else if(times.containsKey(idTime)){
            Jogador novoJogador = new Jogador(id,idTime,nome,dataNascimento,nivelHabilidade,salario);
            jogadores.put(id,novoJogador);
        }else{
            throw new TimeNaoEncontradoException("Time nao encontrado.");
        }
    }

    @Desafio("definirCapitao")
    public void definirCapitao(Long idJogador) {

        if (jogadores.containsKey(idJogador)) {
            Jogador jogador = jogadores.get(idJogador);
            Long idTime = jogador.getIdTime();
            times.get(idTime).setCapitao(idJogador);

        }else{
            throw new JogadorNaoEncontradoException("Jogador nao encontrado");
        }
    }

    @Desafio("buscarCapitaoDoTime")
    public Long buscarCapitaoDoTime(Long idTime) {
        if(times.containsKey(idTime)){
            return times.get(idTime).getCapitao();
        }else{
            throw new TimeNaoEncontradoException("Time nao encontrado");
        }

    }

    @Desafio("buscarNomeJogador")
    public String buscarNomeJogador(Long idJogador) {
        if(jogadores.containsKey(idJogador)){
            return jogadores.get(idJogador).getNome();
        }else{
            throw new JogadorNaoEncontradoException("Jogador nao encontrado");
        }
    }

    @Desafio("buscarNomeTime")
    public String buscarNomeTime(Long idTime) {

        if(times.containsKey(idTime)){
            return times.get(idTime).getNome();
        }else{
            throw new TimeNaoEncontradoException("Time nao encontrado");
        }

    }

    @Desafio("buscarJogadoresDoTime")
    public List<Long> buscarJogadoresDoTime(Long idTime) {

        if(!times.containsKey(idTime)){
            throw new TimeNaoEncontradoException("Time nao encontrado");
        }else{
            islistaDeJogadoresVazia();

            List<Long> time = new ArrayList<>();

            for (Map.Entry<Long,Jogador> jogador:
                    jogadores.entrySet()) {
                if(jogador.getValue().getIdTime().equals(idTime)){
                    time.add(jogador.getValue().getId());
                }
            }
            Collections.sort(time);
            return time;

        }

    }

    @Desafio("buscarMelhorJogadorDoTime")
    public Long buscarMelhorJogadorDoTime(Long idTime) {
        List<Long> time = buscarJogadoresDoTime(idTime);
        Long melhorJogador=null;

        islistaDeJogadoresVazia();
        for(Long idJogador:time){
            if(melhorJogador==null || jogadores.get(idJogador).compareTo(jogadores.get(melhorJogador))>0){
                melhorJogador = idJogador;
            }else{
                if(jogadores.get(idJogador).compareTo(jogadores.get(melhorJogador))==0){
                    if(idJogador<melhorJogador) melhorJogador = idJogador;
                }
            }
        }
        return melhorJogador;

    }

    @Desafio("buscarJogadorMaisVelho")
    public Long buscarJogadorMaisVelho(Long idTime) {
        List<Long> time = buscarJogadoresDoTime(idTime);
        Long jogadorMaisVelho=null;

        islistaDeJogadoresVazia();
        for(Long idJogador : time){
            if(jogadorMaisVelho==null || jogadores.get(idJogador).getDataNascimento().isBefore(jogadores.get(jogadorMaisVelho).getDataNascimento())){
                jogadorMaisVelho = idJogador;
            }else{
                if(jogadores.get(idJogador).getDataNascimento().isEqual(jogadores.get(jogadorMaisVelho).getDataNascimento())){
                    if(idJogador<jogadorMaisVelho) jogadorMaisVelho = idJogador;
                }
            }
        }
        return jogadorMaisVelho;

    }

    @Desafio("buscarTimes")
    public List<Long> buscarTimes() {
        List<Long> listaTimes = new ArrayList<>();

        if(!times.isEmpty()){
            listaTimes.addAll(times.keySet());
            Collections.sort(listaTimes);
        }
        return listaTimes;
    }

    @Desafio("buscarJogadorMaiorSalario")
    public Long buscarJogadorMaiorSalario(Long idTime) {
        List<Long> time = buscarJogadoresDoTime(idTime);
        Long jogadorMaiorSalario=null;

        for(Long idJogador : time){
            if(jogadorMaiorSalario==null){
                jogadorMaiorSalario = jogadores.get(idJogador).getId();
            }else if(jogadores.get(idJogador).getSalario().compareTo(jogadores.get(jogadorMaiorSalario).getSalario())>0){
                jogadorMaiorSalario = jogadores.get(idJogador).getId();
            }else{
                if(jogadores.get(idJogador).getSalario().compareTo(jogadores.get(jogadorMaiorSalario).getSalario())==0){
                    if(idJogador<jogadorMaiorSalario) jogadorMaiorSalario = jogadores.get(idJogador).getId();
                }
            }
        }
        return jogadorMaiorSalario;

    }

    @Desafio("buscarSalarioDoJogador")
    public BigDecimal buscarSalarioDoJogador(Long idJogador) {
        if(jogadores.containsKey(idJogador)){
            return jogadores.get(idJogador).getSalario();
        }else{
            throw new JogadorNaoEncontradoException("Jogador nao encontrado.");
        }
    }

    @Desafio("buscarTopJogadores")
    public List<Long> buscarTopJogadores(Integer top) {
        List<Long> listaTop = new ArrayList<>();

        if(jogadores.isEmpty()){
            return listaTop;
        }else{
            Map<Long,Integer> listaIdHabilidade = new LinkedHashMap<>();

            for(Map.Entry jogador:jogadores.entrySet()){
                listaIdHabilidade.put((Long)jogador.getKey(),((Jogador)jogador.getValue()).getNivelHabilidade());
            }

            listaIdHabilidade = listaIdHabilidade.entrySet()
                    .stream()
                    .sorted(Map.Entry.<Long,Integer> comparingByValue().reversed())
                    .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e2,LinkedHashMap::new));

            for(Map.Entry entry : listaIdHabilidade.entrySet()){
                listaTop.add((Long) entry.getKey());
            }

            if(top>=listaTop.size()){
                return listaTop;
            }else{
                return listaTop.subList(0,top);
            }
        }

    }

    @Desafio("buscarCorCamisaTimeDeFora")
    public String buscarCorCamisaTimeDeFora(Long timeDaCasa, Long timeDeFora) {
        if(times.containsKey(timeDaCasa) && times.containsKey(timeDeFora)){
            Time obj_time_casa= times.get(timeDaCasa);
            Time obj_time_fora= times.get(timeDeFora);
            if(obj_time_casa.getCorUniformePrincipal().equals(obj_time_fora.getCorUniformePrincipal())){
                return obj_time_fora.getCorUniformeSecundario();
            }else{
                return obj_time_fora.getCorUniformePrincipal();
            }
        }else{
            throw new TimeNaoEncontradoException();
        }

    }

    private void islistaDeJogadoresVazia(){
        if(jogadores.isEmpty() || jogadores==null) throw new JogadorNaoEncontradoException("Lista de Jogadores Vazia");
    }


}