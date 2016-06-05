package com.taniafontcuberta.basketball.model;

import android.util.Log;


public class Team {
    Long id;
    String name;
    String locality;

    public Team() {
    }

    public Team(Long id, String name, String locality) {
        this.id = id;
        this.name = name;
        this.locality = locality;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getLocality() { return locality; }

    public void setLocality(String locality) { this.locality = locality; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != null ? !id.equals(team.id) : team.id != null) return false;
        if (!name.equals(team.name)) return false;
        return locality != null ? locality.equals(team.locality) : team.locality == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return name;
    }
}
