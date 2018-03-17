package me.developeralfa.githublookup;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class Details extends AppCompatActivity {

    UserAsyncTask userAsyncTask;
    ReposAsyncTask reposAsyncTask;
    Repodapter repodapter;
    ArrayList<Repo> repos = new ArrayList<>();
    ImageView imageView;
    TextView name;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent p = getIntent();
        imageView = findViewById(R.id.img);
        listView = findViewById(R.id.Repos);
        repodapter = new Repodapter(repos,this);
        listView.setAdapter(repodapter);
        reposAsyncTask = new ReposAsyncTask(new ReposAsyncTask.OnDownloadListener() {
            @Override
            public void onDownload(ArrayList<Repo> r) {
                repos.clear();
                repos.addAll(r);
                repodapter.notifyDataSetChanged();
            }
        });
        reposAsyncTask.execute("https://api.github.com/users/"+p.getStringExtra("text")+"/repos");
        name = findViewById(R.id.name);
        userAsyncTask = new UserAsyncTask(new UserAsyncTask.OnDownloadListener() {
            @Override
            public void onDownload(User user) {
                name.setText(user.name);
                ImageAsyncTask imageAsyncTask = new ImageAsyncTask(new ImageAsyncTask.OnDownloadListener() {
                    @Override
                    public void onDownload(Bitmap bitmap) {
                        imageView.setImageBitmap(bitmap);
                    }
                });
                imageAsyncTask.execute(user.image);

            }
        });
        userAsyncTask.execute("https://api.github.com/users/"+p.getStringExtra("text"));
    }
}
