package com.example.crudlaraveljava;

import com.google.gson.annotations.SerializedName;

public class Post {

    private int id;
    @SerializedName("created_at")
    private String createdAt;
    @SerializedName("updated_at")
    private String updatedAt;
    private String nim;
    @SerializedName("nama_mahasiswa")
    private String namaMahasiswa;
    private String status;

    public void setId(int id) {
        this.id = id;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public void setNim(String nim) {
        this.nim = nim;
    }

    public void setNamaMahasiswa(String namaMahasiswa) {
        this.namaMahasiswa = namaMahasiswa;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public String getNim() {
        return nim;
    }

    public String getNamaMahasiswa() {
        return namaMahasiswa;
    }

    public String getStatus() {
        return status;
    }
}
