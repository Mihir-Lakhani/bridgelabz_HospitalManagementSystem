package com.example.HospitalManagementSystem;

import com.example.HospitalManagementSystem.dto.UserRegistrationRequestDto;
import com.example.HospitalManagementSystem.dto.mapping.UserRegistrationRequestDtoToUser;
import com.example.HospitalManagementSystem.dto.mapping.UserToUserRegistrationResponseDto;
import com.example.HospitalManagementSystem.entity.CustomUserDetails;
import com.example.HospitalManagementSystem.entity.Role;
import com.example.HospitalManagementSystem.entity.User;
import com.example.HospitalManagementSystem.enums.RoleType;
import com.example.HospitalManagementSystem.repository.RoleRepository;
import com.example.HospitalManagementSystem.repository.UserRepository;
import com.example.HospitalManagementSystem.service.AuthService;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserRolesTest {

    @Test
    void principalExposesEveryRoleAndTheCorrectCredentials() {
        User user = new User();
        user.setUsername("alice");
        user.setPassword("stored-password-hash");
        user.getRoles().add(new Role(1L, RoleType.USER));
        user.getRoles().add(new Role(2L, RoleType.ADMIN));

        CustomUserDetails principal = new CustomUserDetails(user);

        assertThat(principal.getAuthorities()).extracting(GrantedAuthority::getAuthority)
                .containsExactlyInAnyOrder("ROLE_USER", "ROLE_ADMIN");
        assertThat(principal.getUsername()).isEqualTo("alice");
        assertThat(principal.getPassword()).isEqualTo("stored-password-hash");
    }

    @Test
    void responseIncludesAllRolesWithoutSharingTheMutableCollection() {
        User user = new User();
        Role standard = new Role(1L, RoleType.USER);
        Role admin = new Role(2L, RoleType.ADMIN);
        user.getRoles().add(standard);
        user.getRoles().add(admin);

        var response = new UserToUserRegistrationResponseDto().mapToResponseDto(user);

        assertThat(response.getRoles()).containsExactlyInAnyOrder(standard, admin);
        response.getRoles().clear();
        assertThat(user.getRoles()).hasSize(2);
    }

    @Test
    void registrationStoresEncodedPasswordAndOnlyTheExistingUserRole() {
        UserRepository users = mock(UserRepository.class);
        RoleRepository roles = mock(RoleRepository.class);
        PasswordEncoder encoder = mock(PasswordEncoder.class);
        Role standard = new Role(1L, RoleType.USER);
        when(roles.findByName(RoleType.USER)).thenReturn(Optional.of(standard));
        when(encoder.encode("a-long-test-password")).thenReturn("encoded-password");
        when(users.save(any(User.class))).thenAnswer(invocation -> invocation.getArgument(0));
        AuthService service = new AuthService(users, roles, encoder,
                new UserRegistrationRequestDtoToUser(), new UserToUserRegistrationResponseDto());
        UserRegistrationRequestDto request = new UserRegistrationRequestDto();
        request.setUsername("alice");
        request.setEmail("alice@example.com");
        request.setPassword("a-long-test-password");

        var response = service.register(request);

        ArgumentCaptor<User> saved = ArgumentCaptor.forClass(User.class);
        verify(users).save(saved.capture());
        assertThat(saved.getValue().getRoles()).containsExactly(standard);
        assertThat(saved.getValue().getPassword()).isEqualTo("encoded-password");
        assertThat(response.getRoles()).containsExactly(standard);
        verify(roles, never()).save(any(Role.class));
    }
}
