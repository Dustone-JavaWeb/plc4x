/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package model

import (
	"github.com/apache/plc4x/plc4go/internal/plc4go/spi/utils"
	"github.com/pkg/errors"
)

// Code generated by code-generation. DO NOT EDIT.

type ModbusDeviceInformationLevel uint8

type IModbusDeviceInformationLevel interface {
	Serialize(writeBuffer utils.WriteBuffer) error
}

const (
	ModbusDeviceInformationLevel_BASIC      ModbusDeviceInformationLevel = 0x01
	ModbusDeviceInformationLevel_REGULAR    ModbusDeviceInformationLevel = 0x02
	ModbusDeviceInformationLevel_EXTENDED   ModbusDeviceInformationLevel = 0x03
	ModbusDeviceInformationLevel_INDIVIDUAL ModbusDeviceInformationLevel = 0x04
)

var ModbusDeviceInformationLevelValues []ModbusDeviceInformationLevel

func init() {
	_ = errors.New
	ModbusDeviceInformationLevelValues = []ModbusDeviceInformationLevel{
		ModbusDeviceInformationLevel_BASIC,
		ModbusDeviceInformationLevel_REGULAR,
		ModbusDeviceInformationLevel_EXTENDED,
		ModbusDeviceInformationLevel_INDIVIDUAL,
	}
}

func ModbusDeviceInformationLevelByValue(value uint8) ModbusDeviceInformationLevel {
	switch value {
	case 0x01:
		return ModbusDeviceInformationLevel_BASIC
	case 0x02:
		return ModbusDeviceInformationLevel_REGULAR
	case 0x03:
		return ModbusDeviceInformationLevel_EXTENDED
	case 0x04:
		return ModbusDeviceInformationLevel_INDIVIDUAL
	}
	return 0
}

func ModbusDeviceInformationLevelByName(value string) ModbusDeviceInformationLevel {
	switch value {
	case "BASIC":
		return ModbusDeviceInformationLevel_BASIC
	case "REGULAR":
		return ModbusDeviceInformationLevel_REGULAR
	case "EXTENDED":
		return ModbusDeviceInformationLevel_EXTENDED
	case "INDIVIDUAL":
		return ModbusDeviceInformationLevel_INDIVIDUAL
	}
	return 0
}

func ModbusDeviceInformationLevelKnows(value uint8) bool {
	for _, typeValue := range ModbusDeviceInformationLevelValues {
		if uint8(typeValue) == value {
			return true
		}
	}
	return false
}

func CastModbusDeviceInformationLevel(structType interface{}) ModbusDeviceInformationLevel {
	castFunc := func(typ interface{}) ModbusDeviceInformationLevel {
		if sModbusDeviceInformationLevel, ok := typ.(ModbusDeviceInformationLevel); ok {
			return sModbusDeviceInformationLevel
		}
		return 0
	}
	return castFunc(structType)
}

func (m ModbusDeviceInformationLevel) GetLengthInBits() uint16 {
	return 8
}

func (m ModbusDeviceInformationLevel) GetLengthInBytes() uint16 {
	return m.GetLengthInBits() / 8
}

func ModbusDeviceInformationLevelParse(readBuffer utils.ReadBuffer) (ModbusDeviceInformationLevel, error) {
	val, err := readBuffer.ReadUint8("ModbusDeviceInformationLevel", 8)
	if err != nil {
		return 0, nil
	}
	return ModbusDeviceInformationLevelByValue(val), nil
}

func (e ModbusDeviceInformationLevel) Serialize(writeBuffer utils.WriteBuffer) error {
	return writeBuffer.WriteUint8("ModbusDeviceInformationLevel", 8, uint8(e), utils.WithAdditionalStringRepresentation(e.name()))
}

func (e ModbusDeviceInformationLevel) name() string {
	switch e {
	case ModbusDeviceInformationLevel_BASIC:
		return "BASIC"
	case ModbusDeviceInformationLevel_REGULAR:
		return "REGULAR"
	case ModbusDeviceInformationLevel_EXTENDED:
		return "EXTENDED"
	case ModbusDeviceInformationLevel_INDIVIDUAL:
		return "INDIVIDUAL"
	}
	return ""
}

func (e ModbusDeviceInformationLevel) String() string {
	return e.name()
}
