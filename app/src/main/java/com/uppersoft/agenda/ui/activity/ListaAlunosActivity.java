package com.uppersoft.agenda.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.uppersoft.agenda.R;
import com.uppersoft.agenda.dao.AlunoDAO;
import com.uppersoft.agenda.model.Aluno;
import com.uppersoft.agenda.ui.adpater.ListaAlunosAdapter;

import static com.uppersoft.agenda.ui.activity.ConstantesActivities.CHAVE_EDITA_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {

    public static final String ALERT_TITLE = "Removendo Aluno";
    public static final String ALERT_MESSAGE = "Tem certeza que deseja remover o aluno?";
    private final AlunoDAO dao = new AlunoDAO();
    public static final String TITULO_APPBAR = "Lista de Alunos";
    private ListaAlunosAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);
        setTitle(TITULO_APPBAR);
        configuraNovoAluno();
        configuraLista();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.activity_lista_alunos_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.activity_lista_alunos_menu_remover) {
            configuraAlertaRemoveAluno(item);
        } else {
            Aluno alunoEscolhido = extraiAlunoSelecionadoMenuDeContexto(item);
            abreFormularioModoEditaAluno(alunoEscolhido);
        }
        return super.onContextItemSelected(item);
    }

    private void configuraAlertaRemoveAluno(@NonNull final MenuItem item) {
        new AlertDialog.Builder(this)
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

    private Aluno extraiAlunoSelecionadoMenuDeContexto(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        return adapter.getItem(menuInfo.position);
    }

    private void configuraNovoAluno() {
        FloatingActionButton botaoNovoAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        botaoNovoAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abreFormularioModoInsereAluno();
            }
        });
    }

    private void abreFormularioModoInsereAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.atualiza(dao.todos());
    }

    private void configuraLista() {
        ListView listaAlunos = findViewById(R.id.activity_lista_alunos_listview);
        configuraAdapter(listaAlunos);
        configuraListernerDeCliquePorItem(listaAlunos);
        registerForContextMenu(listaAlunos);
    }

    private void removeAluno(Aluno alunoEscolhido) {
        dao.remove(alunoEscolhido);
        adapter.remove(alunoEscolhido);
    }

    private void configuraListernerDeCliquePorItem(ListView listaAlunos) {
        listaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioModoEditaAluno(Aluno alunoEscolhido) {
        Intent intent = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        intent.putExtra(CHAVE_EDITA_ALUNO, alunoEscolhido);
        startActivity(intent);
    }

    private void configuraAdapter(final ListView listaAlunos) {
        adapter = new ListaAlunosAdapter(this);
        listaAlunos.setAdapter(adapter);
    }
}
