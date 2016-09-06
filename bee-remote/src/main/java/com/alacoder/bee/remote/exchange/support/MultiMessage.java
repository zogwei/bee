/**
 * 版权声明：bee 版权所有 违者必究 2016
 * Copyright: Copyright (c) 2016 
 * 
 * @project_name: bee-remote
 * @Title: MultiMessage.java
 * @Package com.alacoder.bee.remote.exchange.support
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:14:43
 * @version V1.0
 */

package com.alacoder.bee.remote.exchange.support;

/**
 * @ClassName: MultiMessage
 * @Description: 
 * @author jimmy.zhong
 * @date 2016年7月29日 下午6:14:43
 *
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class MultiMessage implements Iterable {

    public static MultiMessage createFromCollection(Collection collection) {
        MultiMessage result = new MultiMessage();
        result.addMessages(collection);
        return result;
    }

    public static MultiMessage createFromArray(Object... args) {
        return createFromCollection(Arrays.asList(args));
    }

    public static MultiMessage create() {
        return new MultiMessage();
    }

    private final List messages = new ArrayList();

    private MultiMessage() {}

    public void addMessage(Object msg) {
        messages.add(msg);
    }

    public void addMessages(Collection collection) {
        messages.addAll(collection);
    }

    public Collection getMessages() {
        return Collections.unmodifiableCollection(messages);
    }

    public int size() {
        return messages.size();
    }

    public Object get(int index) {
        return messages.get(index);
    }

    public boolean isEmpty() {
        return messages.isEmpty();
    }

    public Collection removeMessages() {
        Collection result = Collections.unmodifiableCollection(messages);
        messages.clear();
        return result;
    }

    public Iterator iterator() {
        return messages.iterator();
    }

}
