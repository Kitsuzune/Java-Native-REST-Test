package com.example.crudlaraveljava;

import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Body;

public interface BackEndAPI {
    @GET("absensi")
    Call<Map<String, List<Post>>> getPosts();

    @POST("absensi")
    Call<Post> createPost(@Body Post post);
}
