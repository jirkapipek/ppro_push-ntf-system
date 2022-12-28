package cz.uhk.ppro.pushntf.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(schema = "ppro", name = "product")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Product {

    @Id
    @Column(name = "product_id", unique=true)
    @Schema( type = "string", example = "5567d933-5d2a-46e3-a58e-34d614e9bded")
    private String productId;

    @Column(name = "product_code", length = 9)
    @Schema(type = "string", example = "609164271")
    private String productCode;

    @Column(name = "product_name")
    @Schema(type = "string", example = "Standard Account")
    private String productName;

    @Column(name = "price")
    @Schema(type = "double", example = "122.24")
    private Double price;

    @Column(name = "currency", length = 3)
    @Schema(type = "string", example = "CZK")
    private String currency;

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    @Hidden
    private LocalDateTime createdOn;

}
