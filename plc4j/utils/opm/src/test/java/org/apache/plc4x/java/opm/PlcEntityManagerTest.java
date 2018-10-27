/*
 Licensed to the Apache Software Foundation (ASF) under one
 or more contributor license agreements.  See the NOTICE file
 distributed with this work for additional information
 regarding copyright ownership.  The ASF licenses this file
 to you under the Apache License, Version 2.0 (the
 "License"); you may not use this file except in compliance
 with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing,
 software distributed under the License is distributed on an
 "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 KIND, either express or implied.  See the License for the
 specific language governing permissions and limitations
 under the License.
 */

package org.apache.plc4x.java.opm;

import org.apache.commons.lang3.tuple.Pair;
import org.apache.plc4x.java.PlcDriverManager;
import org.apache.plc4x.java.api.PlcConnection;
import org.apache.plc4x.java.api.exceptions.PlcConnectionException;
import org.apache.plc4x.java.api.exceptions.PlcInvalidFieldException;
import org.apache.plc4x.java.api.metadata.PlcConnectionMetadata;
import org.apache.plc4x.java.api.types.PlcResponseCode;
import org.apache.plc4x.java.base.connection.PlcFieldHandler;
import org.apache.plc4x.java.base.messages.DefaultPlcReadRequest;
import org.apache.plc4x.java.base.messages.DefaultPlcReadResponse;
import org.apache.plc4x.java.base.messages.InternalPlcReadRequest;
import org.apache.plc4x.java.base.messages.PlcReader;
import org.apache.plc4x.java.base.messages.items.*;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;


public class PlcEntityManagerTest {

    private PlcDriverManager driverManager;

    @Test(expected = IllegalArgumentException.class)
    public void noEntity_throws() throws OPMException {
        PlcEntityManager manager = new PlcEntityManager();

        manager.read(NoEntity.class, "s7://localhost:5555/0/0");
    }

    @Test(expected = IllegalArgumentException.class)
    public void noValidConstructor_throws() throws OPMException {
        PlcEntityManager manager = new PlcEntityManager();

        manager.read(EntityWithBadConstructor.class, "s7://localhost:5555/0/0");
    }

    @Test
    public void read() throws OPMException, PlcConnectionException {
        Map<String, BaseDefaultFieldItem> results = new HashMap<>();
        String prefix = MyEntity.class.getName() + ".";
        results.put(prefix + "counter", new DefaultIntegerFieldItem(1));
        results.put(prefix + "counter2", new DefaultLongFieldItem(1l));
        PlcEntityManager manager = getPlcEntityManager(results);

        MyEntity myEntity = manager.read(MyEntity.class, "s7://localhost:5555/0/0");

        assertEquals(1, (long) myEntity.getCounter());
        assertEquals(1, myEntity.getCounter2());
    }

