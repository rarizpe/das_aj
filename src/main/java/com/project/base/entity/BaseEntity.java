package com.project.base.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.utils.date.DateUtils;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Created by Elí.Arizpe on 01/04/2020.
 */
@MappedSuperclass
@Data
public abstract class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    private Boolean softDelete;

    @JsonIgnore
    private LocalDateTime createdAt;
    @JsonIgnore
    private LocalDateTime modifiedAt;
    @Column(unique = true)
    private String uuid;


    @PrePersist
    protected void setAuditInfo(){
        this.setCreatedAt(DateUtils.getCurrentDateTime());
        this.setSoftDelete(false);
        this.setModifiedAt(DateUtils.getCurrentDateTime());
        if(this.uuid == null || "".equals(this.uuid)){
            this.uuid = UUID.randomUUID().toString();
        }
    }

    @PreUpdate
    protected void setModifyInfo(){
        this.setModifiedAt(DateUtils.getCurrentDateTime());
    }

}
