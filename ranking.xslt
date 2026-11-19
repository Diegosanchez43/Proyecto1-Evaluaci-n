<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
    <xsl:output method="html" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <html lang="es">
            <head>
                <title>Ranking de Eldoria</title>
                <style>
                    body { font-family: Arial, sans-serif; background: #101827; color: #f3f4f6; margin: 0; padding: 40px 16px; }
                    main { max-width: 800px; margin: auto; }
                    h1 { text-align: center; color: #f5cf82; }
                    table { width: 100%; border-collapse: collapse; background: #1b2940; }
                    th, td { padding: 14px; text-align: left; border-bottom: 1px solid #34445a; }
                    th { color: #f5cf82; background: #293c56; }
                    .vacio { text-align: center; padding: 24px; }
                </style>
            </head>
            <body>
                <main>
                    <h1>Ranking de Aventura en Eldoria</h1>
                    <xsl:choose>
                        <xsl:when test="ranking/partida">
                            <table>
                                <thead>
                                    <tr>
                                        <th>Posición</th>
                                        <th>Jugador</th>
                                        <th>Experiencia</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <xsl:for-each select="ranking/partida">
                                        <xsl:sort select="number(experiencia)" data-type="number" order="descending"/>
                                        <tr>
                                            <td><xsl:value-of select="position()"/></td>
                                            <td><xsl:value-of select="experiencia"/></td>
                                        </tr>
                                    </xsl:for-each>
                                </tbody>
                            </table>
                        </xsl:when>
                    </xsl:choose>
                </main>
            </body>
        </html>
    </xsl:template>
</xsl:stylesheet>
