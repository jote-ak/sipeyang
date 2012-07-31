<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>

<head>
    <title>Spring MVC Starter Application</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/static/resources/css/screen.css"/>"/>
</head>

<body>
<div id="container">
    <div class="dualbrand">
        <img src="<c:url value="/static/resources/gfx/dualbrand_logo.png"/>"/>
    </div>
    <div id="content">
        <h1>Welcome to JBoss!</h1>

        <div>
            <p>Tejo, You have successfully deployed a Spring MVC web application.</p>

            <h3>Your application can run on:</h3>
            <img src="<c:url value="/static/resources/gfx/dualbrand_as7eap.png"/>"/>
        </div>
        <h2>Members</h2>

    </div>
    <div id="aside">
        <p>Learn more about JBoss Enterprise Application Platform 6.</p>
        <ul>
            <li><a
                    href="http://red.ht/jbeap-6-docs">Documentation</a></li>
            <li><a href="http://red.ht/jbeap-6">Product Information</a></li>
        </ul>
        <p>Learn more about JBoss AS 7.</p>
        <ul>
            <li><a
                    href="https://docs.jboss.org/author/display/AS7/Getting+Started+Developing+Applications+Guide">Getting
                Started Developing Applications Guide</a></li>
            <li><a href="http://jboss.org/jbossas">Community Project Information</a></li>
        </ul>
    </div>
    <div id="footer">
        <p>
            This project was generated from a Maven archetype from
            JBoss.<br/>
        </p>
    </div>
</div>
</body>
</html>
