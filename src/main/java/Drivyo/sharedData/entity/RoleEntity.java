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
@Table(name = "role")
@NoArgsConstructor
@AllArgsConstructor
public class RoleEntity {
    @Id
    @SequenceGenerator(name = "role_pk_sequence", sequenceName = "role_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "role_pk_sequence")
    private Long id;
    private String name;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_authority",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "authority_id"))
    private List<AuthorityEntity> authorities;

    public RoleEntity(Long id) {
        this.id = id;
    }
}