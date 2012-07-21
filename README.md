Spring Framework on OpenShift with JBoss EAP 6
==============================================

What is it?
-----------

This is a sample Maven 3 project to help you get your foot in the door developing with Spring on JBoss Enterprise Application Platform 6 or JBoss AS 7.1, deployable on OpenShift (PaaS by Red Hat).

This project is setup to allow you to create a compliant Spring 3.1 application using Spring MVC, JPA 2.0 and Bean Validation 1.0. It includes a persistence unit and some sample persistence and transaction code to introduce you to database access in enterprise Java. 

Quickstart
----------

1) Create an account at http://openshift.redhat.com/ and follow the Getting Started guide to install the OpenShift command line tools.

2) Create a JBoss Enterprise Application Platform 6 app:

    rhc app create -a spring -t jbosseap-6.0

3) Add this upstream repo:

    cd spring
    git remote add upstream -m master git://github.com/openshift/spring-eap6-quickstart.git
    git pull -s recursive -X theirs upstream master

4) Remove the default index.html file and commit:

    git rm src/main/webapp/index.html
    git commit -m 'Removed default index.html'

5) Then push the repo upstream:

    git push

6) That's it, you can now browse to your application at:

    http://spring-$yournamespace.rhcloud.com

Setting up a database
---------------------

The example uses a H2 database configured and deployed by the application. You can easily change it to MySQL or PostgreSQL (available on OpenShift as cartridges):

1) Add a database cartridge to your OpenShift app (mysql-5.1 or postgresql-8.4):

    rhc app cartridge add -a spring -c mysql-5.1

2) Edit `src/main/resources/META-INF/persistence.xml` to use the appropriate datasource (java:jboss/datasources/MysqlDS or java:jboss/datasources/PostgreSQLDS):

    <jta-data-source>java:jboss/datasources/MysqlDS</jta-data-source>

3) Commit and push your changes:

    git commit -a -m 'Added MySQL database support'
    git push


