FROM dockerfile/java:oracle-java8
MAINTAINER rmz@telenordigital.com
RUN mkdir /servicedir
ADD dropwizpoc-1.0-SNAPSHOT.jar /servicedir/dropwizpoc-1.0-SNAPSHOT.jar
ADD example.yml  /servicedir/example.yml
WORKDIR /servicedir
RUN pwd
RUN ls -la

# Expose application server port
EXPOSE 8080

# expose administrative port
EXPOSE 8081

##
## XXX   We're missing the SNMP agent setup here!
##


CMD java -jar dropwizpoc-1.0-SNAPSHOT.jar server example.yml