    @Test
    public void readComplexObject() throws PlcConnectionException, OPMException {
        Map<String, BaseDefaultFieldItem> map = new HashMap<>();
        String prefix = ConnectedEntity.class.getName() + ".";
        map.put(prefix + "boolVar", new DefaultBooleanFieldItem(true));
        map.put(prefix + "byteVar", new DefaultByteFieldItem((byte) 1));
        map.put(prefix + "shortVar", new DefaultShortFieldItem((short) 1));
        map.put(prefix + "intVar", new DefaultIntegerFieldItem(1));
        map.put(prefix + "longVar", new DefaultLongFieldItem(1l));
        map.put(prefix + "boxedBoolVar", new DefaultLongFieldItem(1L));
        map.put(prefix + "boxedByteVar", new DefaultByteFieldItem((byte) 1));
        map.put(prefix + "boxedShortVar", new DefaultShortFieldItem((short) 1));
        map.put(prefix + "boxedIntegerVar", new DefaultIntegerFieldItem(1));
        map.put(prefix + "boxedLongVar", new DefaultLongFieldItem(1l));
        map.put(prefix + "bigIntegerVar", new DefaultBigIntegerFieldItem(BigInteger.ONE));
        map.put(prefix + "floatVar", new DefaultFloatFieldItem(1f));
        map.put(prefix + "doubleVar", new DefaultDoubleFieldItem(1d));
        map.put(prefix + "bigDecimalVar", new DefaultBigDecimalFieldItem(BigDecimal.ONE));
        map.put(prefix + "localTimeVar", new DefaultLocalTimeFieldItem(LocalTime.of(1, 1)));
        map.put(prefix + "localDateVar", new DefaultLocalDateFieldItem(LocalDate.of(1, 1, 1)));
        map.put(prefix + "localDateTimeVar", new DefaultLocalDateTimeFieldItem(LocalDateTime.of(1, 1, 1, 1, 1)));
        map.put(prefix + "byteArrayVar", new DefaultByteArrayFieldItem(new Byte[]{0x0, 0x1}));
        map.put(prefix + "bigByteArrayVar", new DefaultByteArrayFieldItem(new Byte[]{0x0, 0x1}));
        map.put(prefix + "stringVar", new DefaultStringFieldItem("Hallo"));
        PlcEntityManager manager = getPlcEntityManager(map);

        ConnectedEntity connect = manager.read(ConnectedEntity.class, "s7://localhost:5555/0/0");

        Assert.assertNotNull(connect);

        // Call different mehtod
        String s = connect.toString();

        assertEquals("ConnectedEntity{boolVar=true, byteVar=1, shortVar=1, intVar=1, longVar=1, boxedBoolVar=true, boxedByteVar=1, boxedShortVar=1, boxedIntegerVar=1, boxedLongVar=1, bigIntegerVar=1, floatVar=1.0, doubleVar=1.0, bigDecimalVar=1, localTimeVar=01:01, localDateVar=0001-01-01, localDateTimeVar=0001-01-01T01:01, byteArrayVar=[0, 1], bigByteArrayVar=[0, 1], stringVar='Hallo'}", s);
    }

    @Test
    public void connect_callComplexMethod() throws PlcConnectionException, OPMException {
        Map<String, BaseDefaultFieldItem> map = new HashMap<>();
        String prefix = ConnectedEntity.class.getName() + ".";
        map.put(prefix + "boolVar", new DefaultBooleanFieldItem(true));
        map.put(prefix + "byteVar", new DefaultByteFieldItem((byte) 1));
        map.put(prefix + "shortVar", new DefaultShortFieldItem((short) 1));
        map.put(prefix + "intVar", new DefaultIntegerFieldItem(1));
        map.put(prefix + "longVar", new DefaultLongFieldItem(1l));
        map.put(prefix + "boxedBoolVar", new DefaultLongFieldItem(1L));
        map.put(prefix + "boxedByteVar", new DefaultByteFieldItem((byte) 1));
        map.put(prefix + "boxedShortVar", new DefaultShortFieldItem((short) 1));
        map.put(prefix + "boxedIntegerVar", new DefaultIntegerFieldItem(1));
        map.put(prefix + "boxedLongVar", new DefaultLongFieldItem(1l));
        map.put(prefix + "bigIntegerVar", new DefaultBigIntegerFieldItem(BigInteger.ONE));
        map.put(prefix + "floatVar", new DefaultFloatFieldItem(1f));
        map.put(prefix + "doubleVar", new DefaultDoubleFieldItem(1d));
        map.put(prefix + "bigDecimalVar", new DefaultBigDecimalFieldItem(BigDecimal.ONE));
        map.put(prefix + "localTimeVar", new DefaultLocalTimeFieldItem(LocalTime.of(1, 1)));
        map.put(prefix + "localDateVar", new DefaultLocalDateFieldItem(LocalDate.of(1, 1, 1)));
        map.put(prefix + "localDateTimeVar", new DefaultLocalDateTimeFieldItem(LocalDateTime.of(1, 1, 1, 1, 1)));
        map.put(prefix + "byteArrayVar", new DefaultByteArrayFieldItem(new Byte[]{0x0, 0x1}));
        map.put(prefix + "bigByteArrayVar", new DefaultByteArrayFieldItem(new Byte[]{0x0, 0x1}));
        map.put(prefix + "stringVar", new DefaultStringFieldItem("Hallo"));
        PlcEntityManager manager = getPlcEntityManager(map);

        ConnectedEntity connect = manager.connect(ConnectedEntity.class, "s7://localhost:5555/0/0");

        Assert.assertNotNull(connect);

        // Call different mehtod
        String s = connect.toString();

        assertEquals("ConnectedEntity{boolVar=true, byteVar=1, shortVar=1, intVar=1, longVar=1, boxedBoolVar=true, boxedByteVar=1, boxedShortVar=1, boxedIntegerVar=1, boxedLongVar=1, bigIntegerVar=1, floatVar=1.0, doubleVar=1.0, bigDecimalVar=1, localTimeVar=01:01, localDateVar=0001-01-01, localDateTimeVar=0001-01-01T01:01, byteArrayVar=[0, 1], bigByteArrayVar=[0, 1], stringVar='Hallo'}", s);
    }

