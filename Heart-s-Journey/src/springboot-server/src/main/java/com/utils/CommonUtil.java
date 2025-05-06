package com.utils;

import java.util.Random;
import org.springframework.stereotype.Component;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Objects;

// 标记该类为Spring框架的组件，Spring将管理其生命周期
@Component
public class CommonUtil {
    /**
     * 生成指定长度的随机字符串，包含小写字母和数字
     *
     * @param num 生成字符串的长度
     * @return 返回生成的随机字符串
     */
    public static String getRandomString(Integer num) {
        String base = "abcdefghijklmnopqrstuvwxyz0123456789"; // 可选字符集
        Random random = new Random(); // 实例化一个随机数生成器
        StringBuffer sb = new StringBuffer(); // 使用StringBuffer来构建最终的字符串
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length()); // 生成一个随机索引
            sb.append(base.charAt(number)); // 从base中获取字符并追加到StringBuffer中
        }
        return sb.toString();
    }

    /**
     * 生成指定长度的随机数字字符串
     *
     * @param num 生成字符串的长度
     * @return 返回生成的随机数字字符串
     */
    public static String getRandomNumber(Integer num) {
        String base = "0123456789"; // 只包含数字的字符集
        Random random = new Random(); // 随机数生成器
        StringBuffer sb = new StringBuffer(); // StringBuffer用于构建字符串
        for (int i = 0; i < num; i++) {
            int number = random.nextInt(base.length()); // 随机选择一个数字
            sb.append(base.charAt(number)); // 添加到字符串缓冲区
        }
        return sb.toString();
    }

    /**
     * 从Excel单元格中获取值并转换为字符串格式
     *
     * @param cell 单元格对象
     * @return 单元格内容的字符串表示
     */
    public static String getCellValue(Cell cell) {
        String resultValue = ""; // 默认返回值为空字符串
        if (Objects.isNull(cell)) { // 检查单元格是否为空
            return resultValue;
        }

        DataFormatter formatter = new DataFormatter(); // 用于格式化单元格内容
        FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator(); // 创建公式求值器

        // 根据单元格类型进行处理
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING: // 字符串类型
                resultValue = StringUtils.isEmpty(cell.getStringCellValue()) ? "" : cell.getStringCellValue().trim();
                break;
            case Cell.CELL_TYPE_FORMULA: // 公式类型
                resultValue = formatter.formatCellValue(cell, evaluator);
                break;
            case Cell.CELL_TYPE_BOOLEAN: // 布尔类型
                resultValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_NUMERIC: // 数值或日期类型
                /**
                 * format 的值可能为以下这些 yyyyMMddHHmmss
                 * yyyy-MM-dd----- 14
                 * yyyy年m月d日----- 31
                 * yyyy年m月--------57
                 * m月d日  -----------58
                 * HH:mm-----------20
                 * h时mm分  --------- 32
                 */
                // 根据数据格式化字符串决定如何格式化单元格
                Object val = cell.getNumericCellValue();
                // POI Excel 日期格式转换
                String formatDate = ""; // 初始化日期格式
                switch (cell.getCellStyle().getDataFormat()) { // 检查单元格的数据格式
                    case 14: formatDate = "yyyy-MM-dd"; break;
                    case 20: formatDate = "HH:mm"; break;
                    case 21: formatDate = "HH:mm:ss"; break;
                    case 31: formatDate = "yyyy年MM月dd日"; break;
                    case 32: formatDate = "HH时mm分"; break;
                    case 33: formatDate = "HH时mm分mm秒"; break;
                    case 57: formatDate = "yyyy年MM月"; break;
                    case 58: formatDate = "MM月dd日"; break;
                    case 176: formatDate = "yyyy-MM-dd HH:mm:ss"; break;
                }
                // 如果日期格式不为空，则按日期处理
                if (!"".equals(formatDate)) {
                    resultValue = new SimpleDateFormat(formatDate).format(DateUtil.getJavaDate((Double) val));
                } else { // 否则按数值处理
                    resultValue = new DecimalFormat("#.######").format(val);
                }
                break;
            default: // 其他类型，取空字符串
                break;
        }
        return resultValue; // 返回转换后的单元格内容
    }
}



