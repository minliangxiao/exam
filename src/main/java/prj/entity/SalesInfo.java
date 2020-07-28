package prj.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author Rayman
 * @since 2018-02-04
 */
@TableName("sales_info")
public class SalesInfo implements Serializable {

    private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId(type = com.baomidou.mybatisplus.enums.IdType.AUTO)
	private Integer id;

	/**
	 * 
	 */
	private String no;

	/**
	 * 
	 */
	private String brand;

	/**
	 * 
	 */
	private String type;

	/**
	 * 
	 */
	private String product;

	/**
	 * 
	 */
	@TableField(value="buy_price")
	private BigDecimal buyPrice;

	/**
	 * 
	 */
	@TableField(value="sell_price")
	private BigDecimal sellPrice;

	/**
	 * 
	 */
	private String status;

	/**
	 * 
	 */
	@TableField(value="buy_time")
	private Date buyTime;

	/**
	 * 
	 */
	@TableField(value="sell_time")
	private Date sellTime;

	/**
	 * 
	 */
	@TableField(value="is_deleted")
	private Boolean isDeleted;

	/**
	 * 
	 */
	@TableField(value="upload_user")
	private String uploadUser;

	/**
	 * 
	 */
	@TableField(value="upload_time")
	private String uploadTime;



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getBuyTime() {
		return buyTime;
	}

	public void setBuyTime(Date buyTime) {
		this.buyTime = buyTime;
	}

	public Date getSellTime() {
		return sellTime;
	}

	public void setSellTime(Date sellTime) {
		this.sellTime = sellTime;
	}

	public Boolean getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

}
