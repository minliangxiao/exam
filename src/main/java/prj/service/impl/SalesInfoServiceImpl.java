package prj.service.impl;

import prj.entity.BarChartDataItem;
import prj.entity.SalesInfo;
import prj.mapper.SalesInfoMapper;
import prj.service.ISalesInfoService;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

@Service
public class SalesInfoServiceImpl extends ServiceImpl<SalesInfoMapper, SalesInfo> implements ISalesInfoService {
	@Resource SalesInfoMapper mapper;
	public List<BarChartDataItem> summaryTypeRateByBrand(int month,String brand){
		return mapper.summaryTypeRateByBrand(month, brand);
	}
	public List<BarChartDataItem> summaryBrandRateByType(int month,String type){
		return mapper.summaryBrandRateByType(month, type);
	}
	public List<BarChartDataItem> summaryBrandRate(Integer month){
		return mapper.summaryBrandRate(month);
	}
	public List<BarChartDataItem> summaryAssertRate1(Integer year,Integer month){
		return mapper.summaryAssertRate1(year,month);
	}
	public List<BarChartDataItem> summaryAssertRate2(Integer year,Integer month){
		return mapper.summaryAssertRate2(year,month);
	}
}
