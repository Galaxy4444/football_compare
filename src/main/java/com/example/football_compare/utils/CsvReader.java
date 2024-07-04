package com.example.football_compare.utils; // Definiert das Paket, in dem sich diese Klasse befindet

import com.example.football_compare.models.Team; // Importiert die Team-Klasse
import com.opencsv.CSVReader; // Importiert den CSVReader aus der OpenCSV-Bibliothek
import com.opencsv.exceptions.CsvException; // Importiert die CsvException aus der OpenCSV-Bibliothek

import java.io.FileReader; // Importiert die FileReader-Klasse für das Lesen von Dateien
import java.io.IOException; // Importiert die IOException-Klasse für das Handling von IO-Ausnahmen
import java.util.List; // Importiert die List-Klasse aus java.util
import java.util.stream.Collectors; // Importiert die Collectors-Klasse aus java.util.stream

public class CsvReader {

    // Methode zum Lesen der Teams aus einer CSV-Datei
    public static List<Team> readTeamsFromCsv(String filePath) {
        try (CSVReader reader = new CSVReader(new FileReader(filePath))) { // Öffnet den CSVReader im Try-With-Resources Block
            return reader.readAll().stream() // Liest alle Zeilen der CSV-Datei und erstellt einen Stream daraus
                    .skip(1) // Überspringt die erste Zeile (Header)
                    .map(CsvReader::mapToTeam) // Mappt jede Zeile auf ein Team-Objekt
                    .collect(Collectors.toList()); // Sammel die Team-Objekte in einer Liste
        } catch (IOException | CsvException e) { // Fängt IO- und CSV-Ausnahmen ab
            e.printStackTrace(); // Druckt den Stacktrace der Ausnahme
            throw new RuntimeException("Failed to read CSV file", e); // Wirft eine Laufzeitausnahme mit einer Fehlermeldung
        }
    }

    // Hilfsmethode zum Mapping eines String-Arrays auf ein Team-Objekt
    private static Team mapToTeam(String[] data) {
        Team team = new Team(); // Erstellt ein neues Team-Objekt
        team.setId(Long.parseLong(data[0].trim())); // Setzt die ID des Teams
        team.setName(data[1].trim()); // Setzt den Namen des Teams
        team.setPlayed(Integer.parseInt(data[2].trim())); // Setzt die Anzahl der gespielten Spiele
        team.setWon(Integer.parseInt(data[3].trim())); // Setzt die Anzahl der gewonnenen Spiele
        team.setDrawn(Integer.parseInt(data[4].trim())); // Setzt die Anzahl der Unentschieden
        team.setLost(Integer.parseInt(data[5].trim())); // Setzt die Anzahl der verlorenen Spiele
        team.setGoalsFor(Integer.parseInt(data[6].trim())); // Setzt die Anzahl der geschossenen Tore
        team.setGoalsAgainst(Integer.parseInt(data[7].trim())); // Setzt die Anzahl der kassierten Tore
        team.setGoalDifference(Integer.parseInt(data[8].trim())); // Setzt die Tordifferenz
        team.setPoints(Integer.parseInt(data[9].trim())); // Setzt die Anzahl der Punkte
        return team; // Gibt das Team-Objekt zurück
    }

}
