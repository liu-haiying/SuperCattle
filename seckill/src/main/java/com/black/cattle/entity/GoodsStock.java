package com.black.cattle.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
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
public class GoodsStock extends Model<GoodsStock> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String goodsNo;

    private Integer stockNum;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
