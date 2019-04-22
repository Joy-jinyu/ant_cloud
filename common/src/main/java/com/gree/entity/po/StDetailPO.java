package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 入库明细表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("ST_DETAIL")
@KeySequence("SEQ_stDetail")
public class StDetailPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 入库主表
     */
    @TableId(value = "STORAGE_GUID",type = IdType.INPUT)
    private String storageGuid;

    /**
     * 物料编号
     */
    @TableField("MATERIAL_GUID")
    private String materialGuid;

    /**
     * 物料加库存数量
     */
    @TableField("ST_DETAIL_COUNT")
    private Double stDetailCount;

    /**
     * 入库明细单号
     */
    @TableId("ST_DETAIL_GUID")
    private String stDetailGuid;

    /**
     * 最后修改日期
     */
    @TableField("MODIFY_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime modifyDate;

    /**
     * 最后修改用户
     */
    @TableField("MODIFY_USER")
    private String modifyUser;

    /**
     * 版本号
     */
    @TableField("VERSION")
    private Double version;


}
