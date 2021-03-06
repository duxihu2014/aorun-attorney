package com.aorun.attorney.util.biz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

/**
 * @author 杜习虎
 * @description
 * @date 2015年11月2日下午3:04:54
 */
public class ImagePropertiesConfig {
    /**
     * 读取配置文件 image.properties
     */
    protected final static Logger log = LoggerFactory.getLogger(ImagePropertiesConfig.class);

    static {

        PropertyUtil props = new PropertyUtil(System.getenv("aorun_env_config_path") + "/account/image.properties");

        CDN_SERVER_ROOT_PATH = props.getCommonConf("cdn.server_root.path");
        //FILE_SERVER_ROOT_PATH = props.getCommonConf("file.server_root.path");
        FILE_PATH = props.getCommonConf("file.path");
    }

//	/** 服务器端的资源根路径 */
//	public static String FILE_SERVER_ROOT_PATH;
    /**
     * 服务器端的资源根路径
     */
    public static String CDN_SERVER_ROOT_PATH;
    /**
     * 后台上传文件的路径
     */
    public static String FILE_PATH;



    /**
     * 申请理赔-图片--web服务路径
     */
    public static final String ADVISORY_SERVER_PATH = CDN_SERVER_ROOT_PATH + "images" + File.separator + "advisory" + File.separator;




}
