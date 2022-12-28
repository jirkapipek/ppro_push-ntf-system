package cz.uhk.ppro.pushntf.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;


@Entity
@Table(schema = "ppro", name = "document")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@DynamicInsert
@DynamicUpdate
public class Document {

    @Id
    @Column(name = "document_id", unique = true)
    @Schema(type = "string", example = "5567d933-5d2a-46e3-a58e-34d614e9bdeb")
    private String documentId;

    @Column(name = "formatCode")
    @Schema(type = "string", example = "pdf")
    private String format;

    @Column(name = "description")
    @Schema(type = "string", example = "There is statement from last month")
    private String description;

    @Column(name = "filename")
    @Schema(type = "string", example = "statement20221228_jpipek")
    private String filename;

    @Column(name = "document_language", length = 3)
    @Schema(type = "string", example = "CZE")
    private String documentLanguage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "party_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Party owner;

    @Column(name = "created", nullable = false, updatable = false)
    @CreationTimestamp
    @Hidden
    private LocalDateTime createdOn;

}
