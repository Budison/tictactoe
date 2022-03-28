#!/bin/bash

mvn clean package
java -jar target/*.jar 3 3 X O ~
