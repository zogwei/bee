/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-config
 * @Title: AbstractConfig.java
 * @Package com.bee.config
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午2:22:08
 * @version V1.0
 */

package com.alacoder.bee.config;

import java.io.Serializable;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.alacoder.bee.common.logger.Logger;
import com.alacoder.bee.common.logger.LoggerFactory;
import com.alacoder.bee.config.annotation.Parameter;

/**
 * @ClassName: AbstractConfig
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月21日 下午2:22:08
 *
 */

public class AbstractConfig  implements Serializable {

	private static final long serialVersionUID = -6459975496546211107L;
	
    protected static final Logger logger = LoggerFactory.getLogger(AbstractConfig.class);
    
    
    private static final int MAX_LENGTH = 100;
    
    private static final int MAX_PATH_LENGTH = 200;
    
    private static final Pattern PATTERN_NAME = Pattern.compile("[\\-._0-9a-zA-Z]+");

    private static final Pattern PATTERN_NAME_HAS_SYMBOL = Pattern.compile("[:*,/\\-._0-9a-zA-Z]+");
    
    private static final Pattern PATTERN_MULTI_NAME = Pattern.compile("[,\\-._0-9a-zA-Z]+");

    private static final Pattern PATTERN_KEY = Pattern.compile("[*,\\-._0-9a-zA-Z]+");

    
    /**
     * TODO 删除了相关代码
     */
    
    protected String id;

    @Parameter(excluded = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    protected static void checkName(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_NAME);
    }
    
    protected static void checkLength(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, null);
    }
    
    protected static void checkPathLength(String property, String value) {
        checkProperty(property, value, MAX_PATH_LENGTH, null);
    }
    
    protected static void checkParameterName(Map<String, String> parameters) {
        if (parameters == null || parameters.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : parameters.entrySet()) {
            //change by tony.chenl parameter value maybe has colon.for example napoli address
            checkNameHasSymbol(entry.getKey(), entry.getValue());
        }
    }
    
    protected static void checkNameHasSymbol(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_NAME_HAS_SYMBOL);
    }

    
    protected static void checkProperty(String property, String value, int maxlength, Pattern pattern) {
        if (value == null || value.length() == 0) {
            return;
        }
        if(value.length() > maxlength){
            throw new IllegalStateException("Invalid " + property + "=\"" + value + "\" is longer than " + maxlength);
        }
        if (pattern != null) {
            Matcher matcher = pattern.matcher(value);
            if(! matcher.matches()) {
                throw new IllegalStateException("Invalid " + property + "=\"" + value + "\" contain illegal charactor, only digit, letter, '-', '_' and '.' is legal.");
            }
        }
    }
    
//    protected static void checkExtension(Class<?> type, String property, String value) {
//        checkName(property, value);
//        if (value != null && value.length() > 0 
//                && ! ExtensionLoader.getExtensionLoader(type).hasExtension(value)) {
//            throw new IllegalStateException("No such extension " + value + " for " + property + "/" + type.getName());
//        }
//    }
    
    protected static void checkMultiName(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_MULTI_NAME);
    }
    
    protected static void checkKey(String property, String value) {
        checkProperty(property, value, MAX_LENGTH, PATTERN_KEY);
    }

}
