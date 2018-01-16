package com.springboot.study.common.beans;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EasyUIDataGrideResult {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private long total;

    private List<?> rows;

    public EasyUIDataGrideResult() {
    }

    public EasyUIDataGrideResult(long total, List<?> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EasyUIDataGrideResult(Long total, List<?> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static EasyUIDataGrideResult formatToList(String jsonData, Class<?> clazz) {
        try {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("rows");
            List<?> list = null;
            if (data.isArray() && data.size() > 0) {
                list = MAPPER.readValue(data.traverse(),MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return new EasyUIDataGrideResult(jsonNode.get("total").intValue(), list);
        } catch (Exception e) {
            return null;
        }
    }

}
