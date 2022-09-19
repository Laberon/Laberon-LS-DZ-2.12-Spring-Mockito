package LaberonLSDZ2.service;

import java.util.Collection;

public interface TeamRepository {
    String add(String teamName);

    String find(String teamName);

    String remove(String teamName);

    Collection<String> findAll();
}
