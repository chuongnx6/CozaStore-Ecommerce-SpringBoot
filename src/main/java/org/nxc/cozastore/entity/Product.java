package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "product")
public class Product implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @EqualsAndHashCode.Include
    @Column(name = "name", nullable = false, unique = true, length = 64)
    private String name;

    @EqualsAndHashCode.Include
    @Column(name = "sku", nullable = false, unique = true, length = 32)
    private String sku;

    @Column(name = "short_description", nullable = false)
    private String shortDescription;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<ProductAttribute> productAttributeList;

    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<ProductImage> productImageList;

    @ToString.Exclude
    @OneToMany(mappedBy = "product")
    private List<ProductReview> productReviewList;
}
