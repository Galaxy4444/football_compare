package com.example.football_compare.controllers;

import com.example.football_compare.models.Team;
import com.example.football_compare.utils.CsvReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LeagueController {

    private static final Map<Integer, String> ID_CSV_PATH_MAP = Map.of(1, "C:\\Users\\glees\\Documents\\football_compare\\src\\main\\resources\\EPL_Standings.csv"
            , 2, "C:\\Users\\glees\\Documents\\football_compare\\src\\main\\resources\\SuperLeagueCSV.csv");

    @GetMapping("/league/{id}")
    public String getPremierLeague(Model model, @RequestParam(name = "alpha", required = false) boolean isAlphabetical , @RequestParam(name = "losses", required = false) boolean isLosses, @RequestParam(name = "goalsFor", required = false) boolean goalsFor, @RequestParam(name = "goalsAgainst", required = false) boolean goalAgainst, @RequestParam(name = "goalDiff", required = false) boolean goalDiff, @RequestParam(name = "points", required = false) boolean points, @RequestParam(name = "wins", required = false) boolean isWin, @RequestParam(name = "draws", required = false) boolean isDraw, @PathVariable String id) {
        List<Team> teams = CsvReader.readTeamsFromCsv(ID_CSV_PATH_MAP.get(Integer.parseInt(id)));
        if (isAlphabetical) {
            teams = teams.stream().sorted(Comparator.comparing(Team::getName)).collect(Collectors.toList());
            model.addAttribute("isAlphaChecked", "checked");
        }
        if (isWin) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getWon).reversed()).collect(Collectors.toList());
            model.addAttribute("isWinChecked", "checked");
        }
        if (isDraw) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getDrawn)).collect(Collectors.toList());
            model.addAttribute("isDrawChecked", "checked");
        }

        if (isLosses) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getLost).reversed()).collect(Collectors.toList());
            model.addAttribute("isLossesChecked", "checked");
        }
        if (goalsFor) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getGoalsFor).reversed()).collect(Collectors.toList());
            model.addAttribute("isGFChecked", "checked");
        }

        if (goalAgainst) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getGoalsAgainst).reversed()).collect(Collectors.toList());
            model.addAttribute("isGAChecked", "checked");
        }
        if (goalDiff) {
            teams = teams.stream().sorted(Comparator.comparingInt(Team::getGoalDifference).reversed()).collect(Collectors.toList());
            model.addAttribute("isGDChecked", "checked");
        }
        if (points) {
            model.addAttribute("normal", "checked");
        }
        model.addAttribute("teams", teams);
        model.addAttribute("selectedLeague", Integer.parseInt(id));
        return "index";
    }


}
