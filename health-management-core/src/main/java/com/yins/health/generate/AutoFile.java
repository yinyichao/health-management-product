package com.yins.health.generate;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;

import java.util.Collections;

/**
 * @Auther: 联想
 * @Date: 2023/11/1 15:47
 * @Description:
 */
public class AutoFile {
    public static void main(String[] args) {
        test1();
    }


    public static void test1(){
        //1、数据库配置
        FastAutoGenerator.create("jdbc:dm://192.168.10.27:5236/ECONOMY_SHENZHEN&zeroDateTimeBehavior=converToNull&useUnicode=true&characterEncoding=utf-8", "ECONOMY_SHENZHEN", "ShzecY@123#")
                //2、全局配置
                .globalConfig(builder -> {
                    builder.author("szzs")
                            .outputDir("user.dir" + "/src/main/java")   //设置输出路径
                            .commentDate("yyyy-MM-dd HH:mm:ss")   //设置日期格式
                            .dateType(DateType.ONLY_DATE)  //生成的实体类中日期的类型
                            .fileOverride()   //覆盖之前的文件
                            .enableSwagger()  //开启swagger模式
                            .disableOpenDir();   //禁止打开输出目录，默认打开
                })
                //3、包配置
                .packageConfig(builder -> {
                    builder.parent("com")
                            .moduleName("szzs.ywtg")
                            .entity("entity.po")
                            .service("service")
                            .serviceImpl("impl")
                            .mapper("mapper")
                            .controller("controller")
//                            .other("util")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"/src/main/resources/mapper"));    //配置 mapper.xml 路径信息：项目的 resources 目录下*/
                })
                //4、策略配置
                .strategyConfig(builder -> {
                    builder.addInclude("TB_ECONOMIC_PRJ_SORT_OLL_M")   //设置需要生成的数据表名
                            .addTablePrefix("t_", "c_", "TB_")   //设置过滤表前缀
                            //4.1、Mapper策略配置
                            .mapperBuilder()
                            .superClass(BaseMapper.class)   //设置父类
                            .formatMapperFileName("%sMapper")   //格式化mapper文件
                            .enableMapperAnnotation()   //开启@Mapper注解
                            .formatXmlFileName("%sMapper")

                            //4.2、service策略配置
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")

                            //4.3、实体类策略配置
                            .entityBuilder()
                            .enableLombok()   //开启lombok
                            .disableSerialVersionUID()   //不实现Serializable接口   不生产SerialVersionUID
                            .logicDeleteColumnName("delFlag")  //逻辑删除
                            .naming(NamingStrategy.underline_to_camel)   //数据库表映射到实体的命名策略   下划线转驼峰命名
                            .columnNaming(NamingStrategy.underline_to_camel)   //数据库表的字段映射到实体的命名策略   下划线转驼峰命名
                            .addTableFills(
                                    new Column("create_time", FieldFill.INSERT),
                                    new Column("update_time", FieldFill.INSERT_UPDATE)
                            )    //自动填充
                            .enableTableFieldAnnotation()   //开启生成实体类时生成字段注解

                            //Controller策略配置
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle();   //开启生成@RestController注解
                })
                //5、模板引擎
                .templateEngine(new FreemarkerTemplateEngine())   //默认
                .templateConfig(builder -> {
                    builder.entity("/templates/entity.java")
                            .service("/templates/service.java")
                            .serviceImpl("/templates/serviceImpl.java")
                            .mapper("/templates/mapper.java")
                            .mapperXml("/templates/mapper.xml")
                            .controller("/templates/controller.java");
                })   //默认
                //6、执行
                .execute();

    }
}
