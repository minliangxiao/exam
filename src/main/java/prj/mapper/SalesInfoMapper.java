package prj.mapper;

import prj.entity.BarChartDataItem;
import prj.entity.SalesInfo;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;

/**
 * <p>
 * Mapper接口
 * </p>
 *
 * @author Rayman
 * @since 2018-02-04
 */
public interface SalesInfoMapper extends BaseMapper<SalesInfo> {
	List<BarChartDataItem> summaryTypeRateByBrand(@Param("month") Integer month,@Param("brand")String brand);
	List<BarChartDataItem> summaryBrandRateByType(@Param("month") Integer month,@Param("type")String type);
	List<BarChartDataItem> summaryBrandRate(@Param("month") Integer month);
	List<BarChartDataItem> summaryAssertRate1(@Param("year") Integer year,@Param("month") Integer month);
	List<BarChartDataItem> summaryAssertRate2(@Param("year") Integer year,@Param("month") Integer month);
}