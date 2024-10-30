package com.medecineWebApp.Configuration.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medecineWebApp.Configuration.models.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "config_groupe")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Groupe extends Auditable implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String nom;
    private String description;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Departement departement;

    /*@OneToMany(mappedBy = "group")
    private List<Service> services = new ArrayList<>();*/


    // Un groupe peut contenir plusieurs utilisateurs
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "group_id")
    private Set<User> users;






  /*  @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "groupe_permission",
            joinColumns = {@JoinColumn(name = "groupe_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_name",referencedColumnName = "name")}
    )
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @BatchSize(size = 100)
    private List<Permission> permissions;*/

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Groupe parentGroupe;


    public String toString(){
        return "Groupe [id=" + id + ", nom=" + nom + ", description=" + description + "]";
    }

}
