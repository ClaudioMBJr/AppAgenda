package com.uppersoft.agenda.model;

import java.io.Serializable;

public class Aluno implements Serializable {
    private int id = 0;
    private String nome;
    private String tel;
    private String email;


    public Aluno() {
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getTel() {
        return tel;
    }

    public String getEmail() {
        return email;
    }

//    @Override
//    @NonNull
//    public String toString() {
//        return nome + tel;
//    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }
}