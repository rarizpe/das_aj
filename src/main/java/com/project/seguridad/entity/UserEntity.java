package com.project.seguridad.entity;

import com.project.base.entity.BaseEntity;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.Where;
import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * Created by Elí.Arizpe on 01/04/2020.
 */
@Entity
@Table(name = "user_entity")
@NamedQuery(name = "UserEntity.findAll", query = "SELECT a from UserEntity a")
@Where(clause = "soft_delete = 0")
@Data @Builder
public class UserEntity extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Size(min = 1, max = 255)
    private String email;
    private String password;
    @Size(min = 1, max = 255)
    private String name;
    @Size(min = 1, max = 255)
    private String lastName;
}
