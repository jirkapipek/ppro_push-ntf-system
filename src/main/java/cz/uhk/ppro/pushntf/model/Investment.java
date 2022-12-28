package cz.uhk.ppro.pushntf.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.*;
import java.time.LocalDateTime;


@Entity
@Table(schema = "ppro", name = "investment")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Investment {

    @Id
    @Column(name = "investment_id", unique = true)
    @Schema(type = "string", example = "5567d933-5d2a-46e3-a58e-34d614e9bdea")
    private String investmentId;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    @Schema(example = "CRYPTOCURRENCIES")
    private Category category;

    @Column(name = "type")
    @Schema(type = "string", example = "Bitcoin")
    private String type;

    @Column(name = "buyPrice")
    @Schema(type = "double", example = "15642.06")
    private Double buyPrice;

    @Column(name = "buyCurrency", length = 3)
    @Schema(type = "string", example = "EUR")
    private String buyCurrency;

    @Column(name = "sellPrice")
    @Schema(type = "double", example = "379177.83")
    private Double sellPrice;

    @Column(name = "sellCurrency", length = 3)
    @Schema(type = "string", example = "CZK")
    private String sellCurrency;

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    @Hidden
    private LocalDateTime createdOn;

    @Column(name = "updated", nullable = false)
    @UpdateTimestamp
    @Hidden
    private LocalDateTime updatedOn;

    public enum Category {
        BONDS, MUTUAL_FUNDS, STOCKS, CRYPTOCURRENCIES
    }
}
