package org.tuxdevelop.spring_boot_cxf_demo.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class AbstractDomainEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "inserted_at")
    private Date insertedAt;
    @Column(name = "inserted_by")
    private String insertedBy;
    @Column(name = "changed_at")
    private Date changedAt;
    @Column(name = "changed_by")
    private String changedBy;
    @Column(name = "valid")
    private Integer valid;

    protected void merge(final AbstractDomainEntity entity) {
        changedAt = entity.getChangedAt();
        changedBy = entity.getChangedBy();
        valid = entity.getValid();
    }

    protected void validateAdd() {
        assert (insertedBy != null);
        assert (insertedAt != null);
        assert (valid != null);
    }

    protected void validateUpdate() {
        assert (id != null);
        assert (changedBy != null);
        assert (changedAt != null);
        validateAdd();
    }
}
