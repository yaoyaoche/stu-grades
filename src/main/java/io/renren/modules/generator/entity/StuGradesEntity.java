package io.renren.modules.generator.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 学生成绩管理
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2021-07-21 16:34:14
 */
@Data
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
	private String name;
	/**
	 * 语文
	 */
	private Integer chinese;
	/**
	 * 数学
	 */
	private Integer maths;
	/**
	 * 英语
	 */
	private Integer english;
	/**
	 * 总分
	 */
	private Integer sum;

}
