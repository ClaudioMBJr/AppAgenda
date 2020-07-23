package com.uppersoft.agenda.dao;

import com.uppersoft.agenda.model.Aluno;

import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private final static List<Aluno> alunos = new ArrayList<>();
    private static int contadorDeIds = 1;

    public void salva(Aluno alunoCriado) {
        alunoCriado.setId(contadorDeIds);
        alunos.add(alunoCriado);
        contadorDeIds++;
    }

    public void edita(Aluno aluno) {
        Aluno alunoEncontrado = buscaAlunoPorId(aluno);
        if (alunoEncontrado != null) {
            int posicaoAluno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoAluno, aluno);
        }
    }

    private Aluno buscaAlunoPorId(Aluno aluno) {
        for (Aluno a :
                alunos) {
            if (a.getId() == aluno.getId()) {
                return a;
            }
        }
        return null;
    }

    public List<Aluno> todos() {
        return new ArrayList<>(alunos); //envia uma cópia como retorno de modo que não seja acessível o array original
    }

    public void remove(Aluno alunoEscolhido) {
        Aluno alunoDevolvido = buscaAlunoPorId(alunoEscolhido);
        if (alunoDevolvido != null) {
            alunos.remove(alunoDevolvido);
        }
    }
}
