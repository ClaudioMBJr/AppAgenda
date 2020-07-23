package com.uppersoft.agenda.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import com.uppersoft.agenda.dao.AlunoDAO;
import com.uppersoft.agenda.model.Aluno;
import com.uppersoft.agenda.ui.adpater.ListaAlunosAdapter;

public class ListaAlunosView {

    private static final String ALERT_TITLE = "Removendo Aluno";
    private static final String ALERT_MESSAGE = "Tem certeza que deseja remover o aluno?";
    private final ListaAlunosAdapter adapter;
    private final AlunoDAO dao;
    private final Context context;

    public ListaAlunosView(Context context) {
        this.context = context;
        this.adapter = new ListaAlunosAdapter(this.context);
        this.dao = new AlunoDAO();
    }

    public void configuraAlertaRemoveAluno(@NonNull final MenuItem item) {
        new AlertDialog.Builder(this.context)
                .setTitle(ALERT_TITLE)
                .setMessage(ALERT_MESSAGE)
                .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Aluno alunoEscolhido = extraiAlunoSelecionadoMenuDeContexto(item);
                        removeAluno(alunoEscolhido);
                    }
                })
                .setNegativeButton("Não", null)
                .show();
    }

    public void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    public void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    public void configuraAdapter(final ListView listaAlunos) {
        listaAlunos.setAdapter(adapter);
    }
    public Aluno extraiAlunoSelecionadoMenuDeContexto(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return adapter.getItem(menuInfo.position);
    }
}
