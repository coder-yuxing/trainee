package com.yuxing.trainee.generator;


import com.dabanjia.boush.Generate;
import com.dabanjia.boush.config.GlobalConfig;
import com.dabanjia.boush.config.TableConfig;

import java.util.Arrays;
import java.util.List;

/**
 * 代码生成工具
 *
 * @author yuxing
 */
public class GeneratorApplication {

    private static final String AUTHOR = "yuxing";

    private static final String FORMAT = "yyyy/MM/dd";

    public static void main(String[] args) throws Exception {
//        TableConfig tableConfig1 = TableConfig.builder().tableName("uac_client_role").beanName("ClientRole").remarks("权限(角色)").build();
//        TableConfig tableConfig2 = TableConfig.builder().tableName("uac_client_permission").beanName("ClientPermission").remarks("客户端权限").build();
        TableConfig tableConfig4 = TableConfig.builder().tableName("uac_user_role_relation").beanName("UserRoleRelation").remarks("用户与角色关系表").build();
        TableConfig tableConfig5 = TableConfig.builder().tableName("uac_user_client_relation").beanName("UserClientRelation").remarks("用户与客户端关系表").build();

        List<TableConfig> tableConfigs = Arrays.asList(tableConfig4, tableConfig5);
        GlobalConfig globalConfig = GlobalConfig.builder()
                .configPath("/generate.properties")
                .author(AUTHOR)
                .datePattern(FORMAT)
                .isCover(true)
                .beanModuleName("trainee-biz/trainee-uac/trainee-uac-service")
                .beanPackage("com.yuxing.trainee.uac.infrastructure.dao.model")
                .mapperModuleName("trainee-biz/trainee-uac/trainee-uac-service")
                .beanMapperPackage("com.yuxing.trainee.uac.infrastructure.dao.mapper")
                .mapperPackage("com.yuxing.trainee.uac.infrastructure.dao.xml")
                .tableConfigs(tableConfigs).build();

        new Generate(globalConfig).execute();

    }

}
