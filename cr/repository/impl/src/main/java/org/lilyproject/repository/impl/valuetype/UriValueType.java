/*
 * Copyright 2010 Outerthought bvba
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.lilyproject.repository.impl.valuetype;

import java.net.URI;
import java.util.Comparator;

import org.lilyproject.bytes.api.DataInput;
import org.lilyproject.bytes.api.DataOutput;
import org.lilyproject.repository.api.IdentityRecordStack;
import org.lilyproject.repository.api.ValueType;
import org.lilyproject.repository.api.ValueTypeFactory;

public class UriValueType extends AbstractValueType implements ValueType {

    public final static String NAME = "URI";

    @Override
    public String getBaseName() {
        return NAME;
    }

    @Override
    public ValueType getDeepestValueType() {
        return this;
    }

    @Override
    public URI read(DataInput dataInput) {
        return URI.create(dataInput.readUTF());
    }

    @Override
    public void write(Object value, DataOutput dataOutput, IdentityRecordStack parentRecords) {
        dataOutput.writeUTF(((URI)value).toString());
    }

    @Override
    public Class getType() {
        return Long.class;
    }

    @Override
    public Comparator getComparator() {
        return null;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + NAME.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        return true;
    }

    //
    // Factory
    //
    public static ValueTypeFactory factory() {
        return new UriValueTypeFactory();
    }

    public static class UriValueTypeFactory implements ValueTypeFactory {
        private static UriValueType instance = new UriValueType();

        @Override
        public ValueType getValueType(String typeParams) {
            return instance;
        }
    }
}
