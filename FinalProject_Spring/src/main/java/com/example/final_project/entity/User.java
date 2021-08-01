package com.example.final_project.entity;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.Set;


//@Data
//-----
@Getter
@Setter
//@Builder
@ToString
//---
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")

public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long idUser;
    //   private List<Role> authorities;
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;
    @Column(nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Transient
    private String passwordConfirm;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles();
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

//    private boolean accountNonExpired;
//    private boolean accountNonLocked;
//    public boolean credentialsNonExpired;
//    public boolean enabled;
}
