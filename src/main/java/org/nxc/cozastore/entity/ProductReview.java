package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.entity.primaryKey.ProductReviewId;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "product_review")
public class ProductReview implements Serializable {
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private ProductReviewId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "rate", nullable = false)
    private Integer rate;

    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @Column(name = "review", nullable = false)
    private String review;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    public ProductReview(ProductReviewId id, Product product, User user, Integer rate, String title, String review, LocalDateTime createdAt) {
        this.id = new ProductReviewId(product.getId(), user.getId());
        setProduct(product);
        setUser(user);
        this.rate = rate;
        this.title = title;
        this.review = review;
        this.createdAt = createdAt;
    }

    public void setProduct(Product product) {
        id.setProductId(product.getId());
        this.product = product;
    }

    public void setUser(User user) {
        id.setUserId(user.getId());
        this.user = user;
    }
}
