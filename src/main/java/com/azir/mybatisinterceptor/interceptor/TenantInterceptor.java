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
public class TenantInterceptor implements Interceptor {

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
        BoundSql boundSql = statementHandler.getBoundSql();
        String sql = boundSql.getSql();
        log.info("原始SQL：{}", sql);
        // 修改SQL，添加租户信息。假设每个表都有一个tenant_id字段
        String modifiedSql = addTenantFilter(sql, TenantContext.getTenantId());

        updateBoundSql(boundSql, modifiedSql);
        return invocation.proceed();
    }


    private void updateBoundSql(BoundSql boundSql, String sql) {
        try {
            Field sqlField = BoundSql.class.getDeclaredField("sql");
            sqlField.setAccessible(true);
            sqlField.set(boundSql, sql);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String addTenantFilter(String sql, Integer tenantId) {
        if (tenantId == null) {
            throw new NullPointerException("租户id为空");
        }
        return null;
    }


}
