package io.auth.api.domain.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Collections;
import java.util.Set;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id")
@Builder @NoArgsConstructor @AllArgsConstructor
public class PartnerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long partnerNo;

    @Column(unique = true)
    @NotBlank
    private String id;

    @JsonIgnore
    @NotBlank
    private String password;

    @NotBlank
    private String partnerEmail;

    @NotBlank
    private String partnerCompanyName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<PartnerRole> roles;

    @Enumerated(EnumType.STRING)
    private PartnerStatus status;

    public void signUp(){
        this.roles = Collections.singleton(PartnerRole.STARTER);
        this.status = PartnerStatus.DRAFT;
    }
}
