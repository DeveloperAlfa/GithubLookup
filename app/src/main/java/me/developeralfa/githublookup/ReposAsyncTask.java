package me.developeralfa.githublookup;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by devalfa on 17/3/18.
 */

public class ReposAsyncTask extends AsyncTask<String,Void,ArrayList<Repo>> {
    public interface OnDownloadListener
    {
        void onDownload(ArrayList<Repo> r);
    }

    public ReposAsyncTask(OnDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    OnDownloadListener downloadListener;
    @Override
    protected ArrayList<Repo> doInBackground(String... strings) {
        ArrayList<Repo> repos = new ArrayList<>();
        String url = strings[0];
        try {
            URL url1 = new URL(url);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url1.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();
            InputStream inputStream = httpsURLConnection.getInputStream();
            String result ="";
            Scanner scanner = new Scanner(inputStream);
            while (scanner.hasNext())
            {
                result = result.concat(scanner.next());
            }
            Log.d("res",result);
            JSONArray jsonArray = new JSONArray(result);
            for(int i=0;i<jsonArray.length();i++)
            {

                Repo repo = new Repo(jsonArray.getJSONObject(i).getString("name"),jsonArray.getJSONObject(i).getString("html_url"),jsonArray.getJSONObject(i).getString("description"));
                repos.add(repo);
            }
            return repos;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Repo> repos) {
        super.onPostExecute(repos);
        downloadListener.onDownload(repos);
    }
}
