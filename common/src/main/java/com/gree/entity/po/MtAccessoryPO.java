package com.gree.entity.po;

import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;
import java.sql.Blob;

import com.gree.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * <p>
 * 物料附件表
 * </p>
 *
 * @author jinyu
 * @since 2019-04-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("MT_ACCESSORY")
public class MtAccessoryPO extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 附件号码
     */
    @TableId(value = "MT_ACCESSORY_GUID",type = IdType.ID_WORKER_STR)
    private String mtAccessoryGuid;

    /**
     * 物料编号
     */
    @TableField("MT_GUID")
    private String mtGuid;

    /**
     * 物料附件资源
     */
    @TableField("MT_ACCESSORY_RESOURCE")
    private String mtAccessoryResource;

    /**
     * 入库图片大小
     */
    @TableField("RESOURCE_SIZE")
    private Long resourceSize;

    /**
     * 最后修改日期
     */
    @TableField(value = "MODIFY_DATE",fill = FieldFill.INSERT_UPDATE)
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
    private Integer version;


}
