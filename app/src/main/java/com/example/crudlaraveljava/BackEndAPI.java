package com.example.crudlaraveljava;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;

public interface BackEndAPI {
    @SerializedName("api/absensi")
    Call<List<Post>> getPosts();

}
