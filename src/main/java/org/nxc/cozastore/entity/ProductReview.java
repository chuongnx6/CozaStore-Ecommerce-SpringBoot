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
@ToString(onlyExplicitlyIncluded = true)
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "product_review")
public class ProductReview implements Serializable {
    @ToString.Include
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private ProductReviewId id;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ToString.Include
    @Column(name = "rate", nullable = false)
    private Integer rate;

    @ToString.Include
    @Column(name = "title", nullable = false, length = 64)
    private String title;

    @ToString.Include
    @Column(name = "review", nullable = false)
    private String review;

    @ToString.Include
    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    public ProductReview(ProductReviewId id, Product product, User user, Integer rate, String title, String review, LocalDateTime createDate) {
        this.id = new ProductReviewId(user.getId(), product.getId());
        setProduct(product);
        setUser(user);
        this.rate = rate;
        this.title = title;
        this.review = review;
        this.createDate = createDate;
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
