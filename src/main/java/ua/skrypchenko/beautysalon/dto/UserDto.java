package ua.skrypchenko.beautysalon.dto;

public class UserDto {
    private String userName;
    private String password;
    private String email;

    public UserDto(String email, String password) {
        this.email = email;
        this.password = password;
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
}
