package com.example.final_project.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.Set;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table
public class Role implements GrantedAuthority {
  @Id
  private Long idRole;
  private String nameRole;
    @Transient
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @Override
    public String getAuthority() {
        return getNameRole();
    }

  public Role(Long idRole, String nameRole) {
    this.idRole = idRole;
    this.nameRole = nameRole;
  }
}
