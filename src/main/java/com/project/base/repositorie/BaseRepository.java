package com.project.base.repositorie;

import com.project.base.entity.BaseEntity;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by Elí.Arizpe on 01/04/2020.
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseEntity,E extends Serializable> extends PagingAndSortingRepository<T,E> {
    List<T> findAll();
    T findById(long id);
    T findByUuid(String uuid);
    List<T> findByCreatedAt(LocalDateTime createdAt);
    List<T> findByCreatedAtBetween(LocalDateTime startDate, LocalDateTime finishDate);
    List<T> findByModifiedAtBetween(LocalDateTime startDate, LocalDateTime finishDate);
    List<T> findBySoftDelete(boolean softDelete);
}
