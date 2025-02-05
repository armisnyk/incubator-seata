/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.seata.spring.boot.autoconfigure.properties.client;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import static io.seata.common.DefaultValues.DEFAULT_DISABLE_GLOBAL_TRANSACTION;
import static io.seata.common.DefaultValues.DEFAULT_GROUPLIST;
import static io.seata.common.DefaultValues.DEFAULT_TC_CLUSTER;
import static io.seata.common.DefaultValues.DEFAULT_TX_GROUP;
import static io.seata.common.DefaultValues.DEFAULT_TX_GROUP_OLD;
import static io.seata.spring.boot.autoconfigure.StarterConstants.SERVICE_PREFIX;

/**
 */
@Component
@ConfigurationProperties(prefix = SERVICE_PREFIX)
public class ServiceProperties implements InitializingBean {
    /**
     * vgroup->rgroup
     */
    private Map<String, String> vgroupMapping = new HashMap<>();
    /**
     * group list
     */
    private Map<String, String> grouplist = new HashMap<>();
    /**
     * degrade current not support
     */
    private boolean enableDegrade = false;
    /**
     * disable globalTransaction
     */
    private boolean disableGlobalTransaction = DEFAULT_DISABLE_GLOBAL_TRANSACTION;

    public Map<String, String> getVgroupMapping() {
        return vgroupMapping;
    }

    public void setVgroupMapping(Map<String, String> vgroupMapping) {
        this.vgroupMapping = vgroupMapping;
    }

    public Map<String, String> getGrouplist() {
        return grouplist;
    }

    public void setGrouplist(Map<String, String> grouplist) {
        this.grouplist = grouplist;
    }

    public boolean isEnableDegrade() {
        return enableDegrade;
    }

    public ServiceProperties setEnableDegrade(boolean enableDegrade) {
        this.enableDegrade = enableDegrade;
        return this;
    }

    public boolean isDisableGlobalTransaction() {
        return disableGlobalTransaction;
    }

    public ServiceProperties setDisableGlobalTransaction(boolean disableGlobalTransaction) {
        this.disableGlobalTransaction = disableGlobalTransaction;
        return this;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (0 == vgroupMapping.size()) {
            vgroupMapping.put(DEFAULT_TX_GROUP, DEFAULT_TC_CLUSTER);
            //compatible with old value, will remove next version
            vgroupMapping.put(DEFAULT_TX_GROUP_OLD, DEFAULT_TC_CLUSTER);
        }
        if (0 == grouplist.size()) {
            grouplist.put(DEFAULT_TC_CLUSTER, DEFAULT_GROUPLIST);
        }
    }
}
