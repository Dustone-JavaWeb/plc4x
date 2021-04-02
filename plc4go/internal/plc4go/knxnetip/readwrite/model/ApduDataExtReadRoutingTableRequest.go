//
// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//      http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
//
package model

import (
	"encoding/xml"
	"github.com/apache/plc4x/plc4go/internal/plc4go/spi/utils"
	"io"
)

// Code generated by build-utils. DO NOT EDIT.

// The data-structure of this message
type ApduDataExtReadRoutingTableRequest struct {
	Parent *ApduDataExt
}

// The corresponding interface
type IApduDataExtReadRoutingTableRequest interface {
	LengthInBytes() uint16
	LengthInBits() uint16
	Serialize(io utils.WriteBuffer) error
	xml.Marshaler
}

///////////////////////////////////////////////////////////
// Accessors for discriminator values.
///////////////////////////////////////////////////////////
func (m *ApduDataExtReadRoutingTableRequest) ExtApciType() uint8 {
	return 0x01
}

func (m *ApduDataExtReadRoutingTableRequest) InitializeParent(parent *ApduDataExt) {
}

func NewApduDataExtReadRoutingTableRequest() *ApduDataExt {
	child := &ApduDataExtReadRoutingTableRequest{
		Parent: NewApduDataExt(),
	}
	child.Parent.Child = child
	return child.Parent
}

func CastApduDataExtReadRoutingTableRequest(structType interface{}) *ApduDataExtReadRoutingTableRequest {
	castFunc := func(typ interface{}) *ApduDataExtReadRoutingTableRequest {
		if casted, ok := typ.(ApduDataExtReadRoutingTableRequest); ok {
			return &casted
		}
		if casted, ok := typ.(*ApduDataExtReadRoutingTableRequest); ok {
			return casted
		}
		if casted, ok := typ.(ApduDataExt); ok {
			return CastApduDataExtReadRoutingTableRequest(casted.Child)
		}
		if casted, ok := typ.(*ApduDataExt); ok {
			return CastApduDataExtReadRoutingTableRequest(casted.Child)
		}
		return nil
	}
	return castFunc(structType)
}

func (m *ApduDataExtReadRoutingTableRequest) GetTypeName() string {
	return "ApduDataExtReadRoutingTableRequest"
}

func (m *ApduDataExtReadRoutingTableRequest) LengthInBits() uint16 {
	lengthInBits := uint16(0)

	return lengthInBits
}

func (m *ApduDataExtReadRoutingTableRequest) LengthInBytes() uint16 {
	return m.LengthInBits() / 8
}

func ApduDataExtReadRoutingTableRequestParse(io *utils.ReadBuffer) (*ApduDataExt, error) {

	// Create a partially initialized instance
	_child := &ApduDataExtReadRoutingTableRequest{
		Parent: &ApduDataExt{},
	}
	_child.Parent.Child = _child
	return _child.Parent, nil
}

func (m *ApduDataExtReadRoutingTableRequest) Serialize(io utils.WriteBuffer) error {
	ser := func() error {

		return nil
	}
	return m.Parent.SerializeParent(io, m, ser)
}

func (m *ApduDataExtReadRoutingTableRequest) UnmarshalXML(d *xml.Decoder, start xml.StartElement) error {
	var token xml.Token
	var err error
	token = start
	for {
		switch token.(type) {
		case xml.StartElement:
			tok := token.(xml.StartElement)
			switch tok.Name.Local {
			}
		}
		token, err = d.Token()
		if err != nil {
			if err == io.EOF {
				return nil
			}
			return err
		}
	}
}

func (m *ApduDataExtReadRoutingTableRequest) MarshalXML(e *xml.Encoder, start xml.StartElement) error {
	return nil
}

func (m ApduDataExtReadRoutingTableRequest) String() string {
	return string(m.Box("ApduDataExtReadRoutingTableRequest", utils.DefaultWidth*2))
}

func (m ApduDataExtReadRoutingTableRequest) Box(name string, width int) utils.AsciiBox {
	if name == "" {
		name = "ApduDataExtReadRoutingTableRequest"
	}
	boxes := make([]utils.AsciiBox, 0)
	return utils.BoxBox(name, utils.AlignBoxes(boxes, width-2), 0)
}
