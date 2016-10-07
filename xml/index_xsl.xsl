<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns="http://www.w3.org/1999/xhtml" >

 <xsl:output method="xml" />
 <xsl:template match="/">
  <html>
    <body>  
      <h1 align="center">XML course SHOW Pages</h1>
      <h2 align="center">For Group 8 - Sandalphon!</h2>
      <h2 align="center">To write the design of DTD, schema and some codes about XML in the course of XML Technology of SEU.</h2>
      <div align="center">
        <img class="img-reponsive" src="images/logo.jpg" alt="Roger Garfield" align="center" />
      </div>
      <h2>Member</h2>


      <table border="1">
       <tr bgcolor="#9acd32">
         <th>Name</th>
         <th>Student ID</th>
         <th>Phone Number</th>
         <th>E-mail</th>
       </tr>
       <xsl:for-each select="group/member/person">
       <xsl:sort select="stu_number" /><!-- >sort by the stu_number<-->
         <tr>
          <td><xsl:value-of select="name"/></td>
          <td><xsl:value-of select="stu_number"/></td>
          <td><xsl:value-of select="phone_number"/></td>
          <td><xsl:value-of select="email"/></td>
        </tr>
      </xsl:for-each>
    </table>
    
    <h2>If Student Number > 71113320</h2>
     <table border="1">
       <tr bgcolor="#9acd32">
         <th>Name</th>
         <th>Student ID</th>
       </tr>
       <xsl:for-each select="group/member/person">
       <xsl:if test="stu_number &gt; 71113320">
         <tr>
          <td><xsl:value-of select="name"/></td>
          <td><xsl:value-of select="stu_number"/></td>
        </tr>
        </xsl:if>
      </xsl:for-each>
    </table>
    
    <h2>When Student Number > 71113318</h2>
     <table border="1">
       <tr bgcolor="#9acd32">
         <th>Name</th>
         <th>Student ID</th>
       </tr>
       <xsl:for-each select="group/member/person">
       
         <tr>
         
          <td><xsl:value-of select="name"/></td>
          <xsl:choose>
            <xsl:when test="stu_number &gt; 71113318">
                <td bgcolor="#ff00ff">
                <xsl:value-of select="stu_number"/></td>
            </xsl:when>
            <xsl:otherwise>
                <td><xsl:value-of select="stu_number"/></td>
            </xsl:otherwise>
          </xsl:choose>
        </tr>
      </xsl:for-each>
    </table>
    
    
    
    <h2>About Us</h2>
    <p><a href="http://github.com/Yvon-Shong/xml_course">Our Github Repo</a></p>
    <p><a href="http://yvon-shong.github.io" >Our Page Written by HTML</a></p>
    <p><a href="view-source:http://yvon-shong.github.io/xml/index_main.xsl" >The source code of the xsl (by Chrome)</a></p>
    
    
  </body>
</html>
</xsl:template>

</xsl:stylesheet>