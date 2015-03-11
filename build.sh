#!/bin/sh

(cd dropwizpoc && mvn clean install)
cp dropwizpoc/example.yml .
cp dropwizpoc/target/dropwizpoc-1.0-SNAPSHOT.jar .

docker build .

rm example.yml
rm dropwizpoc-1.0-SNAPSHOT.jar