    @Test
    public void connect_callGetter() throws PlcConnectionException, OPMException {
        Map<String, BaseDefaultFieldItem> map = new HashMap<>();
        map.put("getIntVar", new DefaultIntegerFieldItem(1));
        map.put("getStringVar", new DefaultStringFieldItem("Hello"));
        map.put("isBoolVar", new DefaultBooleanFieldItem(true));
        PlcEntityManager manager = getPlcEntityManager(map);

        ConnectedEntity connect = manager.connect(ConnectedEntity.class, "s7://localhost:5555/0/0");

        Assert.assertNotNull(connect);

        // Call getter
        assertEquals(1, connect.getIntVar());
        assertEquals("Hello", connect.getStringVar());
        assertEquals(true, connect.isBoolVar());
    }

    private PlcEntityManager getPlcEntityManager(final Map<String, BaseDefaultFieldItem> responses) throws PlcConnectionException {
        driverManager = Mockito.mock(PlcDriverManager.class);
        PlcDriverManager mock = driverManager;
        PlcConnection connection = Mockito.mock(PlcConnection.class);
        when(mock.getConnection(ArgumentMatchers.anyString())).thenReturn(connection);
        when(connection.getMetadata()).thenReturn(new PlcConnectionMetadata() {

            @Override
            public boolean canRead() {
                return true;
            }

            @Override
            public boolean canWrite() {
                return true;
            }

            @Override
            public boolean canSubscribe() {
                return true;
            }
        });

        PlcReader reader = readRequest -> {
            Map<String, Pair<PlcResponseCode, BaseDefaultFieldItem>> map = readRequest.getFieldNames().stream()
                .collect(Collectors.toMap(
                    Function.identity(),
                    s -> Pair.of(PlcResponseCode.OK, Objects.requireNonNull(responses.get(s), s + " not found"))
                ));
            return CompletableFuture.completedFuture(new DefaultPlcReadResponse((InternalPlcReadRequest) readRequest, map));
        };
        when(connection.readRequestBuilder()).then(invocation -> new DefaultPlcReadRequest.Builder(reader, getFieldHandler()));

        return new PlcEntityManager(mock);
    }

    private PlcFieldHandler getFieldHandler() {
        return new NoOpPlcFieldHandler();
    }

    private static class NoOpPlcFieldHandler implements PlcFieldHandler {
        @Override
        public org.apache.plc4x.java.api.model.PlcField createField(String fieldQuery) throws PlcInvalidFieldException {
            return new org.apache.plc4x.java.api.model.PlcField() {
            };
        }

