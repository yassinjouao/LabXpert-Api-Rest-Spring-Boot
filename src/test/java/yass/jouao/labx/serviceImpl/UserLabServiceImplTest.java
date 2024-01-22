package yass.jouao.labx.serviceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import yass.jouao.labx.DTOs.UserLabDTO;
import yass.jouao.labx.entities.UserLab;
import yass.jouao.labx.exeptions.NotFoundException;
import yass.jouao.labx.repositories.IUserLabRepository;
import yass.jouao.labx.serviceImpl.Mappers.UserMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserLabServiceImplTest {
    private UserMapper userMapper;
    @Mock
    private IUserLabRepository userRepository;
    @InjectMocks
    private UserLabServiceImpl userService;

    @BeforeEach
    public void setup() {
        userMapper = new UserMapper();
        userRepository = Mockito.mock(IUserLabRepository.class);
        userService = new UserLabServiceImpl(userMapper, userRepository);
    }
    @Test
    void testGetUserLabByIdService() throws NotFoundException {
        UserLab userLab = UserLab.builder().id(1L).name("UserLabName").build();
        UserLabDTO userLabDTO = UserLabDTO.builder().id(1L).name("UserLabName").build();
        Optional<UserLab> optionalUserLab = Optional.of(userLab);

        when(userRepository.findById(1L)).thenReturn(optionalUserLab);

        UserLabDTO result = userService.getUserLabByIdService(1L);

        assertEquals(userLabDTO, result);
        verify(userRepository, times(1)).findById(1L);
    }
}