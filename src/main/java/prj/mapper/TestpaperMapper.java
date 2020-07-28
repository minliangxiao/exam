package prj.mapper;

import prj.entity.*;

import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.mapper.BaseMapper;
/**
 * <p>
 * 试卷数据访问对象。
 * </p>
 */
public interface TestpaperMapper extends BaseMapper<Testpaper> {
	String findMaxCode(@Param("schoolId") Integer schoolId);
}