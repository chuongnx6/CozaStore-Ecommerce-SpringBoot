package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.entity.primaryKey.UserRoleId;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "user_role")
public class UserRole implements Serializable {
    @ToString.Include
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private UserRoleId id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("roleId")
    @JoinColumn(name = "role_id")
    private Role role;

    @ToString.Include
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public UserRole(UserRoleId id, User user, Role role, LocalDateTime createdAt) {
        this.id = new UserRoleId(user.getId(), role.getId());
        setUser(user);
        setRole(role);
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        id.setUserId(user.getId());
        this.user = user;
    }

    public void setRole(Role role) {
        id.setRoleId(role.getId());
        this.role = role;
    }
}
