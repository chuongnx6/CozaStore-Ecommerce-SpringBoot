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
public class OrderLineId implements Serializable {
    private Integer orderId;
    private Integer productId;
}
