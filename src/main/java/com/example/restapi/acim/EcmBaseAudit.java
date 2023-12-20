package com.example.restapi.acim;

import java.util.Date;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;

@EntityListeners(AuditingEntityListener.class)
public class EcmBaseAudit {
    @CreatedBy
    @Column(nullable = false)
    String createdBy;
    @CreatedDate()
    @Column(updatable = false, nullable = false)
    Date createdDate;
    @LastModifiedBy
    String lastModifiedBy;
    @LastModifiedDate
    Date lastModifiedDate;

    public EcmBaseAudit(){}

    public EcmBaseAudit(String createdBy, Date createdDate, String lastModifyedBy, Date lastModifiedDate){
        this.createdBy = createdBy;
        this.createdDate = createdDate;
        this.lastModifiedBy = lastModifyedBy;
        this.lastModifiedDate = lastModifiedDate;
    }
}
