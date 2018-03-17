package me.developeralfa.githublookup;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by devalfa on 17/3/18.
 */

public class Repodapter extends BaseAdapter {
    ArrayList<Repo> repos;
    Context context;

    public Repodapter(ArrayList<Repo> repos, Context context) {
        this.repos = repos;
        this.context = context;
    }

    @Override
    public int getCount() {
        return repos.size();
    }

    @Override
    public Repo getItem(int position) {
        return repos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.repo,parent,false);
        TextView Name = view.findViewById(R.id.Name);
        Name.setText(repos.get(position).name);
        TextView Description = view.findViewById(R.id.Description);
        Description.setText(repos.get(position).description);
        return view;
    }
}
