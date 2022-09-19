package LaberonLSDZ2.service;

import LaberonLSDZ2.exception.TeamExistsException;
import LaberonLSDZ2.exception.TeamNotExistException;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Repository
public class TeamRepositoryImpl implements TeamRepository {

    private final Set<String> teams = new HashSet<>();

    @Override
    public String add(String teamName) {
        if (teams.contains(teamName)) {
            throw new TeamExistsException();
        }
        teams.add(teamName);
        return teamName;
    }

    @Override
    public String remove(String teamName) {
        if (teams.contains(teamName)) {
            teams.remove(teamName);
            return teamName;
        }
        throw new TeamNotExistException();
    }

    @Override
    public String find(String teamName) {
        if (!teams.contains(teamName)) {
            throw new TeamNotExistException();
        }
        return teamName;
    }

    @Override
    public Collection<String> findAll() {
        return Collections.unmodifiableSet(teams);
    }
}
