<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<section class="row sjp-index-section">
	<div class="col-12">
		<h2><spring:message code="sjp.showcases.data.science.title" /></h2>
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.first.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.first.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/1" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="sringJpFile" items="${ listFiles }">
					<span>${ sringJpFile.name }</span><br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.second.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.second.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/2" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="sringJpFileApacheCommonsIO" items="${ listFilesApacheCommonsIO }">
					<span>${ sringJpFileApacheCommonsIO.name }</span><br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.third.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.third.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/3" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="java8StreamListItem" items="${ java8StreamList }">
					<span>${ java8StreamListItem }</span><br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.fourth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.fourth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/4" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:out value="${ apacheCommonsIODummyText }" />
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.fifth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.fifth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/5" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:out value="${ dummyPDFText }" />
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.sixth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.sixth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/6" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:out value="${ dummyASCIIToCleanText }" /><br/>
				<c:out value="${ dummyASCIICleanText }" />
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.seventh.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.seventh.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/7" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="csvRow" items="${ csvResult }">
					<c:forEach varStatus="loop" var="rowItem" items="${ csvRow }">
						<c:out value="${ rowItem }" /><c:if test="${ loop.index < fn:length(csvRow)-1 }">,</c:if>
					</c:forEach>
					<br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.eighth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.eighth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/8" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="tsvRow" items="${ tsvResult }">
					<c:forEach varStatus="loop" var="rowItem" items="${ tsvRow }">
						<c:out value="${ rowItem }" /><c:if test="${ loop.index < fn:length(tsvRow)-1 }">&emsp;</c:if>
					</c:forEach>
					<br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.nineth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.nineth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/9" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="author" items="${ xmlResult }">
					<c:out value="${ author }" /><br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.tenth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.tenth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/10" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:out value="${ jsonWriterResult }" />
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.eleventh.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.eleventh.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/11" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="jsonProp" items="${ jsonReaderResult }">
					<c:out value="${jsonProp }" /><br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.twelveth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.twelveth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/12" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:forEach var="jsoupLine" items="${ jsoupResult }">
					<c:out value="${jsoupLine }" /><br/>
				</c:forEach>
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.thirteenth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.thirteenth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/1/13" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<c:out value="${ seleniumResult }" />
			</div>
		</div>	
	</div>
</section>
<hr>
<section class="row sjp-index-section">
	<div class="col-12">
		<div class="row">
			<div class="col-12">
				<h3><spring:message code="sjp.showcases.data.science.thirteenth.title" /></h3>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<p class="text-justify"><spring:message code="sjp.showcases.data.science.thirteenth.description" /></p>
			</div>
		</div>
		<div class="row">
			<div class="col-12">
				<a href="<spring:url value="/showcases/data-science/2/1" />" ><spring:message code="sjp.showcases.data.science.testClick" /> <i class="fa fa-arrow-right" aria-hidden="true"></i></a>
			</div>
		</div>
		<div class="row">
			<div class="col-12 sjp-datascience-listfile-panel">
				<div class="col-12 sjp-datascience-listfile-panel">
					<c:forEach var="executeIndexingScreenLog" items="${ executeIndexingScreenLogs }">
						<c:out value="${ executeIndexingScreenLog }" /><br/>
					</c:forEach>
				</div>
			</div>
		</div>	
	</div>
</section>