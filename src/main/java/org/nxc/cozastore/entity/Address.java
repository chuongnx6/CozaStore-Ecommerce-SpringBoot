package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "address")
public class Address implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @EqualsAndHashCode.Include
    @Column(name = "country", nullable = false, length = 32)
    private String country;

    @EqualsAndHashCode.Include
    @Column(name = "city", nullable = false, length = 32)
    private String city;

    @EqualsAndHashCode.Include
    @Column(name = "distric", nullable = false, length = 32)
    private String distric;

    @EqualsAndHashCode.Include
    @Column(name = "stress", nullable = false, length = 32)
    private String stress;

    @Column(name = "address_detail", nullable = false)
    private String addressDetail;

    @ToString.Exclude
    @OneToOne(mappedBy = "address", fetch = FetchType.LAZY)
    private User user;
}
