/*
 * Copyright 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.orange.effectivejava;

import java.util.Objects;

/**
 * 慎用延迟初始化
 *
 * @author mingle
 */
public class LazyInitialization {
    private final FieldType fieldNormal = computeFieldValue();
    private FieldType fieldLazy;
    private volatile FieldType fieldVolatile;

    private static FieldType computeFieldValue() {
        return new FieldType();
    }

    synchronized FieldType getFieldLazy() {
        if (Objects.isNull(fieldLazy))
            fieldLazy = computeFieldValue();

        return fieldLazy;
    }

    static FieldType getFieldStatic() {
        return FieldHolder.field;
    }

    FieldType getFieldVolatile() {
        FieldType result = fieldVolatile;
        if (Objects.isNull(result)) {
            synchronized (this) {
                result = fieldVolatile;
                if (Objects.isNull(result))
                    fieldVolatile = result = computeFieldValue();
            }
        }

        return result;
    }

    /**
     * 可以接收重复值
     *
     * @return
     */
    FieldType getField() {
        FieldType result = fieldVolatile;
        if (Objects.isNull(result)) {
            fieldVolatile = result = computeFieldValue();
        }

        return result;
    }

    private static class FieldHolder {
        static final FieldType field = computeFieldValue();
    }

    private static class FieldType {
    }
}
