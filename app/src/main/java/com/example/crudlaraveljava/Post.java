package com.example.crudlaraveljava;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int id;
    @SerializedName("body")
    private String nim;
    private String nama_mahasiswa;
    private String status;

    public int getId() {
        return id;
    }

    public String getNim() {
        return nim;
    }

    public String getNama_mahasiswa() {
        return nama_mahasiswa;
    }

    public String getStatus() {
        return status;
    }
}
