package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "product_attribute")
public class ProductAttribute implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @EqualsAndHashCode.Include
    @Column(name = "price", nullable = false)
    private Double price;

    @EqualsAndHashCode.Include
    @Column(name = "discount_percent", nullable = false)
    private Double discountPercent;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Size size;

    @ManyToOne
    @JoinColumn(name = "color_id")
    private Color color;

    @ToString.Exclude
    @OneToOne(mappedBy = "productAttribute", fetch = FetchType.LAZY)
    private Inventory inventory;

    @ToString.Exclude
    @OneToMany(mappedBy = "productAttribute")
    private List<Cart> cartList;

    @ToString.Exclude
    @OneToMany(mappedBy = "productAttribute")
    private List<OrderLine> orderLineList;
}
