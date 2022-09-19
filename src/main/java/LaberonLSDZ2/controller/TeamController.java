package LaberonLSDZ2.controller;

import LaberonLSDZ2.service.TeamRepositoryImpl;
import LaberonLSDZ2.service.TeamService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping(path = "/team")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping(path = "/add")
    public String add(@RequestParam String teamName) {
        return teamService.add(teamName);
    }

    @GetMapping(path = "/find")
    public String find(@RequestParam String teamName) {
        return teamService.find(teamName);
    }
    @GetMapping(path = "/remove")
    public String remove(@RequestParam String teamName) {
        return teamService.remove(teamName);
    }

    @GetMapping(path = "/all")
    public Collection<String> findTeams(String prefix) {
        if (prefix != null) {
            return teamService.findTeamsByPrefix(prefix);
        }
        return teamService.findTeams();
    }

    @GetMapping(path = "/all/format")
    public String findTeamsAsString() {
        return teamService.findTeamsAsString();
    }
}
