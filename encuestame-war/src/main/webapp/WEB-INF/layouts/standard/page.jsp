<%@ include file="/WEB-INF/jsp/includes/taglibs.jsp"%>
<!DOCTYPE html>
<!--[if lt IE 7]>  <html class="ie ie6 lte9 lte8 lte7"> <![endif]-->
<!--[if IE 7]>     <html class="ie ie7 lte9 lte8 lte7"> <![endif]-->
<!--[if IE 8]>     <html class="ie ie8 lte9 lte8"> <![endif]-->
<!--[if IE 9]>     <html class="ie ie9 lte9"> <![endif]-->
<!--[if gt IE 9]>  <html> <![endif]-->
<!--[if !IE]><!--> <html>             <!--<![endif]-->
<head>
    <title>
        <tiles:insertAttribute name="title" defaultValue="encuestame" />
    </title>
    <%@ include file="/WEB-INF/jsp/includes/meta.jsp" %>
    <%@ include file="/WEB-INF/jsp/includes/web/css.jsp" %>
      <!-- All JavaScript at the bottom, except for Modernizr / Respond.
       Modernizr enables HTML5 elements & feature detects; Respond is a polyfill for min/max-width CSS3 Media Queries-->
    <script src="<%=request.getContextPath()%>/resources/js/req/modernizr-2.0.6.js"></script>
    <tiles:insertAttribute name="rss" ignore="true" />
</head>
<body class="claro enme-web-context">
     <header id="header">
         <tiles:insertAttribute name="header" ignore="true" />
         <tiles:insertAttribute name="menu" ignore="true" />
     </header>
     <div id="mainWrapper">
        <div id="content-container" class="enme-auto-center">
            <div id="enme-content" class="enme-auto-center">
                <tiles:insertAttribute name="content"/>
            </div>
        </div>
     </div>
     <footer id="footer">
          <tiles:insertAttribute name="footer" />
     </footer>
    <%@ include file="/WEB-INF/jsp/includes/javascript.jsp" %>
    <script type="text/javascript">
         dojo.require("dijit.dijit");
         dojo.require("dojo.parser");
         dojo.require("dojo.io.script");
         dojo.require("encuestame.org.core.commons");
         dojo.require("encuestame.org.core.commons.search.SearchMenu");
         dojo.require("encuestame.org.core.commons.error.ErrorSessionHandler");
         dojo.require("encuestame.org.core.commons.error.ErrorConexionHandler");
         dojo.require("encuestame.org.core.commons.error.ErrorHandler");
         <c:if test="${logged}">
             dojo.require("encuestame.org.activity.Activity");
             dojo.require("encuestame.org.core.commons.dashboard.DashBoardMenu");
             dojo.require("encuestame.org.core.commons.notifications.Notification");
             dojo.require("encuestame.org.core.commons.profile.ProfileMenu");
             dojo.require("dojox.widget.Toaster");
             encuestame.activity = new encuestame.org.activity.Activity(true);
         </c:if>
    </script>
    <!-- Insert additional javascript  -->
    <tiles:insertAttribute name="extra-js" ignore="true"/>
    <div id="modal-box"></div>
</body>
</html>