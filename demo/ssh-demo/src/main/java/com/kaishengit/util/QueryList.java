package com.kaishengit.util;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

/**
 * @author Tonglin
 */
public class QueryList {

    private String parameter;
    private String queryCondition;
    private Object queryValue;

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getQueryCondition() {
        return queryCondition;
    }

    public void setQueryCondition(String queryCondition) {
        this.queryCondition = queryCondition;
    }

    public Object getQueryValue() {
        return queryValue;
    }

    public void setQueryValue(Object queryValue) {
        this.queryValue = queryValue;
    }

    public static List<QueryList> queryListsBuilder(HttpServletRequest request) {

        List<QueryList> queryList = new ArrayList<>();

        Enumeration<String> enumeration = request.getParameterNames();

        while (enumeration.hasMoreElements()) {
            String queryKey = enumeration.nextElement();
            String value = request.getParameter(queryKey);

            //q_eq_bd_marketPrice_or_price
            if (queryKey.startsWith("q_") && !"".equals(value) && value != null) {
                String[] strings = queryKey.split("_",4);
                if (strings == null || strings.length != 4) {
                    throw new RuntimeException("参数异常");
                }

                QueryList query = new QueryList();
                query.setParameter(strings[3]);
                query.setQueryCondition(strings[1]);
                query.setQueryValue(tranValue(strings[2], value));
                queryList.add(query);

            }

        }

        return queryList;
    }

    public static Object tranValue(String valueType,String value) {
        if ("s".equals(valueType)) {
            return value;
        } else if (StringUtils.isNumeric(value)) {
            return new BigDecimal(value);
        }
        return null;
    }

}
