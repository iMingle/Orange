/**
 * Copyright (c) 2016, Mingle. All rights reserved.
 */
package org.mingle.orange.effectivejava;

import java.util.Map;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

/**
 * 服务提供者框架
 * 
 * @since 1.0
 * @author Mingle
 */
public class ServiceProviderFramework {}

interface Service {
    void service();
}

interface Provider {
    Service newService();
}

class Services {
    private Services() {}    // 阻止实例化
    
    // Maps service name to services
    private static final Map<String, Provider> providers = new ConcurrentHashMap<String, Provider>();
    public static final String DEFAULT_PROVIDER_NAME = "<def>";
    
    // Provider registration API
    public static void registerDefaultProvider(Provider p) {
        registerProvider(DEFAULT_PROVIDER_NAME, p);
    }
    
    public static void registerProvider(String name, Provider p) {
        providers.put(name, p);
    }
    
    // Service access API
    public static Service newInstance() {
        return newInstance(DEFAULT_PROVIDER_NAME);
    }
    
    public static Service newInstance(String name) {
        Provider p = providers.get(name);
        if (p == null)
            throw new IllegalArgumentException("No provider registered with name: " + name);
        return p.newService();
    }
}
