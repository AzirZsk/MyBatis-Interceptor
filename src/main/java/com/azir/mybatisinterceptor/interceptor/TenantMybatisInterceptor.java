package com.azir.mybatisinterceptor.interceptor;

import com.azir.mybatisinterceptor.TenantContext;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.*;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.sql.Connection;

/**
 * @author zhangshukun
 * @since 2024/08/02
 */
@Slf4j
@Component
@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
public class TenantMybatisInterceptor implements Interceptor {

    private static final Field SQL_FIELD;

    static {
        try {
            SQL_FIELD = BoundSql.class.getDeclaredField("sql");
            SQL_FIELD.setAccessible(true);
        } catch (NoSuchFieldException e) {
            log.warn("无法获取BoundSql的sql字段");
            throw new RuntimeException(e);
        }
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        log.info("原始SQL：{}", sql);
        // 修改SQL，添加租户信息。假设每个表都有一个tenant_id字段
        String modifiedSql = addTenantFilter(sql, TenantContext.getTenantId());
        log.info("修改后的SQL：{}", modifiedSql);
        updateBoundSql(boundSql, modifiedSql);
        return invocation.proceed();
    }

    private void updateBoundSql(BoundSql boundSql, String sql) {
        try {
            // 使用静态变量反射修改sql，避免每次调用都重新获取对应字段
            SQL_FIELD.set(boundSql, sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String addTenantFilter(String sql, Integer tenantId) {
        if (tenantId == null) {
            log.warn("租户ID为空，无法添加租户过滤条件");
            return sql;
        }
        // 更新操作，在正常的框架中，是会使用jsqlparser解析sql，然后在插入租户的查询条件的。
        // 这里只是简单的示例
        if (sql.contains("where")) {
            int where = sql.lastIndexOf("where");
            if (sql.contains("and")) {
                return sql.substring(0, where) + " and tenant_id = '" + tenantId + "' and " + sql.substring(where);
            }
            return sql.substring(0, where) + " and tenant_id = '" + tenantId + "'";
        } else if (sql.contains("insert")) {
            int i = sql.indexOf(")");
            sql = sql.substring(0, i) + ",tenant_id" + sql.substring(i);
            int i1 = sql.lastIndexOf(")");
            sql = sql.substring(0, i1) + "," +  tenantId + sql.substring(i1);
            return sql;
        } else {
            return sql + " where tenant_id = '" + tenantId + "'";
        }
    }

}
