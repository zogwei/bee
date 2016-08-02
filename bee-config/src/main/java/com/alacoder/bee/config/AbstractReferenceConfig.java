/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-config
 * @Title: AbstractReferenceConfig.java
 * @Package com.alacoder.bee.config
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月27日 下午4:13:13
 * @version V1.0
 */

package com.alacoder.bee.config;

import com.alacoder.bee.common.Constants;
import com.alacoder.bee.config.annotation.Parameter;

/**
 * @ClassName: AbstractReferenceConfig
 * @Description: TODO
 * @author jimmy.zhong
 * @date 2016年7月27日 下午4:13:13
 *
 */

public abstract class AbstractReferenceConfig extends AbstractInterfaceConfig {

    private static final long serialVersionUID = -2786526984373031126L;

    // ======== 引用缺省值，当引用属性未设置时使用该缺省值替代  ========
    
    // 检查服务提供者是否存在
    protected Boolean             check;

    // 是否加载时即刻初始化
    protected Boolean             init;

    // 是否使用泛接口
    protected String             generic;

    // 优先从JVM内获取引用实例
    protected Boolean             injvm;
    
    // lazy create connection
    protected Boolean             lazy;

    protected String              reconnect;
    
    protected Boolean             sticky;
    
    //stub是否支持event事件. //TODO slove merge problem 
    protected Boolean             stubevent ;//= Constants.DEFAULT_STUB_EVENT;

    // 版本
    protected String               version;

    // 服务分组
    protected String               group;

    public Boolean isCheck() {
        return check;
    }

    public void setCheck(Boolean check) {
        this.check = check;
    }

    public Boolean isInit() {
        return init;
    }

    public void setInit(Boolean init) {
        this.init = init;
    }

    @Parameter(excluded = true)
    public Boolean isGeneric() {
    	 return generic != null
    	            && !"".equals(generic)
    	            && (Constants.GENERIC_SERIALIZATION_DEFAULT.equalsIgnoreCase(generic)  /* 正常的泛化调用 */
    	            || Constants.GENERIC_SERIALIZATION_NATIVE_JAVA.equalsIgnoreCase(generic) /* 支持java序列化的流式泛化调用 */
    	            || Constants.GENERIC_SERIALIZATION_BEAN.equalsIgnoreCase(generic));
    }

    public void setGeneric(Boolean generic) {
        if (generic != null) {
            this.generic = generic.toString();
        }
    }

    public void setGeneric(String generic) {
        this.generic = generic;
    }

    public String getGeneric() {
        return generic;
    }

    /**
     * @return
     * @deprecated 通过scope进行判断，scope=local
     */
    @Deprecated
    public Boolean isInjvm() {
        return injvm;
    }
    
    /**
     * @param injvm
     * @deprecated 通过scope设置，scope=local表示使用injvm协议.
     */
    @Deprecated 
    public void setInjvm(Boolean injvm) {
        this.injvm = injvm;
    }

    @Parameter(key = Constants.REFERENCE_FILTER_KEY, append = true)
    public String getFilter() {
        return super.getFilter();
    }

    @Parameter(key = Constants.INVOKER_LISTENER_KEY, append = true)
    public String getListener() {
        return super.getListener();
    }

    @Override
    public void setListener(String listener) {
//        checkMultiExtension(InvokerListener.class, "listener", listener);
        super.setListener(listener);
    }

    @Parameter(key = Constants.LAZY_CONNECT_KEY)
    public Boolean getLazy() {
        return lazy;
    }

    public void setLazy(Boolean lazy) {
        this.lazy = lazy;
    }

    @Override
    public void setOnconnect(String onconnect) {
        if (onconnect != null && onconnect.length() >0){
            this.stubevent = true;
        }
        super.setOnconnect(onconnect);
    }

    @Override
    public void setOndisconnect(String ondisconnect) {
        if (ondisconnect != null && ondisconnect.length() >0){
            this.stubevent = true;
        }
        super.setOndisconnect(ondisconnect);
    }

    @Parameter(key = Constants.STUB_EVENT_KEY)
    public Boolean getStubevent() {
        return stubevent;
    }
    
    @Parameter(key = Constants.RECONNECT_KEY)
    public String getReconnect() {
        return reconnect;
    }

    public void setReconnect(String reconnect) {
        this.reconnect = reconnect;
    }

    @Parameter(key = Constants.CLUSTER_STICKY_KEY)
    public Boolean getSticky() {
        return sticky;
    }

    public void setSticky(Boolean sticky) {
        this.sticky = sticky;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        checkKey("version", version);
        this.version = version;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        checkKey("group", group);
        this.group = group;
    }

}