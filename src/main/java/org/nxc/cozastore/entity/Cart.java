package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.entity.primaryKey.CartId;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "cart")
public class Cart implements Serializable {
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private CartId id;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("productAttributeId")
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttribute;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    public Cart(CartId id, User user, ProductAttribute productAttribute, Integer quantity, Double price) {
        this.id = new CartId(user.getId(), productAttribute.getId());
        setUser(user);
        setProductAttribute(productAttribute);
        this.quantity = quantity;
        this.price = price;
    }

    public void setUser(User user) {
        id.setUserId(user.getId());
        this.user = user;
    }

    public void setProductAttribute(ProductAttribute productAttribute) {
        id.setProductAttributeId(productAttribute.getId());
        this.productAttribute = productAttribute;
    }
}
