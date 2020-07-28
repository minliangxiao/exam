package prj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableField;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import prj.util.*;
/**
 * <p>
 * 年级
 * </p>
 */
@TableName("grade")
public class Grade implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
	 * 主键，自动增长ID。
	 */
	@TableId(type = IdType.AUTO)
	private Integer id;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}


    @TableField(value="name")
    private String name;
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    @TableField(value="is_deleted")
    private Boolean isDeleted;
    public void setIsDeleted(Boolean isDeleted){
        this.isDeleted = isDeleted;
    }
    public Boolean getIsDeleted(){
        return this.isDeleted;
    }
}


