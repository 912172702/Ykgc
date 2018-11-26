package com.prtr.ykgc;

import org.mybatis.generator.api.dom.java.FullyQualifiedJavaType;
import org.mybatis.generator.internal.types.JavaTypeResolverDefaultImpl;

import java.sql.Types;

/**
 * @Author: Knox
 * @Date: 2018/11/26 6:22 PM
 * @Description: You Know
 * @Version 1.0
 */
public class OracleJavaTypeResolver extends JavaTypeResolverDefaultImpl {
    public OracleJavaTypeResolver() {
        super();
        super.typeMap.put(Types.OTHER, new JdbcTypeInformation("NVARCHAR2", new FullyQualifiedJavaType(String.class.getName())));
    }

}
