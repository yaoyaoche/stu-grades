package io.renren.modules.generator.dao;

import io.renren.modules.generator.entity.StuGradesEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 学生成绩管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-21 16:34:14
 */
@Mapper
@Component(value = "StuGradesDao")
public interface StuGradesDao extends BaseMapper<StuGradesEntity> {

//   public int gradessum(StuGradesEntity entity);

}
