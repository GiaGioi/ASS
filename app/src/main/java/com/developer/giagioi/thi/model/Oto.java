package com.developer.giagioi.thi.model;

public class Oto {
    private String ID;
    private String name;
    private String nam;

    public Oto() {
    }

    public Oto(String ID, String name, String nam) {
        this.ID = ID;
        this.name = name;
        this.nam = nam;

    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNam() {
        return nam;
    }

    public void setNam(String nam) {
        this.nam = nam;
    }

    @Override
    public String toString() {
        return "Oto{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", nam='" + nam + '\'' +
                '}';
    }
}