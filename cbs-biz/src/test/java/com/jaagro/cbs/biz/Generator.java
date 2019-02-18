package com.jaagro.cbs.biz;

import com.src.common.util.MybatisGeneratorUtil;
import com.src.common.util.PropertiesFileUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成类
 * Created by src on 2018/6/25.
 * 放在Dao层下面
 */
public class Generator {

    //Model + ModelExample 存放的路径和包名
    private static String targetProjectModel = "cbs-biz";
    private static String modelPack = "com.jaagro.cbs.biz.model";

    //Mapper.java + Mapper.xml 存放路径和包名
    private static String targetProjectDao = "cbs-biz";
    private static String mapperPack = "com.jaagro.cbs.biz.mapper";
    private static String targetProjectSql = "cbs-biz/src/main/resources/";
    private static String sqlmapperPack = "com.jaagro.cbs.biz.mapper";

    //Repository 存放的路径和包名
    private static String targetRepository = "cbs-biz";
    private static String repositoryPack = "com.jaagro.cbs.biz.repository";

    // 远程接口Facade 的路径和包名
    private static String targetProjectRpcApi = "cbs-api";
    private static String rpcPack = "com.jaagro.cbs.api.service";

    // 远程接口FacadeImpl 实现类 的路径和包名
    private static String targetProjectRpcService = "cbs-biz";
    private static String rpcServerPack = "com.jaagro.cbs.biz.service.impl";

    //数据库名
    private static String DATABASE = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.database");

    //需要生成的数据表前缀
    private static String TABLE_PREFIX = "";

    //创建人
    private static String author = "tonyZheng";

    //数据库链接信息
    private static String JDBC_DRIVER = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.driver");
    private static String JDBC_URL = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.url");
    private static String JDBC_USERNAME = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.username");
    private static String JDBC_PASSWORD = PropertiesFileUtil.getInstance("generator").get("generator.jdbc.password");

    /**
     *
     * 需要insert后返回主键的表配置，key:表名,value:主键名
     * 如果主键默认是id 则不需要特殊配置，若主键名非 id，则需要如下配置
     */
    private static Map<String, String> LAST_INSERT_ID_TABLES = new HashMap<>();

    static {
        //TODO ..
//        LAST_INSERT_ID_TABLES.put("permission", "permission_number");
    }

    /**
     * 自动代码生成
     *
     * @param args
     */
    public static void main(String[] args) throws Exception {

        MybatisGeneratorUtil.generator(
                null,
                true,//是否生成Facade 层
                targetProjectDao, targetProjectModel, targetProjectSql, targetProjectRpcApi, targetProjectRpcService,
                targetRepository, modelPack, mapperPack, repositoryPack, sqlmapperPack, rpcPack, rpcServerPack,
                JDBC_DRIVER, JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD,
                DATABASE, TABLE_PREFIX, LAST_INSERT_ID_TABLES, author
        );
    }

}