#!/bin/bash

export MYSQL_IP=$1

result="\"status\":\"RUNNING\""
job="dummyjob"


cd src/main/resources/db/setup
scp -i mysqlkey -o StrictHostKeyChecking=no ../migration/V001__mysql_setup.sql opc@${MYSQL_IP}:/tmp/V001__mysql_setup.sql
ssh -i mysqlkey -o StrictHostKeyChecking=no opc@${MYSQL_IP} sudo su - oracle -c 'mysql "catalog" < "/tmp/mysql_setup.sql"'

echo "..done."