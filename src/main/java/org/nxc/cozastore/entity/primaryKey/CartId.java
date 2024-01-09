package org.nxc.cozastore.entity.primaryKey;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CartId implements Serializable {
    private Integer userId;
    private Integer productId;
}
