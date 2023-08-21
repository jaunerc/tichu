package ch.jaunerc.tichu.backend.web.users;

import ch.jaunerc.tichu.backend.domain.user.port.CreateUserUseCase;
import ch.jaunerc.tichu.backend.web.api.controller.UsersApiDelegate;
import ch.jaunerc.tichu.backend.web.api.model.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsersWebAdapter implements UsersApiDelegate {

    private final CreateUserUseCase createUserUseCase;

    @Override
    public ResponseEntity<UserDto> createUser(String playerName) {
        return ResponseEntity.ok(UserDtoMapper.map(
                createUserUseCase.createUser(playerName)));
    }
}
