# Sample applications using  Oracle Integration technologies
More in the associated blog posts on http://weblog.singhpora.com
## Oracle Integration Cloud
## Oracle SOA
## Oracle Service Bus

--------------------
### Sample: Recursive calls in Oracle Integration Cloud Flows (Scenario: Paginated API for large Data Sets)
https://weblog.singhpora.com/2020/05/recursive-paginated-calls-in-oracle-integration-cloud.html

Location: OIC/Recursive

--------------------
### Sample: A very simple, mavenised Oracle SOA 12c Composite with an associated, CI-ready citrus test harness.
http://weblog.singhpora.com/2016/10/ws-security-enabling-passworddigest.html
http://weblog.singhpora.com/2016/09/test-driven-soa-citrus-for-powerful-soa.html

**To build+deploy+test, just run "mvn integration-test" from the application level
(provide serverUrl, user and password in the 
SOAComposite pom or from the environment e.g. -DserverUrl=http://soahost:soaport)

**To only run the integration tests, just run "mvn integration-test" from the SOACompositeTests level. 

**This repository also contains a shared-metadata section (a source controlled, offline version 
of the SOA MDS). 

**For more on Citrus, see:
http://www.citrusframework.org/

**To get hands-on, the maven archetype is a good starting point - allows you to create the shell
projects for SOAP and JMS testing:
http://www.citrusframework.org/reference/html/#setup-maven-archetype

--------------------
### Service Bus - a simple 'Oracle Diagnostics Logging (ODL)' processor that
#extracts specific information from diagnistics logs and persists these into 
#the database 

Located in: OSB/SBAudit

More on Oracle Diagnostics Logging:
https://docs.oracle.com/middleware/12211/lcm/ASADM/logs.htm#ASADM215
