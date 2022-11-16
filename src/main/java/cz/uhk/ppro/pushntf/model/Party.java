package cz.uhk.ppro.pushntf.model;

import lombok.*;
import javax.persistence.*;
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
    @SequenceGenerator(name = "partyEntitySeq", sequenceName = "SEQ_PARTIES", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "party")
    @Column(name = "id")
    private Long id;

    @Column(name = "party_id")
    private UUID partyId;

    @Column(name = "ico", length = 8)
    private String ico;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "reg_status")
    @Enumerated(EnumType.ORDINAL)
    private Status regStatus;

    public enum Status {
        REGISTERED, UNREGISTERED, DELETED
    }
}
