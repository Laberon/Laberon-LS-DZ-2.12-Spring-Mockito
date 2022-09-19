package LaberonLSDZ2.service;

import LaberonLSDZ2.exception.TeamExistsException;
import LaberonLSDZ2.exception.TeamNotExistException;
import LaberonLSDZ2.service.TeamRepository;
import LaberonLSDZ2.service.TeamServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static LaberonLSDZ2.constant.TeamConstants.*;
import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

//    private final TeamRepository repositoryMock = mock(TeamRepository.class);
    @Mock
    private TeamRepository repositoryMock;

    @InjectMocks
    private TeamServiceImpl out;

    @Test
    public void shouldCallRepositoryMethodWhenAddRemoveAndFindElement() {
        when(repositoryMock.add(TEAM_NAME_1))
                .thenReturn(TEAM_NAME_1);
        when(repositoryMock.find(TEAM_NAME_1))
                .thenReturn(TEAM_NAME_1);
        when(repositoryMock.remove(TEAM_NAME_1))
                .thenReturn(TEAM_NAME_1);

        assertEquals(TEAM_NAME_1, out.add(TEAM_NAME_1));
        assertEquals(TEAM_NAME_1, out.find(TEAM_NAME_1));
        assertEquals(TEAM_NAME_1, out.remove(TEAM_NAME_1));

        verify(repositoryMock, times(1)).add(TEAM_NAME_1);
        verify(repositoryMock, times(1)).find(TEAM_NAME_1);
        verify(repositoryMock, times(1)).remove(TEAM_NAME_1);
    }

    @Test
    public void shouldThrowExceptionWhenRepositoryThrowsExceptions() {
        when(repositoryMock.add(any()))
                .thenThrow(TeamExistsException.class);
        when(repositoryMock.remove(any()))
                .thenThrow(TeamNotExistException.class);
        when(repositoryMock.find(any()))
                .thenThrow(TeamNotExistException.class);

        assertThrows(TeamExistsException.class, () -> out.add(TEAM_NAME_1));
        assertThrows(TeamNotExistException.class, () -> out.find(TEAM_NAME_1));
        assertThrows(TeamNotExistException.class, () -> out.remove(TEAM_NAME_1));
    }

    @Test
    public void shouldReturnCollectionOfTeamsWhenFindAllTeamsCalled() {
        when(repositoryMock.findAll())
                .thenReturn(TEAM_LIST);

        assertIterableEquals(TEAM_LIST, out.findTeams());
    }

    @Test
    public void shouldReturnCorrectStringOfTeams() {
        when(repositoryMock.findAll())
                .thenReturn(TEAM_LIST);

        String result = String.join(", ", TEAM_LIST);
        assertEquals(result, out.findTeamsAsString());

        verify(repositoryMock, only()).findAll();
    }

    @Test
    public void shouldReturnCollectionOfTeamsWhichStartsWithPrefix() {
        when(repositoryMock.findAll())
                .thenReturn(TEAM_LIST);

        assertEquals(TEAM_LIST, out.findTeamsByPrefix(GOOD_PREFIX));
        assertEquals(emptyList(), out.findTeamsByPrefix(BAD_PREFIX));
    }

    @Test
    public void shouldReturnEmptyListWhenTeamsDontExist() {
        when(repositoryMock.findAll())
                .thenReturn(emptyList());

        assertTrue(out.findTeams().isEmpty());
    }
    @Test
    public void shouldReturnEmptyStringWhenTeamsDontExist() {
        when(repositoryMock.findAll())
                .thenReturn(emptyList());

        assertEquals("", out.findTeamsAsString());
    }
}
