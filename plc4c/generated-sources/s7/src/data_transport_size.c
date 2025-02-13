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

#include <string.h>
#include <plc4c/driver_s7_static.h>

#include "data_transport_size.h"

// Code generated by code-generation. DO NOT EDIT.


// Create an empty NULL-struct
static const plc4c_s7_read_write_data_transport_size plc4c_s7_read_write_data_transport_size_null_const;

plc4c_s7_read_write_data_transport_size plc4c_s7_read_write_data_transport_size_null() {
  return plc4c_s7_read_write_data_transport_size_null_const;
}

// Parse function.
plc4c_return_code plc4c_s7_read_write_data_transport_size_parse(plc4c_spi_read_buffer* readBuffer, plc4c_s7_read_write_data_transport_size* _message) {
    plc4c_return_code _res = OK;

    _res = plc4c_spi_read_unsigned_byte(readBuffer, 8, (uint8_t*) _message);

    return _res;
}

plc4c_return_code plc4c_s7_read_write_data_transport_size_serialize(plc4c_spi_write_buffer* writeBuffer, plc4c_s7_read_write_data_transport_size* _message) {
    plc4c_return_code _res = OK;

    _res = plc4c_spi_write_unsigned_byte(writeBuffer, 8, *_message);

    return _res;
}

plc4c_s7_read_write_data_transport_size plc4c_s7_read_write_data_transport_size_value_of(char* value_string) {
    if(strcmp(value_string, "NULL") == 0) {
        return plc4c_s7_read_write_data_transport_size_NULL;
    }
    if(strcmp(value_string, "BIT") == 0) {
        return plc4c_s7_read_write_data_transport_size_BIT;
    }
    if(strcmp(value_string, "BYTE_WORD_DWORD") == 0) {
        return plc4c_s7_read_write_data_transport_size_BYTE_WORD_DWORD;
    }
    if(strcmp(value_string, "INTEGER") == 0) {
        return plc4c_s7_read_write_data_transport_size_INTEGER;
    }
    if(strcmp(value_string, "DINTEGER") == 0) {
        return plc4c_s7_read_write_data_transport_size_DINTEGER;
    }
    if(strcmp(value_string, "REAL") == 0) {
        return plc4c_s7_read_write_data_transport_size_REAL;
    }
    if(strcmp(value_string, "OCTET_STRING") == 0) {
        return plc4c_s7_read_write_data_transport_size_OCTET_STRING;
    }
    return -1;
}

int plc4c_s7_read_write_data_transport_size_num_values() {
  return 7;
}

plc4c_s7_read_write_data_transport_size plc4c_s7_read_write_data_transport_size_value_for_index(int index) {
    switch(index) {
      case 0: {
        return plc4c_s7_read_write_data_transport_size_NULL;
      }
      case 1: {
        return plc4c_s7_read_write_data_transport_size_BIT;
      }
      case 2: {
        return plc4c_s7_read_write_data_transport_size_BYTE_WORD_DWORD;
      }
      case 3: {
        return plc4c_s7_read_write_data_transport_size_INTEGER;
      }
      case 4: {
        return plc4c_s7_read_write_data_transport_size_DINTEGER;
      }
      case 5: {
        return plc4c_s7_read_write_data_transport_size_REAL;
      }
      case 6: {
        return plc4c_s7_read_write_data_transport_size_OCTET_STRING;
      }
      default: {
        return -1;
      }
    }
}

bool plc4c_s7_read_write_data_transport_size_get_size_in_bits(plc4c_s7_read_write_data_transport_size value) {
  switch(value) {
    case plc4c_s7_read_write_data_transport_size_NULL: { /* '0x00' */
      return false;
    }
    case plc4c_s7_read_write_data_transport_size_BIT: { /* '0x03' */
      return true;
    }
    case plc4c_s7_read_write_data_transport_size_BYTE_WORD_DWORD: { /* '0x04' */
      return true;
    }
    case plc4c_s7_read_write_data_transport_size_INTEGER: { /* '0x05' */
      return true;
    }
    case plc4c_s7_read_write_data_transport_size_DINTEGER: { /* '0x06' */
      return false;
    }
    case plc4c_s7_read_write_data_transport_size_REAL: { /* '0x07' */
      return false;
    }
    case plc4c_s7_read_write_data_transport_size_OCTET_STRING: { /* '0x09' */
      return false;
    }
    default: {
      return 0;
    }
  }
}

plc4c_s7_read_write_data_transport_size plc4c_s7_read_write_data_transport_size_get_first_enum_for_field_size_in_bits(bool value) {
        if (!value) {
            return plc4c_s7_read_write_data_transport_size_NULL;
        } else {
            return plc4c_s7_read_write_data_transport_size_BIT;
        }
}

uint16_t plc4c_s7_read_write_data_transport_size_length_in_bytes(plc4c_s7_read_write_data_transport_size* _message) {
    return plc4c_s7_read_write_data_transport_size_length_in_bits(_message) / 8;
}

uint16_t plc4c_s7_read_write_data_transport_size_length_in_bits(plc4c_s7_read_write_data_transport_size* _message) {
    return 8;
}
