package evgesha.blps.lab1.entity;


import evgesha.blps.lab1.enums.Role;
import evgesha.blps.lab1.xml.DateAdapter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@XmlRootElement(name = "user")
@XmlType(propOrder = {"id", "username", "password", "role", "createdAt", "enabled"})
public class User implements UserDetails {
    private Integer id;
    @XmlAttribute
    public void setId(Integer id) {
        this.id = id;
    }
    private String username;

    @XmlAttribute
    public void setUsername(String username) {
        this.username = username;
    }
    private String password;

    @XmlAttribute
    public void setPassword(String password) {
        this.password = password;
    }

    @Enumerated(EnumType.STRING)
    private Role role;

    @XmlAttribute
    public void setRole(Role role) {
        this.role = role;
    }
    @CreatedDate
    private Date createdAt;

    @XmlJavaTypeAdapter(DateAdapter.class)
    @XmlAttribute
    public void setCreatedAt(Date date) {
        this.createdAt = date;
    }
    private boolean enabled = true;

    @XmlAttribute
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.enabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return enabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return enabled;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}