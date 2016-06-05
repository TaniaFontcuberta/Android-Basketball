package com.taniafontcuberta.basketball.model;

/**
 * Created by Alfredo on 28/02/2016.
 */
public class Player {
    Long id;
    String name;
    Integer baskets;
    Integer assists;
    Integer rebounds;
    String fieldPosition;
    String birthdate;
    Team team;

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

    public Integer getAssists() { return assists; }

    public void setAssists(Integer assists) { this.assists = assists; }

    public Integer getRebounds() { return rebounds; }

    public void setRebounds(Integer rebounds) { this.rebounds = rebounds; }

    public String getFieldPosition() { return fieldPosition; }

    public void setFieldPosition(String fieldPosition) { this.fieldPosition = fieldPosition; }

    public String getBirthdate() { return birthdate; }

    public void setBirthdate(String birthdate) { this.birthdate = birthdate; }

    public Team getTeam() { return team; }

    public void setTeam(Team team) { this.team = team; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != null ? !id.equals(player.id) : player.id != null) return false;
        if (name != null ? !name.equals(player.name) : player.name != null) return false;
        if (baskets != null ? !baskets.equals(player.baskets) : player.baskets != null)
            return false;
        if (assists != null ? !assists.equals(player.assists) : player.assists != null)
            return false;
        if (rebounds != null ? !rebounds.equals(player.rebounds) : player.rebounds != null)
            return false;
        if (fieldPosition != null ? !fieldPosition.equals(player.fieldPosition) : player.fieldPosition != null)
            return false;
        return birthdate != null ? birthdate.equals(player.birthdate) : player.birthdate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (baskets != null ? baskets.hashCode() : 0);
        result = 31 * result + (assists != null ? assists.hashCode() : 0);
        result = 31 * result + (rebounds != null ? rebounds.hashCode() : 0);
        result = 31 * result + (fieldPosition != null ? fieldPosition.hashCode() : 0);
        result = 31 * result + (birthdate != null ? birthdate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", baskets=" + baskets +
                ", assists=" + assists +
                ", rebounds=" + rebounds +
                ", fieldPosition='" + fieldPosition + '\'' +
                ", birthdate='" + birthdate + '\'' +
                '}';
    }
}
