package net.benmclean.quakequestswitcher;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.util.Arrays;

public class MainActivity extends FragmentActivity implements RecyclerViewAdapter.OnItemListener {
    private String path = FilenameUtils.concat(
            Environment.getExternalStorageDirectory().getPath(),
            "QuakeQuest/commandline"
    );
    private String dest = FilenameUtils.concat(
            Environment.getExternalStorageDirectory().getPath(),
            "QuakeQuest/commandline.txt"
    );
    static final int WRITE_EXST = 0x3;
    static final int READ_EXST = 0x4;
    private final int request_code = 61996;
    private RecyclerView recyclerView;
    private RecyclerViewAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.FileRecyclerView);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        askForPermission(Manifest.permission.READ_EXTERNAL_STORAGE, READ_EXST);

        File[] files = new File(path).listFiles();

        if (files == null || files.length < 1) {
            ((TextView) findViewById(R.id.Title)).setText("\"" + path + "\" has no text files!");
            return; // Can't go on to list files which aren't there.
        }

        String[] listFiles = new String[files.length];
        for (int x = 0; x < listFiles.length; x++)
            listFiles[x] = files[x].getName();
        Arrays.sort(listFiles);

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
            Log.v("MainActivity", "" + permission + " is already granted.");
        }
    }

    @Override
    public void onItemClick(int position) {
        askForPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE, WRITE_EXST);

        String source = FilenameUtils.concat(
                path,
                mAdapter.strings[position]
        );
        try {
            copy(new File(source), new File(dest));
            ((TextView) findViewById(R.id.Title)).setText("SUCCESS: \"" + mAdapter.strings[position] + "\"");
        } catch (IOException e) {
            e.printStackTrace();
            ((TextView) findViewById(R.id.Title)).setText("FAILED to copy \"" + mAdapter.strings[position] + "\"!");
        }
    }

    public static void copy(File src, File dst) throws IOException {
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(src);
            out = new FileOutputStream(dst);
            IOUtils.copy(in, out);
        } finally {
            if (in != null) in.close();
            if (out != null) out.close();
        }
    }
}
