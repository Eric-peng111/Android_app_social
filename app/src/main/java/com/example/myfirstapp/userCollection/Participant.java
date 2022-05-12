package com.example.myfirstapp.userCollection;

import java.io.Serializable;

public interface Participant extends Comparable<User>,Serializable {
    int compareTo();
    String getUsername();
    String getPassword();
    int hashCode();
    int getID();
}
