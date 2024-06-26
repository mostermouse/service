package org.delivery.db.userorder;

import org.delivery.db.userorder.enums.UserOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserOrderRepository extends JpaRepository<UserOrderEntity, Long> {

    //特定のユーザーの全ての注文
    //select * from user_order where user_id = ? and status = ? order by id desc;
    List<UserOrderEntity> findAllByUserIdAndStatusOrderByIdDesc(Long userId, UserOrderStatus status);

    //特定の注文
    //select * from user_order where id = ? and status = ? and user_id = ?
    Optional<UserOrderEntity> findAllByIdAndStatusAndUserId(Long id, UserOrderStatus status, Long userId);

    List<UserOrderEntity> findAllByUserIdAndStatusInOrderByIdDesc(Long userId, List<UserOrderStatus> status);

    Optional<UserOrderEntity> findAllByIdAndUserId(Long id, Long userId);

}
