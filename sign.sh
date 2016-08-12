#!/bin/bash

# (C) Copyright 2016 Hewlett Packard Enterprise Development LP
#
# Licensed under the Apache License, Version 2.0 (the "License");
# You may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.

CLIENT_HOME=$1
KEYTAB_FILE=$2
ARTIFACT_ID=$3
PROJECT_VERSION=$4
DEBUG=$5

TARGET_DIR="target"
JAVA_DEFAULT="java -Djava.security.krb5.debug=true -jar $CLIENT_HOME/SignHPClient.jar sign -p ONEVIEW-SDK-JAVA"

function sign_file {
	local FILE=$1

	echo "[INFO] >>> Starting the signing process for file '$FILE'"
	echo "[INFO]"

	if [ "$DEBUG" = "debug" ]; then
		echo "[DEBUG] touch $TARGET_DIR/$FILE.sig"

		touch $TARGET_DIR/$FILE.sig
	else
		echo "[INFO] $JAVA_DEFAULT -i $TARGET_DIR/$FILE -o $TARGET_DIR -noextract"

		$JAVA_DEFAULT -i $TARGET_DIR/$FILE -o $TARGET_DIR -noextract
	fi

 	if [ $? -eq 0 ];
 		then
 		SIG=$TARGET_DIR/$FILE.sig
 		ASC=$SIG.asc

		if [ "$DEBUG" = "debug" ]; then
			echo "[DEBUG] mv $SIG $TARGET_DIR/$FILE.asc"

			mv $SIG $TARGET_DIR/$FILE.asc
		else
			echo "[INFO] GPG pack the signature file into an OpenPGP ASCII armor."
			echo "[INFO] gpg --enarmor $SIG && rm $SIG && mv $ASC $TARGET_DIR/$FILE.asc"

			gpg --enarmor $SIG && rm $SIG && mv $ASC $TARGET_DIR/$FILE.asc
		fi

 		if [ $? -ne 0 ];
 			then
 			echo "[ERROR] Error occurred while generating ASCII armored signature file"
 			exit 1
 		fi

 		echo "[INFO]"
		echo "[INFO] <<< File '$FILE' successfully signed!"
 	else
 		echo "[ERROR] Error occurred while executing the signing process for file '$FILE'"
 		exit 1
 	fi
}

function build_pom {
	if [ ! -d $TARGET_DIR ]; then
		mkdir $TARGET_DIR
	fi 

	POM_FILE=$TARGET_DIR/$ARTIFACT_ID-$PROJECT_VERSION.pom

	cp -f pom.xml $POM_FILE
}

function sign_files {
	for FILE in $( ls $TARGET_DIR | grep "oneview-sdk-java-" ); do
		 sign_file $FILE
	done
}

echo "[INFO] CLIENT_HOME >> '$CLIENT_HOME'"
echo "[INFO] KEYTAB_FILE >> '$KEYTAB_FILE'"
echo "[INFO] ARTIFACT_ID >> '$ARTIFACT_ID'"
echo "[INFO] PROJECT_VERSION >> '$PROJECT_VERSION'"
echo "[INFO] DEBUG >> '$DEBUG'"

if [ ! "$DEBUG" = "debug" ]; then
	KRB_TICKET=$(klist |& grep oneviewsdk001)

	if [ -z "$KRB_TICKET" ]; then
		echo "[INFO] Kerberos ticket not found"
		echo "[INFO] Obtaining and caching Kerberos ticket-granting ticket using keytab to authenticate"
		echo "[INFO] kinit -k -t $KEYTAB_FILE \$oneviewsdk001@AMERICAS.CPQCORP.NET"

		export KRB5_CONFIG=$CLIENT_HOME/krb5.ini

		echo "[INFO]" $(kinit -k -t $KEYTAB_FILE \$oneviewsdk001@AMERICAS.CPQCORP.NET)

		unset KRB5_CONFIG
	fi
fi

build_pom
sign_files
