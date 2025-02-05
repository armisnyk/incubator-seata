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
package io.seata.saga.proctrl.eventing;

import java.util.List;

import io.seata.common.exception.FrameworkException;

/**
 * Event bus
 *
 */
public interface EventBus<E> {

    /**
     * insert add element into bus
     *
     * @param e the event
     * @return is success
     * @throws FrameworkException the framework exception
     */
    boolean offer(E e) throws FrameworkException;

    /**
     * get event consumers
     *
     * @param clazz the event class
     * @return event consumer list
     */
    List<EventConsumer> getEventConsumers(Class<?> clazz);

    /**
     * register event consumer
     *
     * @param eventConsumer the event consumer
     */
    void registerEventConsumer(EventConsumer eventConsumer);
}
