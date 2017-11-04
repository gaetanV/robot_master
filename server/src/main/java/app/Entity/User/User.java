package app.Entity.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.ManyToMany;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.validation.constraints.NotNull;
import java.util.List;

import app.Entity.Role.Role;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @NotNull
    private String username;
    
    @NotNull
    private String email;
    
    @NotNull
    private String password;

    private boolean enabled;
    
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
        name = "user_role", 
        joinColumns = {
            @JoinColumn(name = "user_id")
        }, 
        inverseJoinColumns = {
            @JoinColumn(name = "role_id")
        }
    )
    private List<Role> roles;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean getEnabled() {
        return enabled;
    }

    public void setId(boolean enabled) {
        this.enabled = enabled;
    }
 
    public List<Role> getRoles() {
        return this.roles;
    }
    
    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

}
