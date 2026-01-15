package com.example.sportsstore.domain.entity;

import com.example.sportsstore.domain.enums.Size;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "inventories",
    uniqueConstraints = {
        @UniqueConstraint(columnNames = {"product_id", "size"})
    }
)
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @Builder
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "product_id")
    private Product product;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Size size;

    @Column(nullable = false)
    private int availableQty;

    @Version
    private Long version;
}
