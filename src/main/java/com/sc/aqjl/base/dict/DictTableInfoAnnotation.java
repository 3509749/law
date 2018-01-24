package com.sc.aqjl.base.dict;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
* 元注解@Target,@Retention,@Documented,@Inherited
*
*      @Target 表示该注解用于什么地方，可能的 ElemenetType 参数包括：
*          ElemenetType.CONSTRUCTOR 构造器声明
*          ElemenetType.FIELD 域声明（包括 enum 实例）
*          ElemenetType.LOCAL_VARIABLE 局部变量声明
*          ElemenetType.METHOD 方法声明
*          ElemenetType.PACKAGE 包声明
*          ElemenetType.PARAMETER 参数声明
*          ElemenetType.TYPE 类，接口（包括注解类型）或enum声明
*         
*      @Retention 表示在什么级别保存该注解信息。可选的 RetentionPolicy 参数包括：
*          RetentionPolicy.SOURCE 注解将被编译器丢弃
*          RetentionPolicy.CLASS 注解在class文件中可用，但会被VM丢弃
*          RetentionPolicy.RUNTIME VM将在运行期也保留注释，因此可以通过反射机制读取注解的信息。
*         
*      @Documented 将此注解包含在 javadoc 中
*     
*      @Inherited 允许子类继承父类中的注解
*   
*/  
@Target(ElementType.TYPE)   
@Retention(RetentionPolicy.RUNTIME)   
@Documented
@Inherited
public @interface DictTableInfoAnnotation {
	/**
	 * @方法功能描述:字典表中名称字段名
	 * @return:String
	 */
	
	/**
	 * 字典表表明
	 */
	public String TableName() default "";
	
	/**
	 * 字典表编码字段的名称
	 * 功能描述：
	 * @return 
	 * @版本 1.0
	 */
	public String CodeField() default "CODE";
	
	/**
	 * 字典表名称字段的名称
	 * 功能描述：
	 * @return 
	 * @版本 1.0
	 * 修改描述
	 */
	public String NameField() default "NAME";
	
	/**
	 * 字典表排序字段的名称，如果没有可以为空，或者用编码字段的名称代替
	 * 功能描述：
	 * @return 
	 * @版本 1.0
	 * 修改描述
	 */
	public String OrderField() default "CODE";
	
	/**
	 * 字典表逻辑主键字段的名称，不能为空，可以用编码字段的名称代替
	 * 功能描述：
	 * @return 
	 * @版本 1.0
	 * 修改描述
	 */
	//public String IdField() default "CODE";
	
	/**
	 * 父节点字段名（PARENT_CODE）
	 * 功能描述：
	 * @return 
	 * @版本 1.0
	 * 修改描述
	 */
	public String ParentField() default "";
}
