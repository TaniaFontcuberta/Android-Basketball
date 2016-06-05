package com.taniafontcuberta.basketball.model;

/**
 * Created by Alfredo on 28/02/2016.
 */
public class Player {
    Long id;
    String name;
    Integer baskets;

    public Player() {
    }

    public Player(Long id, String name, Integer baskets) {
        this.id = id;
        this.name = name;
        this.baskets = baskets;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBaskets() {
        return baskets;
    }

    public void setBaskets(Integer baskets) {
        this.baskets = baskets;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (!id.equals(player.id)) return false;
        if (!name.equals(player.name)) return false;
        return !(baskets != null ? !baskets.equals(player.baskets) : player.baskets != null);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + (baskets != null ? baskets.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baskets=" + baskets +
                '}';
    }
}
