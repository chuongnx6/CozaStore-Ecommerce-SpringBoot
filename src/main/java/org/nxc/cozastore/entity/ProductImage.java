package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.nxc.cozastore.entity.primaryKey.ProductImageId;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
//@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "product_image")
public class ProductImage implements Serializable {
    @EqualsAndHashCode.Include
    @EmbeddedId
    @Setter(AccessLevel.NONE)
    private ProductImageId id;

    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @MapsId("imageId")
    @JoinColumn(name = "image_id")
    private Image image;

    public ProductImage(ProductImageId id, Product product, Image image) {
        this.id = new ProductImageId(product.getId(), image.getId());
        setProduct(product);
        setImage(image);
    }

    public void setProduct(Product product) {
        id.setProductId(product.getId());
        this.product = product;
    }

    public void setImage(Image image) {
        id.setImageId(image.getId());
        this.image = image;
    }
}
