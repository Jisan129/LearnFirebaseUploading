package com.example.learnfirebase;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {
    public static final int PICK_IMAGE = 1;
    public static final String TAG = "Tag";
    private Button chooseFile;
    private Button upload;
    private EditText editFileName;
    private TextView showFile;
    private ProgressBar progressBar;
    private ImageView imageView;
    private Uri imageUri;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chooseFile = findViewById(R.id.chooseing_btn);
        upload = findViewById(R.id.upload_btn);
        editFileName = findViewById(R.id.editText_chosing);
        showFile = findViewById(R.id.file_text);
        imageView = findViewById(R.id.imageView);
        progressBar = findViewById(R.id.progressBar);
        storageReference = FirebaseStorage.getInstance().getReference("Uploads");
        databaseReference = FirebaseDatabase.getInstance().getReference("Uploads");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadFile();

            }
        });

        chooseFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFileChooser();
            }
        });


    }


    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            Picasso.get().load(imageUri).fit().into(imageView);
            Toast.makeText(getApplicationContext(), "Succesful", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Not so God", Toast.LENGTH_SHORT).show();
        }
    }

    private String getFileExtension(Uri uri) {
        ContentResolver cr = getContentResolver();
        MimeTypeMap mim = MimeTypeMap.getSingleton();
        return mim.getExtensionFromMimeType(cr.getType(uri));
    }

    private void UploadFile() {
        Log.i(TAG, "UploadFile: "+imageUri);
        if (imageUri != null) {

            Log.i(TAG, "UploadFile: Hello");
            StorageReference fileReference = FirebaseStorage.getInstance().getReference().child("Jisan");
            UploadTask uploadTask = fileReference.putFile(imageUri);
            uploadTask
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            Log.i(TAG, "onProgress: "+taskSnapshot.getBytesTransferred()+" / "+taskSnapshot.getTotalByteCount());
                        }
                    })
                    .addOnSuccessListener(taskSnapshot -> Log.i(TAG, "UploadFile: successful"))
                    .addOnFailureListener(e -> Log.i(TAG, "UploadFile: " + e.getLocalizedMessage()));
        }
        /*if(imageUri!=null){
            StorageReference fileReference =storageReference.child(System.currentTimeMillis()+"."+getFileExtension(imageUri));
            fileReference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Handler handler=new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(0);
                        }
                    },5000);
                    Upload upload=new Upload(editFileName.getText().toString().trim(),
                            taskSnapshot.getUploadSessionUri().toString());
                    String uploadId=databaseReference.push().getKey();
                    databaseReference.child(uploadId).setValue(upload);
                }


            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(),"Failure ",Toast.LENGTH_SHORT).show();

                }
            }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                    double progress =(100.00*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    progressBar.setProgress((int)progress);
                }
            });
        }*/


        else {
           //Log.i(TAG, "UploadFile: ");
            Toast.makeText(getApplicationContext(), "No files Selected", Toast.LENGTH_SHORT).show();
        }


    }
}















