<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
 <html>
 <body>
 <h1>Catálogo de Productos</h1>
 <table border="1">
 <tr>
 <th>Modelo</th>
 <th>Marca</th>
 <th>Precio</th>
 </tr>
 <xsl:for-each select="catalogo/producto">
 <tr>
 <td>
 <xsl:value-of select="modelo"/>
 </td>
 <td>
 <xsl:value-of select="marca"/>
 </td>
 <td>
 <xsl:value-of select="precio"/>
 </td>
 </tr>
 </xsl:for-each>
 </table>
 </body>
 </html>
</xsl:template>
</xsl:stylesheet>

