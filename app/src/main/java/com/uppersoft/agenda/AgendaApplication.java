package com.uppersoft.agenda;

import android.app.Application;

import com.uppersoft.agenda.dao.AlunoDAO;
import com.uppersoft.agenda.model.Aluno;

public class AgendaApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        criaAlunosTeste();
    }

    private void criaAlunosTeste() {
        AlunoDAO dao = new AlunoDAO();
        dao.salva(new Aluno("Claudio","(27) 996125652","claudio@gmail.com"));
        dao.salva(new Aluno("Isabelly","(27) 999998888","isa@gmail.com"));
        dao.salva(new Aluno("Gabriel","(27) 888889999","gabriel@gmail.com"));
        dao.salva(new Aluno("Calebi","(27) 9874552163","calebi@gmail.com"));
        dao.salva(new Aluno("Luna","(27) 123456789","luna@gmail.com"));
        dao.salva(new Aluno("Heitor","(27) 123456456","heitor@gmail.com"));
    }
}
