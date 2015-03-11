FROM dockerfile/java:oracle-java8
MAINTAINER rmz@telenordigital.com
RUN mkdir ~/servicedir
ADD dropwizpoc/target/dropwizpoc-1.0-SNAPSHOT.jar ~/servicedir
ADD dropwizpoc/example.yml  ~/servicedir 
WORKDIR ~/servicedir

RUN ls -la

# Expose application server port
EXPOSE 8080

# expose administrative port
EXPOSE 8081

##
## XXX   We're missing the SNMP agent setup here!
##


RUN java -jar dropwizpoc-1.0-SNAPSHOT.jar server example.yml


