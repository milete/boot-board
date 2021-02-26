package com.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * description：excel工具类
 *
 * @author jiac
 * @date 2021/1/6 11:00
 */
@Slf4j
public class ExcelUtil {

    /**
     * 工作簿
     */
    private static Workbook wb;

    /**
     * 工作表
     */
    private static Sheet sheet;

    /**
     * 导出excel
     *
     * @param dataList 数据集合
     * @param clazz    实体类
     * @param fileName 导出文件名
     * @throws Exception
     */
    public static void export(List<?> dataList, Class<?> clazz, String fileName) throws Exception {
        Map<String, String> titleMap = AnnotationUtil.getSwaggerAnnotationData(clazz);
        export(dataList, titleMap, fileName);
    }

    /**
     * 导出
     *
     * @param dataList 数据集合
     * @param titleMap 标题行 Map<字段名, 字段描述>
     * @param fileName 导出文件名
     * @throws Exception
     */
    private static void export(List<?> dataList, Map<String, String> titleMap, String fileName) throws Exception {
        //创建工作簿
        createWorkbook(fileName);
        //标题行
        createHeaderRow(titleMap);
        //文本行
        createContentRow(dataList, titleMap);
        //导出数据
        export(fileName);
    }

    /**
     * 创建工作簿
     *
     * @param fileName 文件名
     */
    private static void createWorkbook(String fileName) {
        if (StringUtils.isBlank(fileName)) {
            log.error("导出文件名不能为空");
        }
        if (fileName.endsWith("xls")) {
            wb = new HSSFWorkbook();
        } else if (fileName.endsWith("xlsx")) {
            wb = new XSSFWorkbook();
        } else {
            log.error("导出文件格式不支持");
        }
        sheet = wb.createSheet();
    }

    /**
     * 标题行
     *
     * @param titleMap Map<字段名, 字段描述>
     */
    private static void createHeaderRow(Map<String, String> titleMap) {
        Row row = sheet.createRow(0);
        int i = 0;
        for (String value : titleMap.values()) {
            Cell cell = row.createCell(i);
            cell.setCellValue(value);
            i++;
        }
    }

    /**
     * 文本行
     *
     * @param dataList 数据集合
     * @param titleMap 标题行 Map<字段名, 字段描述>
     */
    private static void createContentRow(List<?> dataList, Map<String, String> titleMap) {
        try {
            int i = 0;
            for (Object obj : dataList) {
                Row row = sheet.createRow(1 + i);
                int j = 0;
                for (String column : titleMap.keySet()) {
                    String method = "get" + column.substring(0, 1).toUpperCase() + column.substring(1);
                    Method m = obj.getClass().getMethod(method, null);
                    Object value = m.invoke(obj, null);
                    Cell cell = row.createCell(j);
                    cell.setCellValue(Objects.nonNull(value) ? toString(value) : "");
                    j++;
                }
                i++;
            }
        } catch (Exception e) {
            log.error("创建文本行异常", e);
            throw new RuntimeException();
        }
    }

    /**
     * 导出
     *
     * @param fileName 导出文件名
     * @throws Exception
     */
    private static void export(String fileName) throws Exception {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = requestAttributes.getResponse();
        BufferedOutputStream bos = null;
        try {
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            bos = new BufferedOutputStream(response.getOutputStream());
            wb.write(bos);
        } catch (Exception e) {
            log.error("导出数据异常", e);
            throw new RuntimeException();
        } finally {
            if (wb != null) {
                wb.close();
            }
            if (bos != null) {
                bos.close();
            }
        }
    }

    /**
     * 格式转换
     *
     * @param value
     * @return
     */
    private static String toString(Object value) {
        if (value instanceof String) {
            return value.toString();
        } else {
            if (value instanceof Integer) {
                return BigDecimal.valueOf((long) (Integer) value).toPlainString();
            } else if (value instanceof Double) {
                return BigDecimal.valueOf((Double) value).toPlainString();
            } else if (value instanceof Long) {
                return BigDecimal.valueOf((Long) value).toPlainString();
            } else if (value instanceof BigDecimal) {
                return ((BigDecimal) value).toPlainString();
            } else if (value instanceof Date) {
                return DateUtil.format((Date) value);
            } else if (value instanceof LocalDateTime) {
                return DateUtil.format((LocalDateTime) value);
            } else {
                return value.toString();
            }
        }
    }
}
