package com.example.learnfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button chosseFile;
    private Button upload;
    private EditText editFileName;
    private TextView showFile;
    private ProgressBar progressBar;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chosseFile=findViewById(R.id.chooseing_btn);
        upload=findViewById(R.id.upload_btn);
        editFileName =findViewById(R.id.editText_chosing);
        showFile=findViewById(R.id.file_text);
        imageView=findViewById(R.id.imageView);
        progressBar=findViewById(R.id.progressBar);

        chosseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}
