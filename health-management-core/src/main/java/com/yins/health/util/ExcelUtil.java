package com.yins.health.util;

import com.alibaba.excel.EasyExcel;
import org.apache.commons.codec.Charsets;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;

public class ExcelUtil {
    /**
     * 导出模板
     * @param response
     * @param fileName
     * @param head
     * @throws IOException
     */
    public static void exportTemplate(HttpServletResponse response, String fileName, Class head) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(Charsets.UTF_8.name());
        String name = URLEncoder.encode(fileName, Charsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + name + ".xlsx");
        EasyExcel.write(response.getOutputStream(), head).sheet(fileName).doWrite(Collections.emptyList());
    }
    /**
     * 导出数据
     * @param response
     * @param fileName
     * @param head
     * @throws IOException
     */
    public static void exportTemplateDate(HttpServletResponse response, String fileName, Class head, List<?> date, String sheetName) throws IOException {
        response.setContentType("application/vnd.ms-excel");
        response.setCharacterEncoding(Charsets.UTF_8.name());
        String name = URLEncoder.encode(fileName, Charsets.UTF_8.name());
        response.setHeader("Content-disposition", "attachment;filename=" + name + ".xlsx");
        EasyExcel.write(response.getOutputStream(), head).sheet(sheetName).doWrite(date);
    }

}