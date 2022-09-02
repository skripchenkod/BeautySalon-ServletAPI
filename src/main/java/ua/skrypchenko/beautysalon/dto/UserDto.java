package ua.skrypchenko.beautysalon.dto;

import ua.skrypchenko.beautysalon.entity.Role;

import java.util.Objects;

public class UserDto {
    private String userName;
    private String password;
    private String email;
    private Role role;

    public UserDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public UserDto(String userName, String password, String email, Role role) {
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserDto(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public UserDto(String userName) {
        this.userName = userName;
    }

    public UserDto() {
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(userName, userDto.userName) && Objects.equals(password, userDto.password) && Objects.equals(email, userDto.email) && role == userDto.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName, password, email, role);
    }
}


