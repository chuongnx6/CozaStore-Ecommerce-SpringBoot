package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.nxc.cozastore.entity.enumeration.Gender;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "user")
public class User implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @Column(name = "fist_name", nullable = false, length = 32)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 32)
    private String lastName;

    @EqualsAndHashCode.Include
    @Column(name = "email", nullable = false, unique = true, length = 128)
    private String email;

    @EqualsAndHashCode.Include
    @Column(name = "phone", nullable = false, unique = true, length = 16)
    private String phone;

    @Column(name = "password", nullable = false, length = 64)
    private String password;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "avatar")
    private String avatar;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;

    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<UserRole> userRoleList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<Cart> cartList;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    private List<ProductReview> productReviewList;

    @ToString.Exclude
    @OneToOne(mappedBy = "user")
    private Order order;
}
