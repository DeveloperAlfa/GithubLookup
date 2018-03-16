package me.developeralfa.githublookup;

/**
 * Created by devalfa on 16/3/18.
 */

public class User {
    String image;
    String name;
    String handle;
    int repos;

    public User(String image, String name, String handle, int repos) {
        this.image = image;
        this.name = name;
        this.handle = handle;
        this.repos = repos;
    }
}
