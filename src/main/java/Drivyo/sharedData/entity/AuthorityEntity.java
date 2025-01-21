package Drivyo.sharedData.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@Builder
@Table(name = "authority")
@NoArgsConstructor
@AllArgsConstructor
public class AuthorityEntity {
    @Id
    @SequenceGenerator(name = "authority_pk_seq", sequenceName = "authority_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_pk_seq")
    private Long id;
    private String name;
    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<RoleEntity> roles;
}

