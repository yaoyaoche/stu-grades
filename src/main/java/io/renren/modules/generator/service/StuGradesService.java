package io.renren.modules.generator.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.renren.common.utils.PageUtils;
import io.renren.modules.generator.entity.StuGradesEntity;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 学生成绩管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-21 16:34:14
 */
public interface StuGradesService extends IService<StuGradesEntity> {

    PageUtils queryPage(Map<String, Object> params);
    public void gradessum(StuGradesEntity entity);
    public List rank();
//    public void export(HttpServletResponse res) throws IOException;
//    public List list();
    public  void  generateStudentsExcel();
}

