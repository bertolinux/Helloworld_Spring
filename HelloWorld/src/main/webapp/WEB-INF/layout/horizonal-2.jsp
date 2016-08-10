<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<div id="left" style="position: absolute; background-color: lightgray; width: 50%; text-align:center">
	<tiles:insertAttribute name="left"/>
</div>

<div id="right" style="position: absolute; left: 50%; background-color: #F1E0C0; width: 50%; text-align:center">
	<tiles:insertAttribute name="right"/>
</div>				

