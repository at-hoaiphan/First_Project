package com.example.gio.firstproject.thread_handler_asynctask;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gio.firstproject.R;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Copyright by Gio.
 * Created on 4/4/2017.
 */

public class DownloadImageActivity extends AppCompatActivity {

    private static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private ImageView imgdownloaded;
    private ProgressDialog progressDialog;
    private String downloadUrl = "http://static.srcdn.com/wp-content/uploads/X.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async_task);

        Button btnDownloadImage = (Button) findViewById(R.id.btnDownload);
        imgdownloaded = (ImageView) findViewById(R.id.imgDownloaded);

        btnDownloadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new ImageDownloader().execute(downloadUrl);
            }
        });
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                progressDialog = new ProgressDialog(this);
                progressDialog.setMessage("Downloading image...");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.setCancelable(false);
                progressDialog.show();
                return progressDialog;
            default:
                return null;
        }
    }

    private class ImageDownloader extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            int count;
            String filePath = "sdcard/My_downloaded_image2.jpg";

            try {

                URL url = new URL(params[0]);
                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lengthofFile = conexion.getContentLength();

                InputStream input = new BufferedInputStream(url.openStream());
                OutputStream output = new FileOutputStream(filePath);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lengthofFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
                Log.e("ImageDownloader", "Something went wrong while" +
                        " retrieving bitmap from " + params[0] + e.toString());
            }

            return filePath;
        }

        @Override
        protected void onPreExecute() {
            Log.i("AsyncTask Downloader", "onPreExecute Called");
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected void onPostExecute(String result) {
            Log.i("AsyncTask Downloader", "onPostExecute: ");
//                Picasso.with(getBaseContext()).load(Uri.parse(result)).into(imgdownloaded);
            imgdownloaded.setImageDrawable(Drawable.createFromPath(result));
            Log.d("path", "onPostExecute: " + result);
            Toast.makeText(DownloadImageActivity.this, "Download complete!", Toast.LENGTH_SHORT).show();
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected void onProgressUpdate(String... values) {
            Log.d("ANDRO_ASYNC", values[0]);
            progressDialog.setProgress(Integer.parseInt(values[0]));
        }
//        private Bitmap downloadBitmap(String url) {
//            // initilize the default HTTP client object
//            final DefaultHttpClient client = new DefaultHttpClient();
//
//            //forming a HttoGet request
//            final HttpGet getRequest = new HttpGet(url);
//            try {
//
//                HttpResponse response = client.execute(getRequest);
//
//                //check 200 OK for success
//                final int statusCode = response.getStatusLine().getStatusCode();
//
//                if (statusCode != HttpStatus.SC_OK) {
//                    Log.w("ImageDownloader", "Error " + statusCode +
//                            " while retrieving bitmap from " + url);
//                    return null;
//
//                }
//
//                final HttpEntity entity = response.getEntity();
//                if (entity != null) {
//                    InputStream inputStream = null;
//                    try {
//                        // getting contents from the stream
//                        inputStream = entity.getContent();
//
//                        // decoding stream data back into image Bitmap that android understands
//                        return BitmapFactory.decodeStream(inputStream);
//                    } finally {
//                        if (inputStream != null) {
//                            inputStream.close();
//                        }
//                        entity.consumeContent();
//                    }
//                }
//            } catch (Exception e) {
//                // You Could provide a more explicit error message for IOException
//                getRequest.abort();
//                Log.e("ImageDownloader", "Something went wrong while" +
//                        " retrieving bitmap from " + url + e.toString());
//            }
//
//            return null;
//        }
    }
}
