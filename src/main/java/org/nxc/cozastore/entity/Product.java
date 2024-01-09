package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.util.DatabaseUtil;
import org.springframework.data.annotation.CreatedDate;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "product")
public class Product implements Serializable {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Include
    @EqualsAndHashCode.Include
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @EqualsAndHashCode.Include
    @Column(name = "alias")
    private String alias;

    @ToString.Include
    @Column(name = "price", nullable = false)
    private Double price;

    @ToString.Include
    @Column(name = "main_image", nullable = false)
    private String mainImage;

    @ToString.Include
    @Column(name = "short_description")
    private String shortDescription;

    @ToString.Include
    @Column(name = "description")
    private String description;

    @ToString.Include
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @ToString.Include
    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product")
    private Set<Cart> cartSet;

    @OneToMany(mappedBy = "product")
    private Set<OrderLine> orderLineSet;

    @OneToMany(mappedBy = "product")
    private Set<ProductReview> productReviewSet;
}
