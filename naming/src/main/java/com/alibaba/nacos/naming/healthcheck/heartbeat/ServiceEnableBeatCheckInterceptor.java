/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos.naming.healthcheck.heartbeat;

import com.alibaba.nacos.naming.core.v2.metadata.NacosNamingMetadataManager;
import com.alibaba.nacos.naming.core.v2.metadata.ServiceMetadata;
import com.alibaba.nacos.naming.misc.UtilsAndCommons;
import com.alibaba.nacos.sys.utils.ApplicationUtils;

import java.util.Optional;

/**
 * Service enable beat check interceptor.
 *
 * @author xiweng.yy
 */
public class ServiceEnableBeatCheckInterceptor extends AbstractBeatCheckInterceptor {
    
    @Override
    public boolean intercept(InstanceBeatCheckTask object) {
        NacosNamingMetadataManager metadataManager = ApplicationUtils.getBean(NacosNamingMetadataManager.class);
        Optional<ServiceMetadata> metadata = metadataManager.getServiceMetadata(object.getService());
        if (metadata.isPresent() && metadata.get().getExtendData().containsKey(UtilsAndCommons.ENABLE_CLIENT_BEAT)) {
            return Boolean.parseBoolean(metadata.get().getExtendData().get(UtilsAndCommons.ENABLE_CLIENT_BEAT));
        }
        return false;
    }
}