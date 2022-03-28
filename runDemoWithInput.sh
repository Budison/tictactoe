#!/bin/bash

if [ "$#" -ne 6 ]
then
  echo "Invalid arguments provided! To run this script you have to provide six arguments:"
  echo "1. Board dimension"
  echo "2. Winning condition"
  echo "3. Starting player (X or O)"
  echo "4. Winning player (X or O)"
  echo "5. Winning type (e.g. horizontal)"
  echo "6. Winning line"
  
  exit 1
fi


mvn clean package
java -jar target/*.jar $1 $2 $3 $4 $5 $6
