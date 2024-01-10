package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.util.DatabaseUtil;

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
@Table(schema = DatabaseUtil.SCHEMA, name = "order")
public class Order implements Serializable {
    @ToString.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ToString.Include
    @Column(name = "shipping_cost", nullable = false)
    private Double shippingCost;

    @ToString.Include
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ToString.Include
    @EqualsAndHashCode.Include
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @ToString.Include
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "payment_id", nullable = false)
    private Payment payment;


    @OneToMany(mappedBy = "order")
    private Set<OrderLine> orderLineSet;
}
