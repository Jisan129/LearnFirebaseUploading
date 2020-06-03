package com.example.learnfirebase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    public static  final int PICK_IMAGE=1;
    private Button chosseFile;
    private Button upload;
    private EditText editFileName;
    private TextView showFile;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Uri imageUri;
    public static final String TAG ="Tag";

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
                openFileChooser();
            }
        });


    }

    private void openFileChooser() {
        Intent intent =new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE&&resultCode==RESULT_OK&& data!=null && data.getData()!=null){
            imageUri=data.getData();
            Picasso.get().load(imageUri).fit().into(imageView);
            Toast.makeText(getApplicationContext(),"Succesful",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(getApplicationContext(),"Not so God",Toast.LENGTH_SHORT).show();
        }
    }
}
