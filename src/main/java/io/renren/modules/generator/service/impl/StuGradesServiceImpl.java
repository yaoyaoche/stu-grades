package io.renren.modules.generator.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.alibaba.excel.EasyExcel;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.*;
//import java.util.logging.Logger;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.Query;

import io.renren.modules.generator.dao.StuGradesDao;
import io.renren.modules.generator.entity.StuGradesEntity;
import io.renren.modules.generator.service.StuGradesService;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.Map;
//import org.apache.commons.lang.StringUtils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import com.alibaba.fastjson.JSON;


@Service("stuGradesService")
public class StuGradesServiceImpl extends ServiceImpl<StuGradesDao, StuGradesEntity> implements StuGradesService {

    @Autowired
    StuGradesDao stuGradesDao;
    @Autowired
    private HttpServletResponse response;
//    @Override
//    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<StuGradesEntity> page = this.page(
//                new Query<StuGradesEntity>().getPage(params),
//                new QueryWrapper<StuGradesEntity>()
//        );
//
//        return new PageUtils(page);
//    }

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        String key = (String) params.get("key");
        IPage<StuGradesEntity> page = this.page(
                new Query<StuGradesEntity>().getPage(params),
                new QueryWrapper<StuGradesEntity>().like(StringUtils.isNotBlank(key), "name", key)
        );
        return new PageUtils(page);
    }

    public void gradessum(StuGradesEntity entity) {
        entity.setSum(entity.getChinese() + entity.getEnglish() + entity.getMaths());
//        return stuGradesDao.gradessum(entity);
    }

    public List rank() {
        List<StuGradesEntity> list = stuGradesDao.selectList(null);
        list.sort((y, x) -> Integer.compare(x.getSum(), y.getSum()));
        return list;
    }


    public  void  generateStudentsExcel(){
        List<StuGradesEntity> students = stuGradesDao.selectList(null);
        //内容策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        //设置 水平居中
        contentWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        //导出Excel
        //设置两个头
        //响应内容格式
        response.setContentType("application/vnd.ms-excel");
        //设置前端下载文件名
        response.setHeader("Content-disposition", "attachment;filename=scores.xlsx");
        try {
            EasyExcel.write(response.getOutputStream(), StuGradesEntity.class).sheet("工作表1").doWrite(students);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
