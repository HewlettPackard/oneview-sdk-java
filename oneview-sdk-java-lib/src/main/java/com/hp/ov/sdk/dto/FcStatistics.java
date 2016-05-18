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
package com.hp.ov.sdk.dto;

import java.math.BigInteger;
import java.util.Map;

public class FcStatistics extends BaseModelResource {

    private final static long serialVersionUID = 1L;

    private BigInteger portNumber;
    private BigInteger fcRxFrameRate;
    private BigInteger fcTxFrameRate;
    private BigInteger fcRxByteRate;
    private BigInteger fcTxByteRate;
    private BigInteger fcTotalRxFrames;
    private BigInteger fcTotalTxFrames;
    private BigInteger fcAddressErrors;
    private BigInteger fcClass2RxFrames;
    private BigInteger fcClass2TxFrames;
    private BigInteger fcClass3RxFrames;
    private BigInteger fcClass3TxFrames;
    private BigInteger fcClass3Discards;
    private BigInteger fcInvalidCRC;
    private BigInteger fcFramesTooLong;
    private BigInteger fcFramesTruncated;
    private BigInteger fcFRJTFrames;
    private BigInteger fcFBSYFrames;
    private BigInteger fcTotalRxBytes;
    private BigInteger fcTotalTxBytes;
    private BigInteger fcBBCreditFrameFailures;
    private BigInteger fcBBCreditRRDYFailures;
    private BigInteger fcLinkFailures;
    private BigInteger fcRxLinkResets;
    private BigInteger fcTxLinkResets;
    private BigInteger fcNumberLinkResets;
    private BigInteger fcLossOfSynchronization;
    private BigInteger fcRxOfflineSequences;
    private BigInteger fcTxOfflineSequences;
    private BigInteger fcNumberOfflineSequences;
    private BigInteger fcPrimitiveSeqProtocolErrors;
    private BigInteger fcInvalidTxWords;
    private BigInteger fcSmoothingOverflowErrors;
    private BigInteger fcDecodeErrors;
    private Map<String,String> extendedStatistics;

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
     * @return the fcRxFrameRate
     */
    public BigInteger getFcRxFrameRate() {
        return fcRxFrameRate;
    }
    /**
     * @param fcRxFrameRate the fcRxFrameRate to set
     */
    public void setFcRxFrameRate(BigInteger fcRxFrameRate) {
        this.fcRxFrameRate = fcRxFrameRate;
    }
    /**
     * @return the fcTxFrameRate
     */
    public BigInteger getFcTxFrameRate() {
        return fcTxFrameRate;
    }
    /**
     * @param fcTxFrameRate the fcTxFrameRate to set
     */
    public void setFcTxFrameRate(BigInteger fcTxFrameRate) {
        this.fcTxFrameRate = fcTxFrameRate;
    }
    /**
     * @return the fcRxByteRate
     */
    public BigInteger getFcRxByteRate() {
        return fcRxByteRate;
    }
    /**
     * @param fcRxByteRate the fcRxByteRate to set
     */
    public void setFcRxByteRate(BigInteger fcRxByteRate) {
        this.fcRxByteRate = fcRxByteRate;
    }
    /**
     * @return the fcTxByteRate
     */
    public BigInteger getFcTxByteRate() {
        return fcTxByteRate;
    }
    /**
     * @param fcTxByteRate the fcTxByteRate to set
     */
    public void setFcTxByteRate(BigInteger fcTxByteRate) {
        this.fcTxByteRate = fcTxByteRate;
    }
    /**
     * @return the fcTotalRxFrames
     */
    public BigInteger getFcTotalRxFrames() {
        return fcTotalRxFrames;
    }
    /**
     * @param fcTotalRxFrames the fcTotalRxFrames to set
     */
    public void setFcTotalRxFrames(BigInteger fcTotalRxFrames) {
        this.fcTotalRxFrames = fcTotalRxFrames;
    }
    /**
     * @return the fcTotalTxFrames
     */
    public BigInteger getFcTotalTxFrames() {
        return fcTotalTxFrames;
    }
    /**
     * @param fcTotalTxFrames the fcTotalTxFrames to set
     */
    public void setFcTotalTxFrames(BigInteger fcTotalTxFrames) {
        this.fcTotalTxFrames = fcTotalTxFrames;
    }
    /**
     * @return the fcAddressErrors
     */
    public BigInteger getFcAddressErrors() {
        return fcAddressErrors;
    }
    /**
     * @param fcAddressErrors the fcAddressErrors to set
     */
    public void setFcAddressErrors(BigInteger fcAddressErrors) {
        this.fcAddressErrors = fcAddressErrors;
    }
    /**
     * @return the fcClass2RxFrames
     */
    public BigInteger getFcClass2RxFrames() {
        return fcClass2RxFrames;
    }
    /**
     * @param fcClass2RxFrames the fcClass2RxFrames to set
     */
    public void setFcClass2RxFrames(BigInteger fcClass2RxFrames) {
        this.fcClass2RxFrames = fcClass2RxFrames;
    }
    /**
     * @return the fcClass2TxFrames
     */
    public BigInteger getFcClass2TxFrames() {
        return fcClass2TxFrames;
    }
    /**
     * @param fcClass2TxFrames the fcClass2TxFrames to set
     */
    public void setFcClass2TxFrames(BigInteger fcClass2TxFrames) {
        this.fcClass2TxFrames = fcClass2TxFrames;
    }
    /**
     * @return the fcClass3RxFrames
     */
    public BigInteger getFcClass3RxFrames() {
        return fcClass3RxFrames;
    }
    /**
     * @param fcClass3RxFrames the fcClass3RxFrames to set
     */
    public void setFcClass3RxFrames(BigInteger fcClass3RxFrames) {
        this.fcClass3RxFrames = fcClass3RxFrames;
    }
    /**
     * @return the fcClass3TxFrames
     */
    public BigInteger getFcClass3TxFrames() {
        return fcClass3TxFrames;
    }
    /**
     * @param fcClass3TxFrames the fcClass3TxFrames to set
     */
    public void setFcClass3TxFrames(BigInteger fcClass3TxFrames) {
        this.fcClass3TxFrames = fcClass3TxFrames;
    }
    /**
     * @return the fcClass3Discards
     */
    public BigInteger getFcClass3Discards() {
        return fcClass3Discards;
    }
    /**
     * @param fcClass3Discards the fcClass3Discards to set
     */
    public void setFcClass3Discards(BigInteger fcClass3Discards) {
        this.fcClass3Discards = fcClass3Discards;
    }
    /**
     * @return the fcInvalidCRC
     */
    public BigInteger getFcInvalidCRC() {
        return fcInvalidCRC;
    }
    /**
     * @param fcInvalidCRC the fcInvalidCRC to set
     */
    public void setFcInvalidCRC(BigInteger fcInvalidCRC) {
        this.fcInvalidCRC = fcInvalidCRC;
    }
    /**
     * @return the fcFramesTooLong
     */
    public BigInteger getFcFramesTooLong() {
        return fcFramesTooLong;
    }
    /**
     * @param fcFramesTooLong the fcFramesTooLong to set
     */
    public void setFcFramesTooLong(BigInteger fcFramesTooLong) {
        this.fcFramesTooLong = fcFramesTooLong;
    }
    /**
     * @return the fcFramesTruncated
     */
    public BigInteger getFcFramesTruncated() {
        return fcFramesTruncated;
    }
    /**
     * @param fcFramesTruncated the fcFramesTruncated to set
     */
    public void setFcFramesTruncated(BigInteger fcFramesTruncated) {
        this.fcFramesTruncated = fcFramesTruncated;
    }
    /**
     * @return the fcFRJTFrames
     */
    public BigInteger getFcFRJTFrames() {
        return fcFRJTFrames;
    }
    /**
     * @param fcFRJTFrames the fcFRJTFrames to set
     */
    public void setFcFRJTFrames(BigInteger fcFRJTFrames) {
        this.fcFRJTFrames = fcFRJTFrames;
    }
    /**
     * @return the fcFBSYFrames
     */
    public BigInteger getFcFBSYFrames() {
        return fcFBSYFrames;
    }
    /**
     * @param fcFBSYFrames the fcFBSYFrames to set
     */
    public void setFcFBSYFrames(BigInteger fcFBSYFrames) {
        this.fcFBSYFrames = fcFBSYFrames;
    }
    /**
     * @return the fcTotalRxBytes
     */
    public BigInteger getFcTotalRxBytes() {
        return fcTotalRxBytes;
    }
    /**
     * @param fcTotalRxBytes the fcTotalRxBytes to set
     */
    public void setFcTotalRxBytes(BigInteger fcTotalRxBytes) {
        this.fcTotalRxBytes = fcTotalRxBytes;
    }
    /**
     * @return the fcTotalTxBytes
     */
    public BigInteger getFcTotalTxBytes() {
        return fcTotalTxBytes;
    }
    /**
     * @param fcTotalTxBytes the fcTotalTxBytes to set
     */
    public void setFcTotalTxBytes(BigInteger fcTotalTxBytes) {
        this.fcTotalTxBytes = fcTotalTxBytes;
    }
    /**
     * @return the fcBBCreditFrameFailures
     */
    public BigInteger getFcBBCreditFrameFailures() {
        return fcBBCreditFrameFailures;
    }
    /**
     * @param fcBBCreditFrameFailures the fcBBCreditFrameFailures to set
     */
    public void setFcBBCreditFrameFailures(BigInteger fcBBCreditFrameFailures) {
        this.fcBBCreditFrameFailures = fcBBCreditFrameFailures;
    }
    /**
     * @return the fcBBCreditRRDYFailures
     */
    public BigInteger getFcBBCreditRRDYFailures() {
        return fcBBCreditRRDYFailures;
    }
    /**
     * @param fcBBCreditRRDYFailures the fcBBCreditRRDYFailures to set
     */
    public void setFcBBCreditRRDYFailures(BigInteger fcBBCreditRRDYFailures) {
        this.fcBBCreditRRDYFailures = fcBBCreditRRDYFailures;
    }
    /**
     * @return the fcLinkFailures
     */
    public BigInteger getFcLinkFailures() {
        return fcLinkFailures;
    }
    /**
     * @param fcLinkFailures the fcLinkFailures to set
     */
    public void setFcLinkFailures(BigInteger fcLinkFailures) {
        this.fcLinkFailures = fcLinkFailures;
    }
    /**
     * @return the fcRxLinkResets
     */
    public BigInteger getFcRxLinkResets() {
        return fcRxLinkResets;
    }
    /**
     * @param fcRxLinkResets the fcRxLinkResets to set
     */
    public void setFcRxLinkResets(BigInteger fcRxLinkResets) {
        this.fcRxLinkResets = fcRxLinkResets;
    }
    /**
     * @return the fcTxLinkResets
     */
    public BigInteger getFcTxLinkResets() {
        return fcTxLinkResets;
    }
    /**
     * @param fcTxLinkResets the fcTxLinkResets to set
     */
    public void setFcTxLinkResets(BigInteger fcTxLinkResets) {
        this.fcTxLinkResets = fcTxLinkResets;
    }
    /**
     * @return the fcNumberLinkResets
     */
    public BigInteger getFcNumberLinkResets() {
        return fcNumberLinkResets;
    }
    /**
     * @param fcNumberLinkResets the fcNumberLinkResets to set
     */
    public void setFcNumberLinkResets(BigInteger fcNumberLinkResets) {
        this.fcNumberLinkResets = fcNumberLinkResets;
    }
    /**
     * @return the fcLossOfSynchronization
     */
    public BigInteger getFcLossOfSynchronization() {
        return fcLossOfSynchronization;
    }
    /**
     * @param fcLossOfSynchronization the fcLossOfSynchronization to set
     */
    public void setFcLossOfSynchronization(BigInteger fcLossOfSynchronization) {
        this.fcLossOfSynchronization = fcLossOfSynchronization;
    }
    /**
     * @return the fcRxOfflineSequences
     */
    public BigInteger getFcRxOfflineSequences() {
        return fcRxOfflineSequences;
    }
    /**
     * @param fcRxOfflineSequences the fcRxOfflineSequences to set
     */
    public void setFcRxOfflineSequences(BigInteger fcRxOfflineSequences) {
        this.fcRxOfflineSequences = fcRxOfflineSequences;
    }
    /**
     * @return the fcTxOfflineSequences
     */
    public BigInteger getFcTxOfflineSequences() {
        return fcTxOfflineSequences;
    }
    /**
     * @param fcTxOfflineSequences the fcTxOfflineSequences to set
     */
    public void setFcTxOfflineSequences(BigInteger fcTxOfflineSequences) {
        this.fcTxOfflineSequences = fcTxOfflineSequences;
    }
    /**
     * @return the fcNumberOfflineSequences
     */
    public BigInteger getFcNumberOfflineSequences() {
        return fcNumberOfflineSequences;
    }
    /**
     * @param fcNumberOfflineSequences the fcNumberOfflineSequences to set
     */
    public void setFcNumberOfflineSequences(BigInteger fcNumberOfflineSequences) {
        this.fcNumberOfflineSequences = fcNumberOfflineSequences;
    }
    /**
     * @return the fcPrimitiveSeqProtocolErrors
     */
    public BigInteger getFcPrimitiveSeqProtocolErrors() {
        return fcPrimitiveSeqProtocolErrors;
    }
    /**
     * @param fcPrimitiveSeqProtocolErrors the fcPrimitiveSeqProtocolErrors to set
     */
    public void setFcPrimitiveSeqProtocolErrors(BigInteger fcPrimitiveSeqProtocolErrors) {
        this.fcPrimitiveSeqProtocolErrors = fcPrimitiveSeqProtocolErrors;
    }
    /**
     * @return the fcInvalidTxWords
     */
    public BigInteger getFcInvalidTxWords() {
        return fcInvalidTxWords;
    }
    /**
     * @param fcInvalidTxWords the fcInvalidTxWords to set
     */
    public void setFcInvalidTxWords(BigInteger fcInvalidTxWords) {
        this.fcInvalidTxWords = fcInvalidTxWords;
    }
    /**
     * @return the fcSmoothingOverflowErrors
     */
    public BigInteger getFcSmoothingOverflowErrors() {
        return fcSmoothingOverflowErrors;
    }
    /**
     * @param fcSmoothingOverflowErrors the fcSmoothingOverflowErrors to set
     */
    public void setFcSmoothingOverflowErrors(BigInteger fcSmoothingOverflowErrors) {
        this.fcSmoothingOverflowErrors = fcSmoothingOverflowErrors;
    }
    /**
     * @return the fcDecodeErrors
     */
    public BigInteger getFcDecodeErrors() {
        return fcDecodeErrors;
    }
    /**
     * @param fcDecodeErrors the fcDecodeErrors to set
     */
    public void setFcDecodeErrors(BigInteger fcDecodeErrors) {
        this.fcDecodeErrors = fcDecodeErrors;
    }
    /**
     * @return the extendedStatistics
     */
    public Map<String, String> getExtendedStatistics() {
        return extendedStatistics;
    }
    /**
     * @param extendedStatistics the extendedStatistics to set
     */
    public void setExtendedStatistics(Map<String, String> extendedStatistics) {
        this.extendedStatistics = extendedStatistics;
    }

}
