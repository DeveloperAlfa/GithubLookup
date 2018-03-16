package me.developeralfa.githublookup;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by devalfa on 16/3/18.
 */

public class ImageAsyncTask extends AsyncTask<String,Void,Bitmap> {
    public interface OnDownloadListener
    {
        void onDownload(Bitmap bitmap);
    }
    OnDownloadListener downloadListener;

    public ImageAsyncTask(OnDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }


    @Override
    protected Bitmap doInBackground(String... strings) {
        String url = strings[0];
        try {
            URL urlConnection = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) urlConnection
                    .openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);
        downloadListener.onDownload(bitmap);
    }
}
