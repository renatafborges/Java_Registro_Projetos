package br.unicesumar.aula.controle;

import br.unicesumar.aula.exceptions.DadoConsultadoException;
import br.unicesumar.aula.modelo.Projeto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProjetoImpl implements br.unicesumar.aula.controle.ProjetoDAO {

    //Collection que irá armazenar todos os projetos
    private static Set<Projeto> projetos = new HashSet<>();


    @Override
    public void adicionar(Projeto projeto) throws DadoConsultadoException {
        for (Projeto projeto1: projetos){
            if (projeto.getNome().equalsIgnoreCase(projeto1.getNome())){
                throw new DadoConsultadoException("Projeto já existe na lista");
            }
        }
        projetos.add(projeto);
    }

    @Override
    public List<Projeto> listar() {
        List<Projeto> projetoList = new ArrayList<>();
        projetoList.addAll(projetos);
        return projetoList;
    }

    @Override
    public Projeto consultarPorNome(String nome) throws DadoConsultadoException {
        for (Projeto projeto: projetos){
            if (projeto.getNome().equalsIgnoreCase(nome)){
                return projeto;
            }
        }
        throw new DadoConsultadoException("Projeto não encontrado com o nome: "+nome);
    }

    @Override
    public Projeto alterar(String nome, Projeto projetoNovo) throws DadoConsultadoException {
        Projeto projetoEncontrado = consultarPorNome(nome);
        projetoEncontrado.setNome(projetoNovo.getNome());
        projetoEncontrado.setObjetivo(projetoNovo.getObjetivo());
        projetoEncontrado.setNecessidades(projetoNovo.getNecessidades());
        projetoEncontrado.setDataInicio(projetoNovo.getDataInicio());
        projetoEncontrado.setDataFinal(projetoNovo.getDataFinal());
        projetoEncontrado.setStatus(projetoNovo.getStatus());
        return projetoEncontrado;
    }

    @Override
    public void excluir(Projeto projeto) throws DadoConsultadoException, UnsupportedOperationException {
        Projeto projeto1 = consultarPorNome(projeto.getNome());
        if (projetos.contains(projeto)){
            projetos.remove(projeto);
            return;
        }
        throw new DadoConsultadoException("Não foi encontrado nenhum projeto com esse nome para exclusão");
    }

    @Override
    public void excluir(String nome) throws DadoConsultadoException, UnsupportedOperationException {
        Projeto projeto = consultarPorNome(nome);
        this.excluir(projeto);
    }
}