#!/bin/bash
#builds and installs seqs on the system

# build the maven project
mvn clean install

# copy jar to usr/local/bin
cp target/seqs-*-jar-with-dependencies.jar /usr/local/bin/seqs.jar

# copy launch script to /usr/local/bin and make executable
cp seqs.sh /usr/local/bin/seqs
chmod +x /usr/local/bin/seqs

echo "seqs is now installed on your system"
echo "try 'seqs -h'"
