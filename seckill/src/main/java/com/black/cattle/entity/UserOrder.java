package com.black.cattle.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author vicente
 * @since 2021-05-16
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserOrder extends Model<UserOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 下单时间

     */
    private LocalDateTime orderTime;

    /**
     * 下单人
     */
    private String orderBy;

    /**
     * 订单金额
     */
    private BigDecimal orderPrice;

    /**
     * 0 未支付；1 已支付
     */
    private Integer orderFlag;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
