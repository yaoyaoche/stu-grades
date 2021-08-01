package io.renren.modules.generator.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * 学生成绩管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-21 16:34:14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@TableName("stu_grades")
public class StuGradesEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 *
	 */
	@TableId
	private Long id;
	/**
	 * 姓名
	 */
	@ColumnWidth(30)
	@ExcelProperty("姓名")
	private String name;
	/**
	 * 语文
	 */
	@ColumnWidth(20)
	@ExcelProperty("语文")
	private Integer chinese;
	/**
	 * 数学
	 */
	@ColumnWidth(20)
	@ExcelProperty("数学")
	private Integer maths;
	/**
	 * 英语
	 */
	@ColumnWidth(20)
	@ExcelProperty("英语")
	private Integer english;
	/**
	 * 总分
	 */
	@ColumnWidth(20)
	@ExcelProperty("总分")
	private Integer sum;

}
