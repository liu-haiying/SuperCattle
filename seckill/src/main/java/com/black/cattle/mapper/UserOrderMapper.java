package com.black.cattle.mapper;

import com.black.cattle.entity.UserOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author vicente
 * @since 2021-05-16
 */
@Mapper
public interface UserOrderMapper extends BaseMapper<UserOrder> {

    int saveOrder(@Param("order")UserOrder order);
}
