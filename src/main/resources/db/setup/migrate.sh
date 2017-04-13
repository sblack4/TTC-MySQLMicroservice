#!/bin/bash

export MYSQL_IP=$1

result="\"status\":\"RUNNING\""
job="dummyjob"


# scp file
cd src/main/resources/db/setup
scp -i mysqlkey -o StrictHostKeyChecking=no mysql_setup.sql opc@${MYSQL_IP}:/tmp/mysql_setup.sql
ssh -i mysqlkey -o StrictHostKeyChecking=no opc@${MYSQL_IP} 
sudo su - oracle 
mysql "catalog" < "/tmp/mysql_setup.sql"

echo "..done."
