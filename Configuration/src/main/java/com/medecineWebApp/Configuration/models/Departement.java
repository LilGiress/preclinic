package com.medecineWebApp.Configuration.models;

import com.medecineWebApp.Configuration.models.role.Roles;
import jakarta.persistence.*;
import lombok.*;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "config_departments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EntityListeners(AuditingEntityListener.class)
public class Departement extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;
    private boolean active;

   /* @JsonIgnore
    @ManyToMany(mappedBy = "departments",cascade = CascadeType.DETACH,targetEntity = User.class)
    private List<User> users;*/

    // Un département peut contenir plusieurs rôles
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Set<Roles> roles;


}
