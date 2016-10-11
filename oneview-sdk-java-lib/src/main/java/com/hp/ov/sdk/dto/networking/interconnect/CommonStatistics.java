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
package com.hp.ov.sdk.dto.networking.interconnect;

import java.math.BigInteger;

import com.hp.ov.sdk.dto.BaseModelResource;

public class CommonStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private BigInteger portNumber;
    private BigInteger resetTimeEpoch;
    private BigInteger rfc1213IfInOctets;
    private BigInteger rfc1213IfInUcastPkts;
    private BigInteger rfc1213IfInNUcastPkts;
    private BigInteger rfc1213IfInDiscards;
    private BigInteger rfc1213IfInErrors;
    private BigInteger rfc1213IfInUnknownProtos;
    private BigInteger rfc1213IfOutOctets;
    private BigInteger rfc1213IfOutUcastPkts;
    private BigInteger rfc1213IfOutNUcastPkts;
    private BigInteger rfc1213IfOutDiscards;
    private BigInteger rfc1213IfOutErrors;
    private BigInteger rfc1213IfOutQLen;
    private BigInteger rfc1213IpInReceives;
    private BigInteger rfc1213IpInHdrErrors;
    private BigInteger rfc1213IpForwDatagrams;
    private BigInteger rfc1213IpInDiscards;
    private BigInteger rfc1493Dot1DBasePortDelayExceededDiscards;
    private BigInteger rfc1493Dot1DBasePortMtuExceededDiscards;
    private BigInteger rfc1493Dot1DTpPortInFrames;
    private BigInteger rfc1493Dot1DTpPortOutFrames;
    private BigInteger rfc1493Dot1DPortInDiscards;
    private BigInteger rfc1757StatsDropEvents;
    private BigInteger rfc1757StatsMulticastPkts;
    private BigInteger rfc1757StatsBroadcastPkts;
    private BigInteger rfc1757StatsUndersizePkts;
    private BigInteger rfc1757StatsFragments;
    private BigInteger rfc1757StatsPkts64Octets;
    private BigInteger rfc1757StatsPkts65To127Octets;
    private BigInteger rfc1757StatsPkts128To255Octets;
    private BigInteger rfc1757StatsPkts256To511Octets;
    private BigInteger rfc1757StatsPkts512To1023Octets;
    private BigInteger rfc1757StatsPkts1024To1518Octets;
    private BigInteger rfc1757StatsOversizePkts;
    private BigInteger rfc1757StatsJabbers;
    private BigInteger rfc1757StatsOctets;
    private BigInteger rfc1757StatsPkts;
    private BigInteger rfc1757StatsCollisions;
    private BigInteger rfc1757StatsCRCAlignErrors;
    private BigInteger rfc1757StatsTXNoErrors;
    private BigInteger rfc1757StatsRXNoErrors;
    private BigInteger rfc2665Dot3StatsAlignmentErrors;
    private BigInteger rfc2665Dot3StatsFCSErrors;
    private BigInteger rfc2665Dot3StatsSingleCollisionFrames;
    private BigInteger rfc2665Dot3StatsMultipleCollisionFrames;
    private BigInteger rfc2665Dot3StatsSQETTestErrors;
    private BigInteger rfc2665Dot3StatsDeferredTransmissions;
    private BigInteger rfc2665Dot3StatsLateCollisions;
    private BigInteger rfc2665Dot3StatsExcessiveCollisions;
    private BigInteger rfc2665Dot3StatsInternalMacTransmitErrors;
    private BigInteger rfc2665Dot3StatsCarrierSenseErrors;
    private BigInteger rfc2665Dot3StatsFrameTooLongs;
    private BigInteger rfc2665Dot3StatsInternalMacReceiveErrors;
    private BigInteger rfc2665Dot3StatsSymbolErrors;
    private BigInteger rfc2665Dot3ControlInUnknownOpcodes;
    private BigInteger rfc2665Dot3InPauseFrames;
    private BigInteger rfc2665Dot3OutPauseFrames;
    private BigInteger rfc2233IfHCInOctets;
    private BigInteger rfc2233IfHCInUcastPkts;
    private BigInteger rfc2233IfHCInMulticastPkts;
    private BigInteger rfc2233IfHCInBroadcastPkts;
    private BigInteger rfc2233IfHCOutOctets;
    private BigInteger rfc2233IfHCOutUcastPkts;
    private BigInteger rfc2233IfHCOutMulticastPkts;
    private BigInteger rfc2233IfHCOutBroadcastPckts;

    /**
     * @return the portNumber
     */
    public BigInteger getPortNumber() {
        return portNumber;
    }
    /**
     * @param portNumber the portNumber to set
     */
    public void setPortNumber(BigInteger portNumber) {
        this.portNumber = portNumber;
    }
    /**
     * @return the resetTimeEpoch
     */
    public BigInteger getResetTimeEpoch() {
        return resetTimeEpoch;
    }
    /**
     * @param resetTimeEpoch the resetTimeEpoch to set
     */
    public void setResetTimeEpoch(BigInteger resetTimeEpoch) {
        this.resetTimeEpoch = resetTimeEpoch;
    }
    /**
     * @return the rfc1213IfInOctets
     */
    public BigInteger getRfc1213IfInOctets() {
        return rfc1213IfInOctets;
    }
    /**
     * @param rfc1213IfInOctets the rfc1213IfInOctets to set
     */
    public void setRfc1213IfInOctets(BigInteger rfc1213IfInOctets) {
        this.rfc1213IfInOctets = rfc1213IfInOctets;
    }
    /**
     * @return the rfc1213IfInUcastPkts
     */
    public BigInteger getRfc1213IfInUcastPkts() {
        return rfc1213IfInUcastPkts;
    }
    /**
     * @param rfc1213IfInUcastPkts the rfc1213IfInUcastPkts to set
     */
    public void setRfc1213IfInUcastPkts(BigInteger rfc1213IfInUcastPkts) {
        this.rfc1213IfInUcastPkts = rfc1213IfInUcastPkts;
    }
    /**
     * @return the rfc1213IfInNUcastPkts
     */
    public BigInteger getRfc1213IfInNUcastPkts() {
        return rfc1213IfInNUcastPkts;
    }
    /**
     * @param rfc1213IfInNUcastPkts the rfc1213IfInNUcastPkts to set
     */
    public void setRfc1213IfInNUcastPkts(BigInteger rfc1213IfInNUcastPkts) {
        this.rfc1213IfInNUcastPkts = rfc1213IfInNUcastPkts;
    }
    /**
     * @return the rfc1213IfInDiscards
     */
    public BigInteger getRfc1213IfInDiscards() {
        return rfc1213IfInDiscards;
    }
    /**
     * @param rfc1213IfInDiscards the rfc1213IfInDiscards to set
     */
    public void setRfc1213IfInDiscards(BigInteger rfc1213IfInDiscards) {
        this.rfc1213IfInDiscards = rfc1213IfInDiscards;
    }
    /**
     * @return the rfc1213IfInErrors
     */
    public BigInteger getRfc1213IfInErrors() {
        return rfc1213IfInErrors;
    }
    /**
     * @param rfc1213IfInErrors the rfc1213IfInErrors to set
     */
    public void setRfc1213IfInErrors(BigInteger rfc1213IfInErrors) {
        this.rfc1213IfInErrors = rfc1213IfInErrors;
    }
    /**
     * @return the rfc1213IfInUnknownProtos
     */
    public BigInteger getRfc1213IfInUnknownProtos() {
        return rfc1213IfInUnknownProtos;
    }
    /**
     * @param rfc1213IfInUnknownProtos the rfc1213IfInUnknownProtos to set
     */
    public void setRfc1213IfInUnknownProtos(BigInteger rfc1213IfInUnknownProtos) {
        this.rfc1213IfInUnknownProtos = rfc1213IfInUnknownProtos;
    }
    /**
     * @return the rfc1213IfOutOctets
     */
    public BigInteger getRfc1213IfOutOctets() {
        return rfc1213IfOutOctets;
    }
    /**
     * @param rfc1213IfOutOctets the rfc1213IfOutOctets to set
     */
    public void setRfc1213IfOutOctets(BigInteger rfc1213IfOutOctets) {
        this.rfc1213IfOutOctets = rfc1213IfOutOctets;
    }
    /**
     * @return the rfc1213IfOutUcastPkts
     */
    public BigInteger getRfc1213IfOutUcastPkts() {
        return rfc1213IfOutUcastPkts;
    }
    /**
     * @param rfc1213IfOutUcastPkts the rfc1213IfOutUcastPkts to set
     */
    public void setRfc1213IfOutUcastPkts(BigInteger rfc1213IfOutUcastPkts) {
        this.rfc1213IfOutUcastPkts = rfc1213IfOutUcastPkts;
    }
    /**
     * @return the rfc1213IfOutNUcastPkts
     */
    public BigInteger getRfc1213IfOutNUcastPkts() {
        return rfc1213IfOutNUcastPkts;
    }
    /**
     * @param rfc1213IfOutNUcastPkts the rfc1213IfOutNUcastPkts to set
     */
    public void setRfc1213IfOutNUcastPkts(BigInteger rfc1213IfOutNUcastPkts) {
        this.rfc1213IfOutNUcastPkts = rfc1213IfOutNUcastPkts;
    }
    /**
     * @return the rfc1213IfOutDiscards
     */
    public BigInteger getRfc1213IfOutDiscards() {
        return rfc1213IfOutDiscards;
    }
    /**
     * @param rfc1213IfOutDiscards the rfc1213IfOutDiscards to set
     */
    public void setRfc1213IfOutDiscards(BigInteger rfc1213IfOutDiscards) {
        this.rfc1213IfOutDiscards = rfc1213IfOutDiscards;
    }
    /**
     * @return the rfc1213IfOutErrors
     */
    public BigInteger getRfc1213IfOutErrors() {
        return rfc1213IfOutErrors;
    }
    /**
     * @param rfc1213IfOutErrors the rfc1213IfOutErrors to set
     */
    public void setRfc1213IfOutErrors(BigInteger rfc1213IfOutErrors) {
        this.rfc1213IfOutErrors = rfc1213IfOutErrors;
    }
    /**
     * @return the rfc1213IfOutQLen
     */
    public BigInteger getRfc1213IfOutQLen() {
        return rfc1213IfOutQLen;
    }
    /**
     * @param rfc1213IfOutQLen the rfc1213IfOutQLen to set
     */
    public void setRfc1213IfOutQLen(BigInteger rfc1213IfOutQLen) {
        this.rfc1213IfOutQLen = rfc1213IfOutQLen;
    }
    /**
     * @return the rfc1213IpInReceives
     */
    public BigInteger getRfc1213IpInReceives() {
        return rfc1213IpInReceives;
    }
    /**
     * @param rfc1213IpInReceives the rfc1213IpInReceives to set
     */
    public void setRfc1213IpInReceives(BigInteger rfc1213IpInReceives) {
        this.rfc1213IpInReceives = rfc1213IpInReceives;
    }
    /**
     * @return the rfc1213IpInHdrErrors
     */
    public BigInteger getRfc1213IpInHdrErrors() {
        return rfc1213IpInHdrErrors;
    }
    /**
     * @param rfc1213IpInHdrErrors the rfc1213IpInHdrErrors to set
     */
    public void setRfc1213IpInHdrErrors(BigInteger rfc1213IpInHdrErrors) {
        this.rfc1213IpInHdrErrors = rfc1213IpInHdrErrors;
    }
    /**
     * @return the rfc1213IpForwDatagrams
     */
    public BigInteger getRfc1213IpForwDatagrams() {
        return rfc1213IpForwDatagrams;
    }
    /**
     * @param rfc1213IpForwDatagrams the rfc1213IpForwDatagrams to set
     */
    public void setRfc1213IpForwDatagrams(BigInteger rfc1213IpForwDatagrams) {
        this.rfc1213IpForwDatagrams = rfc1213IpForwDatagrams;
    }
    /**
     * @return the rfc1213IpInDiscards
     */
    public BigInteger getRfc1213IpInDiscards() {
        return rfc1213IpInDiscards;
    }
    /**
     * @param rfc1213IpInDiscards the rfc1213IpInDiscards to set
     */
    public void setRfc1213IpInDiscards(BigInteger rfc1213IpInDiscards) {
        this.rfc1213IpInDiscards = rfc1213IpInDiscards;
    }
    /**
     * @return the rfc1493Dot1DBasePortDelayExceededDiscards
     */
    public BigInteger getRfc1493Dot1DBasePortDelayExceededDiscards() {
        return rfc1493Dot1DBasePortDelayExceededDiscards;
    }
    /**
     * @param rfc1493Dot1DBasePortDelayExceededDiscards the rfc1493Dot1DBasePortDelayExceededDiscards to set
     */
    public void setRfc1493Dot1DBasePortDelayExceededDiscards(BigInteger rfc1493Dot1DBasePortDelayExceededDiscards) {
        this.rfc1493Dot1DBasePortDelayExceededDiscards = rfc1493Dot1DBasePortDelayExceededDiscards;
    }
    /**
     * @return the rfc1493Dot1DBasePortMtuExceededDiscards
     */
    public BigInteger getRfc1493Dot1DBasePortMtuExceededDiscards() {
        return rfc1493Dot1DBasePortMtuExceededDiscards;
    }
    /**
     * @param rfc1493Dot1DBasePortMtuExceededDiscards the rfc1493Dot1DBasePortMtuExceededDiscards to set
     */
    public void setRfc1493Dot1DBasePortMtuExceededDiscards(BigInteger rfc1493Dot1DBasePortMtuExceededDiscards) {
        this.rfc1493Dot1DBasePortMtuExceededDiscards = rfc1493Dot1DBasePortMtuExceededDiscards;
    }
    /**
     * @return the rfc1493Dot1DTpPortInFrames
     */
    public BigInteger getRfc1493Dot1DTpPortInFrames() {
        return rfc1493Dot1DTpPortInFrames;
    }
    /**
     * @param rfc1493Dot1DTpPortInFrames the rfc1493Dot1DTpPortInFrames to set
     */
    public void setRfc1493Dot1DTpPortInFrames(BigInteger rfc1493Dot1DTpPortInFrames) {
        this.rfc1493Dot1DTpPortInFrames = rfc1493Dot1DTpPortInFrames;
    }
    /**
     * @return the rfc1493Dot1DTpPortOutFrames
     */
    public BigInteger getRfc1493Dot1DTpPortOutFrames() {
        return rfc1493Dot1DTpPortOutFrames;
    }
    /**
     * @param rfc1493Dot1DTpPortOutFrames the rfc1493Dot1DTpPortOutFrames to set
     */
    public void setRfc1493Dot1DTpPortOutFrames(BigInteger rfc1493Dot1DTpPortOutFrames) {
        this.rfc1493Dot1DTpPortOutFrames = rfc1493Dot1DTpPortOutFrames;
    }
    /**
     * @return the rfc1493Dot1DPortInDiscards
     */
    public BigInteger getRfc1493Dot1DPortInDiscards() {
        return rfc1493Dot1DPortInDiscards;
    }
    /**
     * @param rfc1493Dot1DPortInDiscards the rfc1493Dot1DPortInDiscards to set
     */
    public void setRfc1493Dot1DPortInDiscards(BigInteger rfc1493Dot1DPortInDiscards) {
        this.rfc1493Dot1DPortInDiscards = rfc1493Dot1DPortInDiscards;
    }
    /**
     * @return the rfc1757StatsDropEvents
     */
    public BigInteger getRfc1757StatsDropEvents() {
        return rfc1757StatsDropEvents;
    }
    /**
     * @param rfc1757StatsDropEvents the rfc1757StatsDropEvents to set
     */
    public void setRfc1757StatsDropEvents(BigInteger rfc1757StatsDropEvents) {
        this.rfc1757StatsDropEvents = rfc1757StatsDropEvents;
    }
    /**
     * @return the rfc1757StatsMulticastPkts
     */
    public BigInteger getRfc1757StatsMulticastPkts() {
        return rfc1757StatsMulticastPkts;
    }
    /**
     * @param rfc1757StatsMulticastPkts the rfc1757StatsMulticastPkts to set
     */
    public void setRfc1757StatsMulticastPkts(BigInteger rfc1757StatsMulticastPkts) {
        this.rfc1757StatsMulticastPkts = rfc1757StatsMulticastPkts;
    }
    /**
     * @return the rfc1757StatsBroadcastPkts
     */
    public BigInteger getRfc1757StatsBroadcastPkts() {
        return rfc1757StatsBroadcastPkts;
    }
    /**
     * @param rfc1757StatsBroadcastPkts the rfc1757StatsBroadcastPkts to set
     */
    public void setRfc1757StatsBroadcastPkts(BigInteger rfc1757StatsBroadcastPkts) {
        this.rfc1757StatsBroadcastPkts = rfc1757StatsBroadcastPkts;
    }
    /**
     * @return the rfc1757StatsUndersizePkts
     */
    public BigInteger getRfc1757StatsUndersizePkts() {
        return rfc1757StatsUndersizePkts;
    }
    /**
     * @param rfc1757StatsUndersizePkts the rfc1757StatsUndersizePkts to set
     */
    public void setRfc1757StatsUndersizePkts(BigInteger rfc1757StatsUndersizePkts) {
        this.rfc1757StatsUndersizePkts = rfc1757StatsUndersizePkts;
    }
    /**
     * @return the rfc1757StatsFragments
     */
    public BigInteger getRfc1757StatsFragments() {
        return rfc1757StatsFragments;
    }
    /**
     * @param rfc1757StatsFragments the rfc1757StatsFragments to set
     */
    public void setRfc1757StatsFragments(BigInteger rfc1757StatsFragments) {
        this.rfc1757StatsFragments = rfc1757StatsFragments;
    }
    /**
     * @return the rfc1757StatsPkts64Octets
     */
    public BigInteger getRfc1757StatsPkts64Octets() {
        return rfc1757StatsPkts64Octets;
    }
    /**
     * @param rfc1757StatsPkts64Octets the rfc1757StatsPkts64Octets to set
     */
    public void setRfc1757StatsPkts64Octets(BigInteger rfc1757StatsPkts64Octets) {
        this.rfc1757StatsPkts64Octets = rfc1757StatsPkts64Octets;
    }
    /**
     * @return the rfc1757StatsPkts65To127Octets
     */
    public BigInteger getRfc1757StatsPkts65To127Octets() {
        return rfc1757StatsPkts65To127Octets;
    }
    /**
     * @param rfc1757StatsPkts65To127Octets the rfc1757StatsPkts65To127Octets to set
     */
    public void setRfc1757StatsPkts65To127Octets(BigInteger rfc1757StatsPkts65To127Octets) {
        this.rfc1757StatsPkts65To127Octets = rfc1757StatsPkts65To127Octets;
    }
    /**
     * @return the rfc1757StatsPkts128To255Octets
     */
    public BigInteger getRfc1757StatsPkts128To255Octets() {
        return rfc1757StatsPkts128To255Octets;
    }
    /**
     * @param rfc1757StatsPkts128To255Octets the rfc1757StatsPkts128To255Octets to set
     */
    public void setRfc1757StatsPkts128To255Octets(BigInteger rfc1757StatsPkts128To255Octets) {
        this.rfc1757StatsPkts128To255Octets = rfc1757StatsPkts128To255Octets;
    }
    /**
     * @return the rfc1757StatsPkts256To511Octets
     */
    public BigInteger getRfc1757StatsPkts256To511Octets() {
        return rfc1757StatsPkts256To511Octets;
    }
    /**
     * @param rfc1757StatsPkts256To511Octets the rfc1757StatsPkts256To511Octets to set
     */
    public void setRfc1757StatsPkts256To511Octets(BigInteger rfc1757StatsPkts256To511Octets) {
        this.rfc1757StatsPkts256To511Octets = rfc1757StatsPkts256To511Octets;
    }
    /**
     * @return the rfc1757StatsPkts512To1023Octets
     */
    public BigInteger getRfc1757StatsPkts512To1023Octets() {
        return rfc1757StatsPkts512To1023Octets;
    }
    /**
     * @param rfc1757StatsPkts512To1023Octets the rfc1757StatsPkts512To1023Octets to set
     */
    public void setRfc1757StatsPkts512To1023Octets(BigInteger rfc1757StatsPkts512To1023Octets) {
        this.rfc1757StatsPkts512To1023Octets = rfc1757StatsPkts512To1023Octets;
    }
    /**
     * @return the rfc1757StatsPkts1024To1518Octets
     */
    public BigInteger getRfc1757StatsPkts1024To1518Octets() {
        return rfc1757StatsPkts1024To1518Octets;
    }
    /**
     * @param rfc1757StatsPkts1024To1518Octets the rfc1757StatsPkts1024To1518Octets to set
     */
    public void setRfc1757StatsPkts1024To1518Octets(BigInteger rfc1757StatsPkts1024To1518Octets) {
        this.rfc1757StatsPkts1024To1518Octets = rfc1757StatsPkts1024To1518Octets;
    }
    /**
     * @return the rfc1757StatsOversizePkts
     */
    public BigInteger getRfc1757StatsOversizePkts() {
        return rfc1757StatsOversizePkts;
    }
    /**
     * @param rfc1757StatsOversizePkts the rfc1757StatsOversizePkts to set
     */
    public void setRfc1757StatsOversizePkts(BigInteger rfc1757StatsOversizePkts) {
        this.rfc1757StatsOversizePkts = rfc1757StatsOversizePkts;
    }
    /**
     * @return the rfc1757StatsJabbers
     */
    public BigInteger getRfc1757StatsJabbers() {
        return rfc1757StatsJabbers;
    }
    /**
     * @param rfc1757StatsJabbers the rfc1757StatsJabbers to set
     */
    public void setRfc1757StatsJabbers(BigInteger rfc1757StatsJabbers) {
        this.rfc1757StatsJabbers = rfc1757StatsJabbers;
    }
    /**
     * @return the rfc1757StatsOctets
     */
    public BigInteger getRfc1757StatsOctets() {
        return rfc1757StatsOctets;
    }
    /**
     * @param rfc1757StatsOctets the rfc1757StatsOctets to set
     */
    public void setRfc1757StatsOctets(BigInteger rfc1757StatsOctets) {
        this.rfc1757StatsOctets = rfc1757StatsOctets;
    }
    /**
     * @return the rfc1757StatsPkts
     */
    public BigInteger getRfc1757StatsPkts() {
        return rfc1757StatsPkts;
    }
    /**
     * @param rfc1757StatsPkts the rfc1757StatsPkts to set
     */
    public void setRfc1757StatsPkts(BigInteger rfc1757StatsPkts) {
        this.rfc1757StatsPkts = rfc1757StatsPkts;
    }
    /**
     * @return the rfc1757StatsCollisions
     */
    public BigInteger getRfc1757StatsCollisions() {
        return rfc1757StatsCollisions;
    }
    /**
     * @param rfc1757StatsCollisions the rfc1757StatsCollisions to set
     */
    public void setRfc1757StatsCollisions(BigInteger rfc1757StatsCollisions) {
        this.rfc1757StatsCollisions = rfc1757StatsCollisions;
    }
    /**
     * @return the rfc1757StatsCRCAlignErrors
     */
    public BigInteger getRfc1757StatsCRCAlignErrors() {
        return rfc1757StatsCRCAlignErrors;
    }
    /**
     * @param rfc1757StatsCRCAlignErrors the rfc1757StatsCRCAlignErrors to set
     */
    public void setRfc1757StatsCRCAlignErrors(BigInteger rfc1757StatsCRCAlignErrors) {
        this.rfc1757StatsCRCAlignErrors = rfc1757StatsCRCAlignErrors;
    }
    /**
     * @return the rfc1757StatsTXNoErrors
     */
    public BigInteger getRfc1757StatsTXNoErrors() {
        return rfc1757StatsTXNoErrors;
    }
    /**
     * @param rfc1757StatsTXNoErrors the rfc1757StatsTXNoErrors to set
     */
    public void setRfc1757StatsTXNoErrors(BigInteger rfc1757StatsTXNoErrors) {
        this.rfc1757StatsTXNoErrors = rfc1757StatsTXNoErrors;
    }
    /**
     * @return the rfc1757StatsRXNoErrors
     */
    public BigInteger getRfc1757StatsRXNoErrors() {
        return rfc1757StatsRXNoErrors;
    }
    /**
     * @param rfc1757StatsRXNoErrors the rfc1757StatsRXNoErrors to set
     */
    public void setRfc1757StatsRXNoErrors(BigInteger rfc1757StatsRXNoErrors) {
        this.rfc1757StatsRXNoErrors = rfc1757StatsRXNoErrors;
    }
    /**
     * @return the rfc2665Dot3StatsAlignmentErrors
     */
    public BigInteger getRfc2665Dot3StatsAlignmentErrors() {
        return rfc2665Dot3StatsAlignmentErrors;
    }
    /**
     * @param rfc2665Dot3StatsAlignmentErrors the rfc2665Dot3StatsAlignmentErrors to set
     */
    public void setRfc2665Dot3StatsAlignmentErrors(BigInteger rfc2665Dot3StatsAlignmentErrors) {
        this.rfc2665Dot3StatsAlignmentErrors = rfc2665Dot3StatsAlignmentErrors;
    }
    /**
     * @return the rfc2665Dot3StatsFCSErrors
     */
    public BigInteger getRfc2665Dot3StatsFCSErrors() {
        return rfc2665Dot3StatsFCSErrors;
    }
    /**
     * @param rfc2665Dot3StatsFCSErrors the rfc2665Dot3StatsFCSErrors to set
     */
    public void setRfc2665Dot3StatsFCSErrors(BigInteger rfc2665Dot3StatsFCSErrors) {
        this.rfc2665Dot3StatsFCSErrors = rfc2665Dot3StatsFCSErrors;
    }
    /**
     * @return the rfc2665Dot3StatsSingleCollisionFrames
     */
    public BigInteger getRfc2665Dot3StatsSingleCollisionFrames() {
        return rfc2665Dot3StatsSingleCollisionFrames;
    }
    /**
     * @param rfc2665Dot3StatsSingleCollisionFrames the rfc2665Dot3StatsSingleCollisionFrames to set
     */
    public void setRfc2665Dot3StatsSingleCollisionFrames(BigInteger rfc2665Dot3StatsSingleCollisionFrames) {
        this.rfc2665Dot3StatsSingleCollisionFrames = rfc2665Dot3StatsSingleCollisionFrames;
    }
    /**
     * @return the rfc2665Dot3StatsMultipleCollisionFrames
     */
    public BigInteger getRfc2665Dot3StatsMultipleCollisionFrames() {
        return rfc2665Dot3StatsMultipleCollisionFrames;
    }
    /**
     * @param rfc2665Dot3StatsMultipleCollisionFrames the rfc2665Dot3StatsMultipleCollisionFrames to set
     */
    public void setRfc2665Dot3StatsMultipleCollisionFrames(BigInteger rfc2665Dot3StatsMultipleCollisionFrames) {
        this.rfc2665Dot3StatsMultipleCollisionFrames = rfc2665Dot3StatsMultipleCollisionFrames;
    }
    /**
     * @return the rfc2665Dot3StatsSQETTestErrors
     */
    public BigInteger getRfc2665Dot3StatsSQETTestErrors() {
        return rfc2665Dot3StatsSQETTestErrors;
    }
    /**
     * @param rfc2665Dot3StatsSQETTestErrors the rfc2665Dot3StatsSQETTestErrors to set
     */
    public void setRfc2665Dot3StatsSQETTestErrors(BigInteger rfc2665Dot3StatsSQETTestErrors) {
        this.rfc2665Dot3StatsSQETTestErrors = rfc2665Dot3StatsSQETTestErrors;
    }
    /**
     * @return the rfc2665Dot3StatsDeferredTransmissions
     */
    public BigInteger getRfc2665Dot3StatsDeferredTransmissions() {
        return rfc2665Dot3StatsDeferredTransmissions;
    }
    /**
     * @param rfc2665Dot3StatsDeferredTransmissions the rfc2665Dot3StatsDeferredTransmissions to set
     */
    public void setRfc2665Dot3StatsDeferredTransmissions(BigInteger rfc2665Dot3StatsDeferredTransmissions) {
        this.rfc2665Dot3StatsDeferredTransmissions = rfc2665Dot3StatsDeferredTransmissions;
    }
    /**
     * @return the rfc2665Dot3StatsLateCollisions
     */
    public BigInteger getRfc2665Dot3StatsLateCollisions() {
        return rfc2665Dot3StatsLateCollisions;
    }
    /**
     * @param rfc2665Dot3StatsLateCollisions the rfc2665Dot3StatsLateCollisions to set
     */
    public void setRfc2665Dot3StatsLateCollisions(BigInteger rfc2665Dot3StatsLateCollisions) {
        this.rfc2665Dot3StatsLateCollisions = rfc2665Dot3StatsLateCollisions;
    }
    /**
     * @return the rfc2665Dot3StatsExcessiveCollisions
     */
    public BigInteger getRfc2665Dot3StatsExcessiveCollisions() {
        return rfc2665Dot3StatsExcessiveCollisions;
    }
    /**
     * @param rfc2665Dot3StatsExcessiveCollisions the rfc2665Dot3StatsExcessiveCollisions to set
     */
    public void setRfc2665Dot3StatsExcessiveCollisions(BigInteger rfc2665Dot3StatsExcessiveCollisions) {
        this.rfc2665Dot3StatsExcessiveCollisions = rfc2665Dot3StatsExcessiveCollisions;
    }
    /**
     * @return the rfc2665Dot3StatsInternalMacTransmitErrors
     */
    public BigInteger getRfc2665Dot3StatsInternalMacTransmitErrors() {
        return rfc2665Dot3StatsInternalMacTransmitErrors;
    }
    /**
     * @param rfc2665Dot3StatsInternalMacTransmitErrors the rfc2665Dot3StatsInternalMacTransmitErrors to set
     */
    public void setRfc2665Dot3StatsInternalMacTransmitErrors(BigInteger rfc2665Dot3StatsInternalMacTransmitErrors) {
        this.rfc2665Dot3StatsInternalMacTransmitErrors = rfc2665Dot3StatsInternalMacTransmitErrors;
    }
    /**
     * @return the rfc2665Dot3StatsCarrierSenseErrors
     */
    public BigInteger getRfc2665Dot3StatsCarrierSenseErrors() {
        return rfc2665Dot3StatsCarrierSenseErrors;
    }
    /**
     * @param rfc2665Dot3StatsCarrierSenseErrors the rfc2665Dot3StatsCarrierSenseErrors to set
     */
    public void setRfc2665Dot3StatsCarrierSenseErrors(BigInteger rfc2665Dot3StatsCarrierSenseErrors) {
        this.rfc2665Dot3StatsCarrierSenseErrors = rfc2665Dot3StatsCarrierSenseErrors;
    }
    /**
     * @return the rfc2665Dot3StatsFrameTooLongs
     */
    public BigInteger getRfc2665Dot3StatsFrameTooLongs() {
        return rfc2665Dot3StatsFrameTooLongs;
    }
    /**
     * @param rfc2665Dot3StatsFrameTooLongs the rfc2665Dot3StatsFrameTooLongs to set
     */
    public void setRfc2665Dot3StatsFrameTooLongs(BigInteger rfc2665Dot3StatsFrameTooLongs) {
        this.rfc2665Dot3StatsFrameTooLongs = rfc2665Dot3StatsFrameTooLongs;
    }
    /**
     * @return the rfc2665Dot3StatsInternalMacReceiveErrors
     */
    public BigInteger getRfc2665Dot3StatsInternalMacReceiveErrors() {
        return rfc2665Dot3StatsInternalMacReceiveErrors;
    }
    /**
     * @param rfc2665Dot3StatsInternalMacReceiveErrors the rfc2665Dot3StatsInternalMacReceiveErrors to set
     */
    public void setRfc2665Dot3StatsInternalMacReceiveErrors(BigInteger rfc2665Dot3StatsInternalMacReceiveErrors) {
        this.rfc2665Dot3StatsInternalMacReceiveErrors = rfc2665Dot3StatsInternalMacReceiveErrors;
    }
    /**
     * @return the rfc2665Dot3StatsSymbolErrors
     */
    public BigInteger getRfc2665Dot3StatsSymbolErrors() {
        return rfc2665Dot3StatsSymbolErrors;
    }
    /**
     * @param rfc2665Dot3StatsSymbolErrors the rfc2665Dot3StatsSymbolErrors to set
     */
    public void setRfc2665Dot3StatsSymbolErrors(BigInteger rfc2665Dot3StatsSymbolErrors) {
        this.rfc2665Dot3StatsSymbolErrors = rfc2665Dot3StatsSymbolErrors;
    }
    /**
     * @return the rfc2665Dot3ControlInUnknownOpcodes
     */
    public BigInteger getRfc2665Dot3ControlInUnknownOpcodes() {
        return rfc2665Dot3ControlInUnknownOpcodes;
    }
    /**
     * @param rfc2665Dot3ControlInUnknownOpcodes the rfc2665Dot3ControlInUnknownOpcodes to set
     */
    public void setRfc2665Dot3ControlInUnknownOpcodes(BigInteger rfc2665Dot3ControlInUnknownOpcodes) {
        this.rfc2665Dot3ControlInUnknownOpcodes = rfc2665Dot3ControlInUnknownOpcodes;
    }
    /**
     * @return the rfc2665Dot3InPauseFrames
     */
    public BigInteger getRfc2665Dot3InPauseFrames() {
        return rfc2665Dot3InPauseFrames;
    }
    /**
     * @param rfc2665Dot3InPauseFrames the rfc2665Dot3InPauseFrames to set
     */
    public void setRfc2665Dot3InPauseFrames(BigInteger rfc2665Dot3InPauseFrames) {
        this.rfc2665Dot3InPauseFrames = rfc2665Dot3InPauseFrames;
    }
    /**
     * @return the rfc2665Dot3OutPauseFrames
     */
    public BigInteger getRfc2665Dot3OutPauseFrames() {
        return rfc2665Dot3OutPauseFrames;
    }
    /**
     * @param rfc2665Dot3OutPauseFrames the rfc2665Dot3OutPauseFrames to set
     */
    public void setRfc2665Dot3OutPauseFrames(BigInteger rfc2665Dot3OutPauseFrames) {
        this.rfc2665Dot3OutPauseFrames = rfc2665Dot3OutPauseFrames;
    }
    /**
     * @return the rfc2233IfHCInOctets
     */
    public BigInteger getRfc2233IfHCInOctets() {
        return rfc2233IfHCInOctets;
    }
    /**
     * @param rfc2233IfHCInOctets the rfc2233IfHCInOctets to set
     */
    public void setRfc2233IfHCInOctets(BigInteger rfc2233IfHCInOctets) {
        this.rfc2233IfHCInOctets = rfc2233IfHCInOctets;
    }
    /**
     * @return the rfc2233IfHCInUcastPkts
     */
    public BigInteger getRfc2233IfHCInUcastPkts() {
        return rfc2233IfHCInUcastPkts;
    }
    /**
     * @param rfc2233IfHCInUcastPkts the rfc2233IfHCInUcastPkts to set
     */
    public void setRfc2233IfHCInUcastPkts(BigInteger rfc2233IfHCInUcastPkts) {
        this.rfc2233IfHCInUcastPkts = rfc2233IfHCInUcastPkts;
    }
    /**
     * @return the rfc2233IfHCInMulticastPkts
     */
    public BigInteger getRfc2233IfHCInMulticastPkts() {
        return rfc2233IfHCInMulticastPkts;
    }
    /**
     * @param rfc2233IfHCInMulticastPkts the rfc2233IfHCInMulticastPkts to set
     */
    public void setRfc2233IfHCInMulticastPkts(BigInteger rfc2233IfHCInMulticastPkts) {
        this.rfc2233IfHCInMulticastPkts = rfc2233IfHCInMulticastPkts;
    }
    /**
     * @return the rfc2233IfHCInBroadcastPkts
     */
    public BigInteger getRfc2233IfHCInBroadcastPkts() {
        return rfc2233IfHCInBroadcastPkts;
    }
    /**
     * @param rfc2233IfHCInBroadcastPkts the rfc2233IfHCInBroadcastPkts to set
     */
    public void setRfc2233IfHCInBroadcastPkts(BigInteger rfc2233IfHCInBroadcastPkts) {
        this.rfc2233IfHCInBroadcastPkts = rfc2233IfHCInBroadcastPkts;
    }
    /**
     * @return the rfc2233IfHCOutOctets
     */
    public BigInteger getRfc2233IfHCOutOctets() {
        return rfc2233IfHCOutOctets;
    }
    /**
     * @param rfc2233IfHCOutOctets the rfc2233IfHCOutOctets to set
     */
    public void setRfc2233IfHCOutOctets(BigInteger rfc2233IfHCOutOctets) {
        this.rfc2233IfHCOutOctets = rfc2233IfHCOutOctets;
    }
    /**
     * @return the rfc2233IfHCOutUcastPkts
     */
    public BigInteger getRfc2233IfHCOutUcastPkts() {
        return rfc2233IfHCOutUcastPkts;
    }
    /**
     * @param rfc2233IfHCOutUcastPkts the rfc2233IfHCOutUcastPkts to set
     */
    public void setRfc2233IfHCOutUcastPkts(BigInteger rfc2233IfHCOutUcastPkts) {
        this.rfc2233IfHCOutUcastPkts = rfc2233IfHCOutUcastPkts;
    }
    /**
     * @return the rfc2233IfHCOutMulticastPkts
     */
    public BigInteger getRfc2233IfHCOutMulticastPkts() {
        return rfc2233IfHCOutMulticastPkts;
    }
    /**
     * @param rfc2233IfHCOutMulticastPkts the rfc2233IfHCOutMulticastPkts to set
     */
    public void setRfc2233IfHCOutMulticastPkts(BigInteger rfc2233IfHCOutMulticastPkts) {
        this.rfc2233IfHCOutMulticastPkts = rfc2233IfHCOutMulticastPkts;
    }
    /**
     * @return the rfc2233IfHCOutBroadcastPckts
     */
    public BigInteger getRfc2233IfHCOutBroadcastPckts() {
        return rfc2233IfHCOutBroadcastPckts;
    }
    /**
     * @param rfc2233IfHCOutBroadcastPckts the rfc2233IfHCOutBroadcastPckts to set
     */
    public void setRfc2233IfHCOutBroadcastPckts(BigInteger rfc2233IfHCOutBroadcastPckts) {
        this.rfc2233IfHCOutBroadcastPckts = rfc2233IfHCOutBroadcastPckts;
    }

}
