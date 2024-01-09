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
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "cart")
public class Cart implements Serializable {
    @ToString.Include
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private CartId id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ToString.Include
    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ToString.Include
    @Column(name = "price", nullable = false)
    private Double price;

    public Cart(CartId id, User user, Product product, Integer quantity, Double price) {
        this.id = new CartId(user.getId(), product.getId());
        setUser(user);
        setProduct(product);
        this.quantity = quantity;
        this.price = price;
    }

    public void setUser(User user) {
        id.setUserId(user.getId());
        this.user = user;
    }

    public void setProduct(Product product) {
        id.setProductId(product.getId());
        this.product = product;
    }
}
