package com.wikimediadata.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "wikimedia")
public class Wikimedia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private long id;
    @Lob
    private String data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Wikimedia{" +
                "id=" + id +
                ", data='" + data + '\'' +
                '}';
    }
}
