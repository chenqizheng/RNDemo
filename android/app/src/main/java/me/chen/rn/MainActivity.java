package me.chen.rn;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    public static final String BUNDLE_PATH = "http://172.29.182.3:8887/";
    public static final String BUNDLE_FILE = ".bundle.js";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.demo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRNModule("demo");
            }
        });
        findViewById(R.id.demo2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRNModule("demo2");
            }
        });
    }

    private void openRNModule(String moduleName) {
        String path = getCacheBundlePath(moduleName);
        File bundleFile = new File(path);
        if (bundleFile != null && bundleFile.exists()) {
            Intent intent = new Intent(MainActivity.this, RNActivity.class);
            intent.putExtra(RNActivity.EXTRA_BUNDLE_PATH, path);
            intent.putExtra(RNActivity.EXTRA_MODULE_NAME, moduleName);
            startActivity(intent);
        } else {
            downloadBundle(moduleName);
        }
    }

    private void downloadBundle(String moduleName) {
        String remoteBundlePath = getRemoteBundlePath(moduleName);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(remoteBundlePath));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationUri(Uri.parse("file://" + getCacheBundlePath(moduleName)));
        DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        dm.enqueue(request);
    }

    private String getCacheBundlePath(String moduleName) {
        return getExternalCacheDir() + File.separator + moduleName + BUNDLE_FILE;
    }

    private String getRemoteBundlePath(String moduleName) {
        return BUNDLE_PATH + moduleName + BUNDLE_FILE;
    }
}
