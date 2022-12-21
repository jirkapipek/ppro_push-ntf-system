package cz.uhk.ppro.pushntf.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(schema = "ppro", name = "party")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class Party {

    @Id
    @Column(name = "party_id", unique=true)
    @Schema( type = "string", example = "5567d933-5d2a-46e3-a58e-34d614e9bdec")
    private String partyId;

    @Column(name = "ico", length = 8)
    @Schema(type = "string", example = "60916427")
    private String ico;

    @Column(name = "firstname")
    @Schema(type = "string", example = "John")
    private String firstName;

    @Column(name = "lastname")
    @Schema(type = "string", example = "Doe")
    private String lastName;

    @Column(name = "reg_status")
    @Enumerated(EnumType.ORDINAL)
    private Status regStatus;

    @Column(name = "created")
    @CreationTimestamp
    @Hidden
    private LocalDateTime createdOn;

    public enum Status {
        REGISTERED, UNREGISTERED, DELETED
    }
}