        @Override
        public BaseDefaultFieldItem encodeBoolean(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeByte(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeShort(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeInteger(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeBigInteger(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeLong(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeFloat(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeBigDecimal(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeDouble(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeString(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeTime(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeDate(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeDateTime(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }

        @Override
        public BaseDefaultFieldItem encodeByteArray(org.apache.plc4x.java.api.model.PlcField field, Object[] values) {
            return null;
        }
    }

    private static class NoEntity {

    }

    @PlcEntity()
    public static class EntityWithBadConstructor {

        @PlcField("asdf")
        private long field;

        public EntityWithBadConstructor(long field) {
            this.field = field;
        }

        public long getField() {
            return field;
        }
    }

    @PlcEntity()
    public static class MyEntity {

        @PlcField("%DB3.DBW500")
        private Long counter;

        @PlcField("%DB3.DBW504")
        private long counter2;

        public Long getCounter() {
            return counter;
        }

        public long getCounter2() {
            return counter2;
        }

    }

    @PlcEntity()
    public static class ConnectedEntity {

        @PlcField("%DB1.DW111:BOOL")
        private boolean boolVar;
        @PlcField("%DB1.DW111:BYTE")
        private byte byteVar;
        @PlcField("%DB1.DW111:SHORT")
        private short shortVar;
        @PlcField("%DB1.DW111:INT")
        private int intVar;
        @PlcField("%DB1.DW111:LONG")
        private long longVar;
        @PlcField("%DB1.DW111:BOOL")
        private Boolean boxedBoolVar;
        @PlcField("%DB1.DW111:BYTE")
        private Byte boxedByteVar;
        @PlcField("%DB1.DW111:SHORT")
        private Short boxedShortVar;
        @PlcField("%DB1.DW111:SHORT")
        private Integer boxedIntegerVar;
        @PlcField("%DB1.DW111:LONG")
        private Long boxedLongVar;
        @PlcField("%DB1.DW111:BIGINT")
        private BigInteger bigIntegerVar;
        @PlcField("%DB1.DW111:FLOAT")
        private Float floatVar;
        @PlcField("%DB1.DW111:DOUBLE")
        private Double doubleVar;
        @PlcField("%DB1.DW111:BIGDECIMAL")
        private BigDecimal bigDecimalVar;
        @PlcField("%DB1.DW111:LOCALTIME")
        private LocalTime localTimeVar;
        @PlcField("%DB1.DW111:LOCALDATE")
        private LocalDate localDateVar;
        @PlcField("%DB1.DW111:LOCALDATETIME")
        private LocalDateTime localDateTimeVar;
        @PlcField("%DB1.DW111:BYTEARRAY")
        private byte[] byteArrayVar;
        @PlcField("%DB1.DW111:BYTEARRAY")
        private Byte[] bigByteArrayVar;

        @PlcField("%DB1.DW111:STRING")
        private String stringVar;

        public ConnectedEntity() {
            // Default
        }

        public boolean isBoolVar() {
            return boolVar;
        }

        public byte getByteVar() {
            return byteVar;
        }


        public short getShortVar() {
            return shortVar;
        }


        public int getIntVar() {
            return intVar;
        }


        public long getLongVar() {
            return longVar;
        }


        public String getStringVar() {
            return stringVar;
        }

        public void someMethod() {
            System.out.println("I do nothing");
        }

        @Override
        public String toString() {
            return "ConnectedEntity{" +
                "boolVar=" + boolVar +
                ", byteVar=" + byteVar +
                ", shortVar=" + shortVar +
                ", intVar=" + intVar +
                ", longVar=" + longVar +
                ", boxedBoolVar=" + boxedBoolVar +
                ", boxedByteVar=" + boxedByteVar +
                ", boxedShortVar=" + boxedShortVar +
                ", boxedIntegerVar=" + boxedIntegerVar +
                ", boxedLongVar=" + boxedLongVar +
                ", bigIntegerVar=" + bigIntegerVar +
                ", floatVar=" + floatVar +
                ", doubleVar=" + doubleVar +
                ", bigDecimalVar=" + bigDecimalVar +
                ", localTimeVar=" + localTimeVar +
                ", localDateVar=" + localDateVar +
                ", localDateTimeVar=" + localDateTimeVar +
                ", byteArrayVar=" + Arrays.toString(byteArrayVar) +
                ", bigByteArrayVar=" + Arrays.toString(bigByteArrayVar) +
                ", stringVar='" + stringVar + '\'' +
                '}';
        }
    }
}