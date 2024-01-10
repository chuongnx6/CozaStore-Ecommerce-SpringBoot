package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.entity.enumeration.Gender;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "user")
public class User implements Serializable {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Include
    @Column(name = "fist_name", nullable = false, length = 32)
    private String firstName;

    @ToString.Include
    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "email", nullable = false, unique = true, length = 128)
    private String email;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "phone", nullable = false, unique = true, length = 16)
    private String phone;

    @ToString.Include
    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @ToString.Include
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ToString.Include
    @Column(name = "avatar")
    private String avatar;

    @ToString.Include
    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @ToString.Include
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoleSet;

    @OneToMany(mappedBy = "user")
    private Set<Cart> cartSet;

    @OneToMany(mappedBy = "user")
    private Set<ProductReview> productReviewSet;

    @OneToOne(mappedBy = "user")
    private Order order;
}
