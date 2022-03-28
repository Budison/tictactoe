#!/bin/bash

mvn clean package
java -jar target/*.jar $0 $1 $2 $3 $4
