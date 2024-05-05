package org.delivery.db.store;

import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StoreRepository extends JpaRepository<StoreEntity , Long> {
    //特定id状態
    //select * from store where id = ? and status = ? oreder by id desc limit 1
    Optional<StoreEntity> findFirstByIdAndStatusOrderByIdDesc(Long id, StoreStatus status);
    //有効なストアのリスト
    //select * from store where status = ? order by id desc
    List<StoreEntity> findAllByStatusOrderByIdDesc(StoreStatus status);
    //有効な特定カテゴリーストアのリスト
    List<StoreEntity> findAllByStatusAndCategoryOrderByStar(StoreStatus status , StoreCategory storeCategory);
    //select * from store where name  = ? and status =? order by id desc limit 1
    Optional<StoreEntity> findFirstByNameAndStatusOrderByIdDesc(String name, StoreStatus status);
}
