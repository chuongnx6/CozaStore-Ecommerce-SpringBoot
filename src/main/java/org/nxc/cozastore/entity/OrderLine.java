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
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "order_line")
public class OrderLine implements Serializable {
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private OrderLineId id;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @MapsId("productAttributeId")
    @JoinColumn(name = "product_attribute_id")
    private ProductAttribute productAttribute;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "discount", nullable = false)
    private Double discount;

    public OrderLine(OrderLineId id, Order order, ProductAttribute productAttribute, Integer quantity, Double price, Double discount) {
        this.id = new OrderLineId(order.getId(), productAttribute.getId());
        setOrder(order);
        setProductAttribute(productAttribute);
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
    }

    public void setOrder(Order order) {
        id.setOrderId(order.getId());
        this.order = order;
    }

    public void setProductAttribute(ProductAttribute productAttribute) {
        id.setProductAttributeId(productAttribute.getId());
        this.productAttribute = productAttribute;
    }
}
