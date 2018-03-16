package me.developeralfa.githublookup;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by devalfa on 16/3/18.
 */

public class UserAsyncTask extends AsyncTask<String,Void,User> {
    public interface OnDownloadListener
    {
        void onDownload(User user);
    }

    public UserAsyncTask(OnDownloadListener downloadListener) {
        this.downloadListener = downloadListener;
    }

    OnDownloadListener downloadListener;
    @Override
    protected User doInBackground(String... strings) {
        String url = strings[0];
        try {
            URL url1 = new URL(url);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) url1.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();
            InputStream inputStream = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String result = "";
            while(scanner.hasNext())
            {
                result=result.concat(scanner.next());
            }
            Log.d("msg",result);
            JSONObject jsonObject = new JSONObject(result);
            User user = new User(jsonObject.getString("avatar_url"),jsonObject.getString("name"),jsonObject.getString("login"),jsonObject.getInt("public_repos"));
            return user;


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
    protected void onPostExecute(User user) {
        super.onPostExecute(user);
        downloadListener.onDownload(user);
    }
}
