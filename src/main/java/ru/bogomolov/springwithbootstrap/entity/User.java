package ru.bogomolov.springwithbootstrap.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.UniqueElements;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Table(name = "users")
@Setter
@Getter
@ToString
@RequiredArgsConstructor
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Некорректный формат имени: проверьте введенные данные")
    private String name;

    @Column(name = "lastName")
    @NotEmpty(message = "Некорректный формат фамилии: проверьте введенные данные")
    private String lastName;

    @Column(name = "age")
    @NotNull(message = "Некорректный формат возраста: возраст не указан")
    @Min(value = 0, message = "Некорректный формат возраста: возраст не может быть меньше 0")
    private byte age;

    @Column(name = "username", unique = true)
    @NotEmpty(message = "Некорректный формат логина: проверьте введённые данные")
    private String username;

    @Column(name = "password")
    @NotEmpty(message = "Некорректный формат пароля: проверьте введённые данные")
    private String password;

    @Fetch(FetchMode.JOIN)
    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @NotEmpty(message = "Роли не выбраны")
    private Collection<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public String getShortRole() {
        return roles.toString().substring(1, roles.toString().length() - 1);
    }
}
