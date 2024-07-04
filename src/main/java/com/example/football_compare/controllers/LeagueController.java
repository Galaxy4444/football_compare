package com.example.football_compare.controllers; // Definiert das Paket, in dem sich diese Klasse befindet

import com.example.football_compare.models.Team; // Importiert die Team-Klasse
import com.example.football_compare.utils.CsvReader; // Importiert die CsvReader-Klasse
import org.springframework.stereotype.Controller; // Importiert die Spring-Controller-Annotation
import org.springframework.web.bind.annotation.GetMapping; // Importiert die GetMapping-Annotation
import org.springframework.ui.Model; // Importiert das Model-Interface für das Hinzufügen von Attributen zur Ansicht
import org.springframework.web.bind.annotation.PathVariable; // Importiert die PathVariable-Annotation
import org.springframework.web.bind.annotation.RequestParam; // Importiert die RequestParam-Annotation

import java.util.Comparator; // Importiert den Comparator zum Vergleichen von Objekten
import java.util.List; // Importiert die List-Klasse aus java.util
import java.util.Map; // Importiert die Map-Klasse aus java.util
import java.util.stream.Collectors; // Importiert die Collectors-Klasse aus java.util.stream

@Controller // Markiert diese Klasse als einen Spring MVC Controller
public class LeagueController {

    // Definiert eine statische Map, die Liga-IDs mit den Pfaden zu den entsprechenden CSV-Dateien verknüpft
    private static final Map<Integer, String> ID_CSV_PATH_MAP = Map.of(
            1, "C:\\Users\\glees\\Documents\\football_compare\\src\\main\\resources\\EPL_Standings.csv",
            2, "C:\\Users\\glees\\Documents\\football_compare\\src\\main\\resources\\SuperLeagueCSV.csv"
    );

    // Definiert eine GET-Mapping-Methode, die auf Anfragen an /league/{id} reagiert
    @GetMapping("/league/{id}")
    public String getPremierLeague(
            Model model,
            @RequestParam(name = "alpha", required = false) boolean isAlphabetical,
            @RequestParam(name = "losses", required = false) boolean isLosses,
            @RequestParam(name = "goalsFor", required = false) boolean goalsFor,
            @RequestParam(name = "goalsAgainst", required = false) boolean goalAgainst,
            @RequestParam(name = "goalDiff", required = false) boolean goalDiff,
            @RequestParam(name = "points", required = false) boolean points,
            @RequestParam(name = "wins", required = false) boolean isWin,
            @RequestParam(name = "draws", required = false) boolean isDraw,
            @PathVariable String id
    ) {
        // Liest die Teams aus der CSV-Datei, basierend auf der Liga-ID
        List<Team> teams = CsvReader.readTeamsFromCsv(ID_CSV_PATH_MAP.get(Integer.parseInt(id)));

        // Sortiert die Teams alphabetisch, falls angefordert
        if (isAlphabetical) {
            teams = teams.stream().sorted(Comparator.comparing(Team::getName)).collect(Collectors.toList());
            model.addAttribute("isAlphaChecked", "checked");
        }

        // Sortiert die Teams nach gewonnenen Spielen, falls angefordert
        if (isWin) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getWon).reversed()).collect(Collectors.toList());
            model.addAttribute("isWinChecked", "checked");
        }

        // Sortiert die Teams nach Unentschieden, falls angefordert
        if (isDraw) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getDrawn)).collect(Collectors.toList());
            model.addAttribute("isDrawChecked", "checked");
        }

        // Sortiert die Teams nach verlorenen Spielen, falls angefordert
        if (isLosses) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getLost).reversed()).collect(Collectors.toList());
            model.addAttribute("isLossesChecked", "checked");
        }

        // Sortiert die Teams nach geschossenen Toren, falls angefordert
        if (goalsFor) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getGoalsFor).reversed()).collect(Collectors.toList());
            model.addAttribute("isGFChecked", "checked");
        }

        // Sortiert die Teams nach kassierten Toren, falls angefordert
        if (goalAgainst) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getGoalsAgainst).reversed()).collect(Collectors.toList());
            model.addAttribute("isGAChecked", "checked");
        }

        // Sortiert die Teams nach Tordifferenz, falls angefordert
        if (goalDiff) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getGoalDifference).reversed()).collect(Collectors.toList());
            model.addAttribute("isGDChecked", "checked");
        }

        // Markiert den Punkte-Filter als ausgewählt, falls angefordert
        if (points) {
            model.addAttribute("normal", "checked");
        }

        // Fügt die Teams und die ausgewählte Liga zum Modell hinzu
        model.addAttribute("teams", teams);
        model.addAttribute("selectedLeague", Integer.parseInt(id));

        // Gibt den Namen der Ansicht zurück
        return "index";
    }
}
