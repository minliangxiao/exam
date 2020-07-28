package prj.service.impl;

import prj.entity.*;
import prj.mapper.*;
import prj.service.*;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

/**
 * <p>
 * 试卷服务实现类
 * </p>
 */
@Service
public class TestpaperServiceImpl extends ServiceImpl<TestpaperMapper, Testpaper> implements ITestpaperService {
	@Resource TestpaperMapper mapper;
	public String findMaxCode(int schoolId){
		return mapper.findMaxCode(schoolId);
	}
}