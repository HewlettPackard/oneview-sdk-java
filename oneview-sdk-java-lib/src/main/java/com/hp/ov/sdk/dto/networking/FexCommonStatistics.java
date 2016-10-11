/*******************************************************************************
 * (C) Copyright 2016 Hewlett Packard Enterprise Development LP
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.hp.ov.sdk.dto.networking;

import com.hp.ov.sdk.dto.BaseModelResource;

public class FexCommonStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private String received_errors;
    private String transmitted_errors;
    private Bit_Transfer_Rate bit_Transfer_Rate;
    private Packet_Transfer_Rate packet_Transfer_Rate;

    /**
     * @return the received_errors
     */
    public String getReceived_errors() {
        return received_errors;
    }
    /**
     * @param received_errors the received_errors to set
     */
    public void setReceived_errors(String received_errors) {
        this.received_errors = received_errors;
    }
    /**
     * @return the transmitted_errors
     */
    public String getTransmitted_errors() {
        return transmitted_errors;
    }
    /**
     * @param transmitted_errors the transmitted_errors to set
     */
    public void setTransmitted_errors(String transmitted_errors) {
        this.transmitted_errors = transmitted_errors;
    }
    /**
     * @return the bit_Transfer_Rate
     */
    public Bit_Transfer_Rate getBit_Transfer_Rate() {
        return bit_Transfer_Rate;
    }
    /**
     * @param bit_Transfer_Rate the bit_Transfer_Rate to set
     */
    public void setBit_Transfer_Rate(Bit_Transfer_Rate bit_Transfer_Rate) {
        this.bit_Transfer_Rate = bit_Transfer_Rate;
    }
    /**
     * @return the packet_Transfer_Rate
     */
    public Packet_Transfer_Rate getPacket_Transfer_Rate() {
        return packet_Transfer_Rate;
    }
    /**
     * @param packet_Transfer_Rate the packet_Transfer_Rate to set
     */
    public void setPacket_Transfer_Rate(Packet_Transfer_Rate packet_Transfer_Rate) {
        this.packet_Transfer_Rate = packet_Transfer_Rate;
    }

    public static class Bit_Transfer_Rate extends BaseModelResource {

        private static final long serialVersionUID = 1L;

        private String _30_seconds_input;
        private String _30_seconds_output;
        private String received;
        private String transmitted;

        /**
         * @return the _30_seconds_input
         */
        public String get_30_seconds_input() {
            return _30_seconds_input;
        }
        /**
         * @param _30_seconds_input the _30_seconds_input to set
         */
        public void set_30_seconds_input(String _30_seconds_input) {
            this._30_seconds_input = _30_seconds_input;
        }
        /**
         * @return the _30_seconds_output
         */
        public String get_30_seconds_output() {
            return _30_seconds_output;
        }
        /**
         * @param _30_seconds_output the _30_seconds_output to set
         */
        public void set_30_seconds_output(String _30_seconds_output) {
            this._30_seconds_output = _30_seconds_output;
        }
        /**
         * @return the received
         */
        public String getReceived() {
            return received;
        }
        /**
         * @param received the received to set
         */
        public void setReceived(String received) {
            this.received = received;
        }
        /**
         * @return the transmitted
         */
        public String getTransmitted() {
            return transmitted;
        }
        /**
         * @param transmitted the transmitted to set
         */
        public void setTransmitted(String transmitted) {
            this.transmitted = transmitted;
        }

    }

    public static class Packet_Transfer_Rate extends BaseModelResource {

        private static final long serialVersionUID = 1L;

        protected String _30_seconds_input;
        protected String _30_seconds_output;
        protected String received;
        protected String transmitted;

        /**
         * @return the _30_seconds_input
         */
        public String get_30_seconds_input() {
            return _30_seconds_input;
        }
        /**
         * @param _30_seconds_input the _30_seconds_input to set
         */
        public void set_30_seconds_input(String _30_seconds_input) {
            this._30_seconds_input = _30_seconds_input;
        }
        /**
         * @return the _30_seconds_output
         */
        public String get_30_seconds_output() {
            return _30_seconds_output;
        }
        /**
         * @param _30_seconds_output the _30_seconds_output to set
         */
        public void set_30_seconds_output(String _30_seconds_output) {
            this._30_seconds_output = _30_seconds_output;
        }
        /**
         * @return the received
         */
        public String getReceived() {
            return received;
        }
        /**
         * @param received the received to set
         */
        public void setReceived(String received) {
            this.received = received;
        }
        /**
         * @return the transmitted
         */
        public String getTransmitted() {
            return transmitted;
        }
        /**
         * @param transmitted the transmitted to set
         */
        public void setTransmitted(String transmitted) {
            this.transmitted = transmitted;
        }

    }

}
