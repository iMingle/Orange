/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.HashMap;
import java.util.Map;

/**
 * 优先考虑类型安全的异构容器
 * 
 * @since 1.8
 * @author Mingle
 */
public class Favorites {
    private Map<Class<?>, Object> favorites = new HashMap<>();
    
    public <T> void putFavorites(Class<T> type, T instance) {
        if (type == null)
            throw new NullPointerException("Type is null");
        favorites.put(type, type.cast(instance));
    }
    
    public <T> T getFavorites(Class<T> type) {
        return type.cast(favorites.get(type));
    }
}
