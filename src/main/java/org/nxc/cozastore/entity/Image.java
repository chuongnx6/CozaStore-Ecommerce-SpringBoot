package org.nxc.cozastore.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.nxc.cozastore.util.DatabaseUtil;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(schema = DatabaseUtil.SCHEMA, name = "image")
public class Image implements Serializable {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(name = "id", length = 36)
    private String id;

    @EqualsAndHashCode.Include
    @Column(name = "name", nullable = false, length = 64)
    private String name;

    @EqualsAndHashCode.Include
    @Column(name = "url", nullable = false, unique = true)
    private String url;

    @ToString.Exclude
    @OneToMany(mappedBy = "image")
    private List<ProductImage> productImageList;
}
