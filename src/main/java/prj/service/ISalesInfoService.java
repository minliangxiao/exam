package prj.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.service.IService;

import prj.entity.BarChartDataItem;
import prj.entity.SalesInfo;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Rayman
 * @since 2018-02-04
 */
public interface ISalesInfoService extends IService<SalesInfo> {
	List<BarChartDataItem> summaryTypeRateByBrand(int month,String brand);
	List<BarChartDataItem> summaryBrandRateByType(int month,String type);
	List<BarChartDataItem> summaryBrandRate(Integer month);
	List<BarChartDataItem> summaryAssertRate1(Integer year,Integer month);
	List<BarChartDataItem> summaryAssertRate2(Integer year,Integer month);
}
