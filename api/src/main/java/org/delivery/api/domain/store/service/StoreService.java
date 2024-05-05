package org.delivery.api.domain.store.service;

import lombok.RequiredArgsConstructor;
import org.delivery.api.common.error.ErrorCode;
import org.delivery.api.common.exception.ApiException;
import org.delivery.db.store.StoreEntity;
import org.delivery.db.store.StoreRepository;
import org.delivery.db.store.enums.StoreCategory;
import org.delivery.db.store.enums.StoreStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final StoreRepository storeRepository;

    //有効なストアのインポート
    public StoreEntity getStoreWithThrow(Long id){
        var entity = storeRepository.findFirstByIdAndStatusOrderByIdDesc(id, StoreStatus.REGISTERED);
        return entity.orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }

    //ストア登録
    public StoreEntity register(StoreEntity storeEntity){
        return Optional.ofNullable(storeEntity)
                .map(it -> {
                    it.setStar(0);
                    it.setStatus(StoreStatus.REGISTERED);

                    return storeRepository.save(it);

                })
                .orElseThrow(() -> new ApiException(ErrorCode.NULL_POINT));
    }
    //カテゴリーでストアを検索
    public List<StoreEntity> searchByCategory(StoreCategory storeCategory){
        var list = storeRepository.findAllByStatusAndCategoryOrderByStar(
                StoreStatus.REGISTERED,
                storeCategory
        );
        return list;
    }



    //フルストア
    public List<StoreEntity> registerStore(){
        var list = storeRepository.findAllByStatusOrderByIdDesc(StoreStatus.REGISTERED);
        return list;
    }
}
