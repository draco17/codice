
<%@ page import="com.synergyj.codice.news.NewsItem" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
        <meta name="layout" content="main" />
        <title>NewsItem List</title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLinkTo(dir:'')}">Home</a></span>
            <span class="menuButton"><g:link class="create" action="create">New NewsItem</g:link></span>
        </div>
        <div class="body">
            <h1>NewsItem List</h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                   	        <g:sortableColumn property="id" title="Id" />
                        
                   	        <g:sortableColumn property="title" title="Title" />
                        
                   	        <g:sortableColumn property="body" title="Body" />
                        
                   	        <g:sortableColumn property="allowComments" title="Allow Comments" />
                        
                   	        <g:sortableColumn property="created" title="Created" />
                        
                   	        <g:sortableColumn property="lastUpdated" title="Last Updated" />
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${newsItemInstanceList}" status="i" var="newsItemInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${newsItemInstance.id}">${fieldValue(bean:newsItemInstance, field:'id')}</g:link></td>
                        
                            <td>${fieldValue(bean:newsItemInstance, field:'title')}</td>
                        
                            <td>${fieldValue(bean:newsItemInstance, field:'body')}</td>
                        
                            <td>${fieldValue(bean:newsItemInstance, field:'allowComments')}</td>
                        
                            <td>${fieldValue(bean:newsItemInstance, field:'created')}</td>
                        
                            <td>${fieldValue(bean:newsItemInstance, field:'lastUpdated')}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${newsItemInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
