package estudos.agregadoInvestimentos.service.impl;

import estudos.agregadoInvestimentos.dto.request.CreateUserDto;
import estudos.agregadoInvestimentos.entity.UserEntity;
import estudos.agregadoInvestimentos.repository.UserRepository;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Captor
    private ArgumentCaptor<UserEntity> userArgumentCaptor;
    @Captor
    private ArgumentCaptor<UUID> uuidArgumentCaptor;

    @Nested
    class createUser{
        @Test
        void shouldCreateUser() {

            UserEntity userEntity = new UserEntity(
                    UUID.randomUUID(),
                    "name",
                    "email",
                    "123",
                    Instant.now(),
                    null
            );

            doReturn(userEntity).when(userRepository).save(userArgumentCaptor.capture());

            var userDto = new CreateUserDto(
                    "name",
                    "email",
                    "123"
            );

          var output = userService.createUser(userDto);

          assertNotNull(output);

          var userCapture = userArgumentCaptor.getValue();

          assertEquals(userDto.name(),userCapture.getUsername());

          assertEquals(userDto.email(),userCapture.getEmail());

          assertEquals(userDto.password(),userCapture.getPassword());
        }

        @Test
        void shouldThrowExceptionWhenErrorOccurs() {

            doThrow(new RuntimeException()).when(userRepository).save(any());
            var userDto = new CreateUserDto(
                    "name",
                    "email",
                    "123");

            assertThrows(RuntimeException.class, () -> userService.createUser(userDto));
        }
    }
    @Nested
    class getUserById{
        @Test
        void shouldGetUserByIdWhenOptionalIsPresent() {
            UserEntity userEntity = new UserEntity(
                    UUID.randomUUID(),
                    "name",
                    "email",
                    "123",
                    Instant.now(),
                    null
            );
            doReturn(Optional.of(userEntity)).when(userRepository).findById(uuidArgumentCaptor.capture());

           var output = userService.getUserById(userEntity.getUserId().toString());

            assertTrue(output.isPresent());
            assertEquals(userEntity.getUserId(), uuidArgumentCaptor.getValue());
        }

        @Test
        void shouldGetUserByIdWhenOptionalIsEmpty() {
            var userEntity = UUID.randomUUID();
            doReturn(Optional.of(userEntity)).when(userRepository).findById(uuidArgumentCaptor.capture());

            var output = userService.getUserById(userEntity.toString());

            assertTrue(output.isEmpty());
            assertEquals(userEntity, uuidArgumentCaptor.getValue());
        }
    }

}