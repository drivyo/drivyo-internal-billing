package Drivyo.sharedData.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@Table(name = "company")
@NoArgsConstructor
@AllArgsConstructor
public class CompanyEntity {
    @Id
    @SequenceGenerator(name = "company_pk_seq", sequenceName = "company_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "company_pk_seq")
    private Long id;
    @Column(unique = true)
    private String name;
    private String cif;
    private Boolean enabled;
}
