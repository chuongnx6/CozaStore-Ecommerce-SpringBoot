package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.entity.primaryKey.OrderLineId;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "order_line")
public class OrderLine implements Serializable {
    @ToString.Include
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private OrderLineId id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

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

    @ToString.Include
    @Column(name = "discount", nullable = false)
    private Double discount;

    public OrderLine(OrderLineId id, Order order, Product product, Integer quantity, Double price, Double discount) {
        this.id = new OrderLineId(order.getId(), product.getId());
        setOrder(order);
        setProduct(product);
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public void setOrder(Order order) {
        id.setOrderId(order.getId());
        this.order = order;
    }

    public void setProduct(Product product) {
        id.setProductId(product.getId());
        this.product = product;
    }
}
