package com.example.football_compare.models; // Definiert das Paket, in dem sich diese Klasse befindet

import jakarta.persistence.Entity; // Importiert die JPA-Annotationen
import jakarta.persistence.Id;

@Entity // Markiert diese Klasse als JPA-Entity, die mit einer Datenbanktabelle verbunden ist
public class Team {
    private Long id; // Primärschlüssel der Entität

    private String name; // Name des Teams

    private int played; // Anzahl der gespielten Spiele

    private int won; // Anzahl der gewonnenen Spiele

    private int drawn; // Anzahl der Unentschieden

    private int lost; // Anzahl der verlorenen Spiele

    private int goalsFor; // Anzahl der geschossenen Tore
    private int goalsAgainst; // Anzahl der kassierten Tore
    private int goalDifference; // Tordifferenz (goalsFor - goalsAgainst)
    private int points; // Anzahl der Punkte

    // Getter- und Setter-Methoden für den Zugriff und die Modifikation der Felder

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPlayed() {
        return played;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }

    public void setWon(int won) {
        this.won = won;
    }

    public int getDrawn() {
        return drawn;
    }

    public void setDrawn(int drawn) {
        this.drawn = drawn;
    }

    public int getLost() {
        return lost;
    }

    public void setLost(int lost) {
        this.lost = lost;
    }

    public int getGoalsFor() {
        return goalsFor;
    }

    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }

    public void setGoalDifference(int goalDifference) {
        this.goalDifference = goalDifference;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id // Markiert diese Methode als diejenige, die den Primärschlüssel zurückgibt
    public Long getId() {
        return id;
    }
}
