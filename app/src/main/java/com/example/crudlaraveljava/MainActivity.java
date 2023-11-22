package com.example.crudlaraveljava;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://javaapi.shounen.tech/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BackEndAPI laraAPI = retrofit.create(BackEndAPI.class);

        Call<Map<String, List<Post>>> call = laraAPI.getPosts();

        Button createButton = findViewById(R.id.buttonCreate);
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Inisialisasi dialog
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.modal_input);

                EditText editTextNIM = dialog.findViewById(R.id.editTextNIM);
                EditText editTextNama = dialog.findViewById(R.id.editTextNama);
                EditText editTextStatus = dialog.findViewById(R.id.editTextStatus);
                Button submitButton = dialog.findViewById(R.id.buttonSubmit);

                // Ketika tombol submit pada dialog ditekan
                submitButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String nim = editTextNIM.getText().toString().trim();
                        String nama = editTextNama.getText().toString().trim();
                        String status = editTextStatus.getText().toString().trim();

                        Post post = new Post();
                        post.setNim(nim);
                        post.setNamaMahasiswa(nama);
                        post.setStatus(status);

                        Call<Post> createCall = laraAPI.createPost(post);
                        createCall.enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {
                                if (!response.isSuccessful()) {
                                    textViewResult.setText("Code: " + response.code());
                                    return;
                                }

                                Post postResponse = response.body();
                                String content = "";
                                content += "ID: " + postResponse.getId() + "\n";
                                content += "NIM: " + postResponse.getNim() + "\n";
                                content += "Nama: " + postResponse.getNamaMahasiswa() + "\n";
                                content += "Status: " + postResponse.getStatus() + "\n\n";

                                textViewResult.setText(content);
                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {
                                textViewResult.setText(t.getMessage());
                            }
                        });

                        dialog.dismiss();
                    }
                });

                dialog.show(); // Tampilkan dialog modal
            }
        });

        call.enqueue(new Callback<Map<String, List<Post>>>() {
            @Override
            public void onResponse(Call<Map<String, List<Post>>> call, Response<Map<String, List<Post>>> response) {
                if (!response.isSuccessful()) {
                    textViewResult.setText("Code: " + response.code());
                    return;
                }

                Map<String, List<Post>> responseData = response.body();
                if (responseData != null && responseData.containsKey("data")) {
                    List<Post> posts = responseData.get("data");

                    TableLayout tableLayout = findViewById(R.id.tableLayout);

                    for (Post post : posts) {
                        TableRow tableRow = new TableRow(MainActivity.this);
                        tableRow.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));

                        TextView textViewID = new TextView(MainActivity.this);
                        textViewID.setText(String.valueOf(post.getId()));
                        textViewID.setPadding(8, 8, 8, 8);
                        tableRow.addView(textViewID);

                        TextView textViewNIM = new TextView(MainActivity.this);
                        textViewNIM.setText(post.getNim());
                        textViewNIM.setPadding(8, 8, 8, 8);
                        tableRow.addView(textViewNIM);

                        TextView textViewNama = new TextView(MainActivity.this);
                        textViewNama.setText(post.getNamaMahasiswa());
                        textViewNama.setPadding(8, 8, 8, 8);
                        tableRow.addView(textViewNama);

                        TextView textViewStatus = new TextView(MainActivity.this);
                        textViewStatus.setText(post.getStatus());
                        textViewStatus.setPadding(8, 8, 8, 8);
                        tableRow.addView(textViewStatus);

                        // Button for Delete (Not Implemented)
                        Button deleteButton = new Button(MainActivity.this);
                        deleteButton.setText("Delete");
                        deleteButton.setPadding(12, 12, 12, 12);
                        deleteButton.setBackgroundColor(Color.parseColor("#F8D7DA"));
                        tableRow.addView(deleteButton);

                        tableLayout.addView(tableRow);
                    }
                } else {
                    textViewResult.setText("No data found");
                }
            }

            @Override
            public void onFailure(Call<Map<String, List<Post>>> call, Throwable t) {
                textViewResult.setText(t.getMessage());
            }
        });
    }
}
