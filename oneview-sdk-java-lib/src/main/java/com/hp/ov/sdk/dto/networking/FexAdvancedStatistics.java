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

public class FexAdvancedStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    protected Received received;
    protected Transmitted transmitted;

    /**
     * @return the received
     */
    public Received getReceived() {
        return received;
    }
    /**
     * @param received the received to set
     */
    public void setReceived(Received received) {
        this.received = received;
    }
    /**
     * @return the transmitted
     */
    public Transmitted getTransmitted() {
        return transmitted;
    }
    /**
     * @param transmitted the transmitted to set
     */
    public void setTransmitted(Transmitted transmitted) {
        this.transmitted = transmitted;
    }

    public static class Received extends BaseModelResource {

        private static final long serialVersionUID = 1L;

        private String unicast_packets;
        private String multicast_packets;
        private String broadcast_packets;
        private String input_packets;
        private String jumbo_packets;
        private String storm_suppression_bytes;
        private String giants;
        private String short_frame;
        private String overrun;
        private String underrun;
        private String watchdog;
        private String if_down_drop;
        private String input_with_dribble;
        private String input_discard;
        private String Rx_pause;

        /**
         * @return the unicast_packets
         */
        public String getUnicast_packets() {
            return unicast_packets;
        }
        /**
         * @param unicast_packets the unicast_packets to set
         */
        public void setUnicast_packets(String unicast_packets) {
            this.unicast_packets = unicast_packets;
        }
        /**
         * @return the multicast_packets
         */
        public String getMulticast_packets() {
            return multicast_packets;
        }
        /**
         * @param multicast_packets the multicast_packets to set
         */
        public void setMulticast_packets(String multicast_packets) {
            this.multicast_packets = multicast_packets;
        }
        /**
         * @return the broadcast_packets
         */
        public String getBroadcast_packets() {
            return broadcast_packets;
        }
        /**
         * @param broadcast_packets the broadcast_packets to set
         */
        public void setBroadcast_packets(String broadcast_packets) {
            this.broadcast_packets = broadcast_packets;
        }
        /**
         * @return the input_packets
         */
        public String getInput_packets() {
            return input_packets;
        }
        /**
         * @param input_packets the input_packets to set
         */
        public void setInput_packets(String input_packets) {
            this.input_packets = input_packets;
        }
        /**
         * @return the jumbo_packets
         */
        public String getJumbo_packets() {
            return jumbo_packets;
        }
        /**
         * @param jumbo_packets the jumbo_packets to set
         */
        public void setJumbo_packets(String jumbo_packets) {
            this.jumbo_packets = jumbo_packets;
        }
        /**
         * @return the storm_suppression_bytes
         */
        public String getStorm_suppression_bytes() {
            return storm_suppression_bytes;
        }
        /**
         * @param storm_suppression_bytes the storm_suppression_bytes to set
         */
        public void setStorm_suppression_bytes(String storm_suppression_bytes) {
            this.storm_suppression_bytes = storm_suppression_bytes;
        }
        /**
         * @return the giants
         */
        public String getGiants() {
            return giants;
        }
        /**
         * @param giants the giants to set
         */
        public void setGiants(String giants) {
            this.giants = giants;
        }
        /**
         * @return the short_frame
         */
        public String getShort_frame() {
            return short_frame;
        }
        /**
         * @param short_frame the short_frame to set
         */
        public void setShort_frame(String short_frame) {
            this.short_frame = short_frame;
        }
        /**
         * @return the overrun
         */
        public String getOverrun() {
            return overrun;
        }
        /**
         * @param overrun the overrun to set
         */
        public void setOverrun(String overrun) {
            this.overrun = overrun;
        }
        /**
         * @return the underrun
         */
        public String getUnderrun() {
            return underrun;
        }
        /**
         * @param underrun the underrun to set
         */
        public void setUnderrun(String underrun) {
            this.underrun = underrun;
        }
        /**
         * @return the watchdog
         */
        public String getWatchdog() {
            return watchdog;
        }
        /**
         * @param watchdog the watchdog to set
         */
        public void setWatchdog(String watchdog) {
            this.watchdog = watchdog;
        }
        /**
         * @return the if_down_drop
         */
        public String getIf_down_drop() {
            return if_down_drop;
        }
        /**
         * @param if_down_drop the if_down_drop to set
         */
        public void setIf_down_drop(String if_down_drop) {
            this.if_down_drop = if_down_drop;
        }
        /**
         * @return the input_with_dribble
         */
        public String getInput_with_dribble() {
            return input_with_dribble;
        }
        /**
         * @param input_with_dribble the input_with_dribble to set
         */
        public void setInput_with_dribble(String input_with_dribble) {
            this.input_with_dribble = input_with_dribble;
        }
        /**
         * @return the input_discard
         */
        public String getInput_discard() {
            return input_discard;
        }
        /**
         * @param input_discard the input_discard to set
         */
        public void setInput_discard(String input_discard) {
            this.input_discard = input_discard;
        }
        /**
         * @return the rx_pause
         */
        public String getRx_pause() {
            return Rx_pause;
        }
        /**
         * @param rx_pause the rx_pause to set
         */
        public void setRx_pause(String rx_pause) {
            Rx_pause = rx_pause;
        }
    }

    public static class Transmitted extends BaseModelResource {

        private static final long serialVersionUID = 1L;

        private String unicast_packets;
        private String multicast_packets;
        private String broadcast_packets;
        private String output_packets;
        private String jumbo_packets;
        private String collision;
        private String deferred;
        private String late_collision;
        private String lost_carrier;
        private String no_carrier;
        private String babble;
        private String Tx_pause;
        private String interface_resets;

        /**
         * @return the unicast_packets
         */
        public String getUnicast_packets() {
            return unicast_packets;
        }
        /**
         * @param unicast_packets the unicast_packets to set
         */
        public void setUnicast_packets(String unicast_packets) {
            this.unicast_packets = unicast_packets;
        }
        /**
         * @return the multicast_packets
         */
        public String getMulticast_packets() {
            return multicast_packets;
        }
        /**
         * @param multicast_packets the multicast_packets to set
         */
        public void setMulticast_packets(String multicast_packets) {
            this.multicast_packets = multicast_packets;
        }
        /**
         * @return the broadcast_packets
         */
        public String getBroadcast_packets() {
            return broadcast_packets;
        }
        /**
         * @param broadcast_packets the broadcast_packets to set
         */
        public void setBroadcast_packets(String broadcast_packets) {
            this.broadcast_packets = broadcast_packets;
        }
        /**
         * @return the output_packets
         */
        public String getOutput_packets() {
            return output_packets;
        }
        /**
         * @param output_packets the output_packets to set
         */
        public void setOutput_packets(String output_packets) {
            this.output_packets = output_packets;
        }
        /**
         * @return the jumbo_packets
         */
        public String getJumbo_packets() {
            return jumbo_packets;
        }
        /**
         * @param jumbo_packets the jumbo_packets to set
         */
        public void setJumbo_packets(String jumbo_packets) {
            this.jumbo_packets = jumbo_packets;
        }
        /**
         * @return the collision
         */
        public String getCollision() {
            return collision;
        }
        /**
         * @param collision the collision to set
         */
        public void setCollision(String collision) {
            this.collision = collision;
        }
        /**
         * @return the deferred
         */
        public String getDeferred() {
            return deferred;
        }
        /**
         * @param deferred the deferred to set
         */
        public void setDeferred(String deferred) {
            this.deferred = deferred;
        }
        /**
         * @return the late_collision
         */
        public String getLate_collision() {
            return late_collision;
        }
        /**
         * @param late_collision the late_collision to set
         */
        public void setLate_collision(String late_collision) {
            this.late_collision = late_collision;
        }
        /**
         * @return the lost_carrier
         */
        public String getLost_carrier() {
            return lost_carrier;
        }
        /**
         * @param lost_carrier the lost_carrier to set
         */
        public void setLost_carrier(String lost_carrier) {
            this.lost_carrier = lost_carrier;
        }
        /**
         * @return the no_carrier
         */
        public String getNo_carrier() {
            return no_carrier;
        }
        /**
         * @param no_carrier the no_carrier to set
         */
        public void setNo_carrier(String no_carrier) {
            this.no_carrier = no_carrier;
        }
        /**
         * @return the babble
         */
        public String getBabble() {
            return babble;
        }
        /**
         * @param babble the babble to set
         */
        public void setBabble(String babble) {
            this.babble = babble;
        }
        /**
         * @return the tx_pause
         */
        public String getTx_pause() {
            return Tx_pause;
        }
        /**
         * @param tx_pause the tx_pause to set
         */
        public void setTx_pause(String tx_pause) {
            Tx_pause = tx_pause;
        }
        /**
         * @return the interface_resets
         */
        public String getInterface_resets() {
            return interface_resets;
        }
        /**
         * @param interface_resets the interface_resets to set
         */
        public void setInterface_resets(String interface_resets) {
            this.interface_resets = interface_resets;
        }
    }

}
