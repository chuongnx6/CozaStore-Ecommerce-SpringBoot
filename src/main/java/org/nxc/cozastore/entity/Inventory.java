package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "inventory")
public class Inventory implements Serializable {
    @Id
    @OneToOne
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttribute;

    @Column(name = "quantity_in_stock", nullable = false)
    private Integer quantityInStock;
}
