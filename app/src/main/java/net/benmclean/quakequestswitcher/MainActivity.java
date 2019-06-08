package net.benmclean.quakequestswitcher;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

public class MainActivity extends FragmentActivity implements RecyclerViewAdapter.OnItemListener {
    private String path;
    static final int WRITE_EXST = 0x3;
    static final int READ_EXST = 0x4;
    private final int request_code = 61996;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    String[] subjects = {"ANDROID", "PHP", "ASP.NET", "JAVA", "C++"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        path = Environment.getExternalStorageDirectory().getPath() + "/QuakeQuest";

        recyclerView = (RecyclerView) findViewById(R.id.FileRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXST);

        File[] files = new File(path + "/commandline").listFiles();

        if (files == null || files.length < 1) {
            Toast.makeText(this, path + "/commandline has no text files!", Toast.LENGTH_LONG).show();
            return; // Can't go on to list files which aren't there.
        }

        String[] listFiles = new String[files.length];
        for (int x = 0; x < listFiles.length; x++)
            listFiles[x] = files[x].getName();

        mAdapter = new RecyclerViewAdapter(this, listFiles, this);
        recyclerView.setAdapter(mAdapter);
    }

    private void askForPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {
                //This is called if user has denied the permission before
                //In this case I am just asking the permission again
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
            }
        } else {
//            Toast.makeText(this, "" + permission + " is already granted.", Toast.LENGTH_SHORT).show();
            Log.v("MainActivity", "" + permission + " is already granted.");
        }
    }

    @Override
    public void onItemClick(int position) {
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXST);

        String source = path + "/commandline/" + mAdapter.strings[position];
        String destination = path + "/commandline.txt";
        try {
            copyFile(new File(source), new File(destination));
            Toast.makeText(this, "SUCCESS: overwrote commandline.txt with \"" + mAdapter.strings[position] + "\"", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "FAILED to copy \"" + source + "\" to \"" + destination + "\"!", Toast.LENGTH_LONG).show();
        }
    }

    public static void copyFile(File sourceFile, File destFile) throws IOException {
        if (!destFile.getParentFile().exists()) destFile.getParentFile().mkdirs();
        if (!destFile.exists()) destFile.createNewFile();
        FileChannel source = null;
        FileChannel destination = null;
        try {
            source = new FileInputStream(sourceFile).getChannel();
            destination = new FileOutputStream(destFile).getChannel();
            destination.transferFrom(source, 0, source.size());
        } finally {
            if (source != null) source.close();
            if (destination != null) destination.close();
        }
    }
}
