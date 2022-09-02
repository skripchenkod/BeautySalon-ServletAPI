package ua.skrypchenko.beautysalon.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class User implements Serializable {

  private Integer id;
  private String username;
  private String password;
  private Role role;
  private List<Reservation> reservations;
  private String email;

  public User(Integer id, String username, String password, Role role, List<Reservation> reservations) {
    this.id = id;
    this.username = username;
    this.password = password;
    this.role = role;
    this.reservations = reservations;
  }

  public User() {

  }

  public User(String username) {
    this.username = username;
  }

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  public User(String username, String password, Role role, String email) {
    this.username = username;
    this.password = password;
    this.role = role;
    this.email = email;
  }

  public User(String username, String password, Role role) {
    this.username = username;
    this.password = password;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String eMail) {
    this.email = eMail;
  }

  public Integer getId() {
    return id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public Role getRole() {
    return role;
  }

  public void setRole(Role role) {
    this.role = role;
  }

  public List<Reservation> getReservations() {
    return reservations;
  }

  public void setReservations(List<Reservation> reservations) {
    this.reservations = reservations;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof User)) return false;
    User user = (User) o;
    return Objects.equals(username, user.username)
        && Objects.equals(password, user.password)
        && role == user.role
        && Objects.equals(reservations, user.reservations);
  }

  @Override
  public int hashCode() {
    return Objects.hash( username, password, role, reservations);
  }



  @Override
  public String toString() {
    return "User{"
        + "id="
        + id
        + ", username='"
        + username
        + '\''
        + ", password='"
        + password
        + '\''
        + ", role="
        + role
        + ", reservations="
        + reservations
        + '}';
  }

}
