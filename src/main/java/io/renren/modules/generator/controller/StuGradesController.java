package io.renren.modules.generator.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import io.renren.modules.generator.dao.StuGradesDao;
import io.renren.modules.generator.service.impl.StuGradesServiceImpl;
import io.renren.modules.generator.service.impl.UploadDataListener;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.renren.modules.generator.entity.StuGradesEntity;
import io.renren.modules.generator.service.StuGradesService;
import io.renren.common.utils.PageUtils;
import io.renren.common.utils.R;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 学生成绩管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-21 16:34:14
 */
@RestController
@RequestMapping("generator/stugrades")
public class StuGradesController {
    @Autowired
    private StuGradesService stuGradesService;
//    @Autowired
//    private StuGradesDao stuGradesDao;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("generator:stugrades:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = stuGradesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("generator:stugrades:info")
    public R info(@PathVariable("id") Long id){
		StuGradesEntity stuGrades = stuGradesService.getById(id);
        return R.ok().put("stuGrades", stuGrades);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("generator:stugrades:save")
    public R save(@RequestBody StuGradesEntity stuGrades){
        stuGradesService.gradessum(stuGrades);
		stuGradesService.save(stuGrades);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("generator:stugrades:update")
    public R update(@RequestBody StuGradesEntity stuGrades){
        stuGradesService.gradessum(stuGrades);
        stuGradesService.updateById(stuGrades);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("generator:stugrades:delete")
    public R delete(@RequestBody Long[] ids){
		stuGradesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 排名
     */
    @RequestMapping("/rank")
    @RequiresPermissions("generator:stugrades:rank")
    public R rank(){
        List<StuGradesEntity> list = stuGradesService.rank();

        return R.ok().put("list",list);
    }

    /**
     * 导入excel
     */
    @RequestMapping("/excelin")
//    @RequiresPermissions("generator:stugrades:excelin")
    public R upload(MultipartFile file) throws IOException {
        System.out.println("已运行");
        EasyExcel.read(file.getInputStream(), StuGradesEntity.class, new UploadDataListener(stuGradesService)).sheet().doRead();

        return R.ok();
    }

    /**
     * 导出excel
     */
    @RequestMapping("/excelout")
    @RequiresPermissions("generator:stugrades:excelout")
    // @GetMapping("/exportUserExcel")
    public R exportExcel() {
        stuGradesService.generateStudentsExcel();
        return R.ok();
    }

}
