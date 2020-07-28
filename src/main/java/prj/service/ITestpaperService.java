package prj.service;

import prj.entity.*;
import com.baomidou.mybatisplus.service.IService;

/**
 * <p>
 * 试卷服务接口类
 * </p>
 */
public interface ITestpaperService extends com.baomidou.mybatisplus.service.IService<Testpaper> {
	String findMaxCode(int schoolId);
}