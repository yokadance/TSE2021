package uy.vacunas.ui.login;


public class User {
    private String name;
    private String surNmae;
    private String ci;


    public User(String name, String surNmae, String ci) {
        this.name = name;
        this.surNmae = surNmae;
        this.ci = ci;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurNmae() {
        return surNmae;
    }

    public void setSurNmae(String surNmae) {
        this.surNmae = surNmae;
    }

    public String getCi() {
        return ci;
    }

    public void setCi(String ci) {
        this.ci = ci;
    }

}