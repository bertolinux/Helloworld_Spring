<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<style>
.left {
	position: relative;
    background: #C0EDff;
    font-size: 16px;
    margin-left: 2px;
    margin-right: 2px;
    display: block;
    float: left;
    width: 48%;
    height: 30%;
    text-align: center;
}

.right {
	position: relative;
    background: #C0EAff;
    font-size: 16px;
    margin-left: 2px;
    margin-right: 2px;
    display: block;
    float: left;
    width: 48%;
    height: 30%;
    text-align: center;
    border-left: 5px
}
</style>

<div class="left">
	<tiles:insertAttribute name="left"/>
</div>

<div class="right">
	<tiles:insertAttribute name="right"/>
</div>				

