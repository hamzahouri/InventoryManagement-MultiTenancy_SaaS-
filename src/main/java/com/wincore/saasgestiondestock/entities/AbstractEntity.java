package com.wincore.saasgestiondestock.entities;

import com.wincore.saasgestiondestock.config.TenantContext;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
@MappedSuperclass
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@EntityListeners(AuditingEntityListener.class)
@FilterDef(name = "tenantFilter", parameters = @ParamDef(name = "tenantId", type = String.class),
        defaultCondition = "tenant_id = :tenantId")
@Filter(name = "tenantFilter")
public class AbstractEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "tenant_id", nullable = false)
    private String tenantId;

    @CreatedDate
    @Column(name = "created_At", updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_At", insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "created_By", nullable = false, updatable = false)
    private String createdBy;

    @Column(name = "updated_By", insertable = false)
    private LocalDateTime updatedBy;

    @Column(name = "deleted", nullable = false)
    private Boolean isDeleted;

    @PrePersist
    protected void onCreate () {
        if(this.isDeleted == null) {
            this.isDeleted = Boolean.FALSE;
        }
  // toDo : to be deleted after security implemented
        if(this.createdBy == null) {
            this.createdBy = "SYSTEM";
        }

        if(this.tenantId == null) {
            this.tenantId = TenantContext.getCurrentTenant();
        }
    }
}
