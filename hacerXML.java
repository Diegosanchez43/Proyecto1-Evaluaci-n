import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class hacerXML
{
    private static class Resultado
    {
        String nombre;
        int experiencia;

        Resultado(String nombre, int experiencia)
        {
            this.nombre = nombre;
            this.experiencia = experiencia;
        }
    }

    public static void crearXML()
    {
        try
        {
            File archivo = new File("ranking.XML");
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder constructor = fabrica.newDocumentBuilder();

            if (archivo.exists())
            {
                Document existente = constructor.parse(archivo);
                if ("ranking".equals(existente.getDocumentElement().getTagName()))
                {
                    generarHTML();
                    return;
                }
            }

            Document documento = constructor.newDocument();
            Element raiz = documento.createElement("ranking");
            documento.appendChild(raiz);

            TransformerFactory transformerFabrica = TransformerFactory.newInstance();
            Transformer transformer = transformerFabrica.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource origen = new DOMSource(documento);
            StreamResult destino = new StreamResult(archivo);
            transformer.transform(origen, destino);

            generarHTML();
            System.out.println("Ranking XML generado correctamente");
        }
        catch (Exception e)
        {
            System.out.println("Error al generar el ranking XML: " + e.getMessage());
        }
    }

    public static void guardarXML()
    {
        try
        {
            File archivo = new File("ranking.XML");
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivo);
            documento.getDocumentElement().normalize();

            NodeList lista = documento.getElementsByTagName("partida");
            System.out.println("Partidas guardadas: " + lista.getLength());

            for (int i = 0; i < lista.getLength(); i++)
            {
                Element partida = (Element) lista.item(i);
                String nombre = partida.getElementsByTagName("nombre").item(0).getTextContent();
                String experiencia = partida.getElementsByTagName("experiencia").item(0).getTextContent();

                System.out.println("\nPartida " + partida.getAttribute("id"));
                System.out.println("Jugador: " + nombre);
                System.out.println("Experiencia: " + experiencia);
            }

            generarHTML();
        }
        catch (Exception e)
        {
            System.out.println("Error al leer el ranking XML: " + e.getMessage());
        }
    }

    public static void escribirXML(String nombre, int puntosPartida)
    {
        try
        {
            File archivo = new File("ranking.XML");
            if (!archivo.exists())
            {
                crearXML();
            }

            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivo);
            documento.getDocumentElement().normalize();

            NodeList lista = documento.getElementsByTagName("partida");
            Element partida = documento.createElement("partida");
            partida.setAttribute("id", Integer.toString(lista.getLength() + 1));

            Element elementoNombre = documento.createElement("nombre");
            elementoNombre.setTextContent(nombre);
            partida.appendChild(elementoNombre);

            Element experiencia = documento.createElement("experiencia");
            experiencia.setTextContent(Integer.toString(puntosPartida));
            partida.appendChild(experiencia);

            documento.getDocumentElement().appendChild(partida);

            TransformerFactory transformerFabrica = TransformerFactory.newInstance();
            Transformer transformer = transformerFabrica.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

            DOMSource origen = new DOMSource(documento);
            StreamResult destino = new StreamResult(archivo);
            transformer.transform(origen, destino);

            generarHTML();
            System.out.println("Partida guardada correctamente");
        }
        catch (Exception e)
        {
            System.out.println("Error al guardar la partida: " + e.getMessage());
        }
    }

    private static void generarHTML()
    {
        try
        {
            File archivo = new File("ranking.XML");
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            fabrica.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document documento = constructor.parse(archivo);
            NodeList partidas = documento.getElementsByTagName("partida");
            List<Resultado> ranking = new ArrayList<>();

            for (int i = 0; i < partidas.getLength(); i++)
            {
                Element partida = (Element) partidas.item(i);
                String nombre = partida.getElementsByTagName("nombre").item(0).getTextContent().trim();
                int experiencia = Integer.parseInt(
                        partida.getElementsByTagName("experiencia").item(0).getTextContent().trim());
                ranking.add(new Resultado(nombre, experiencia));
            }

            Collections.sort(ranking, new Comparator<Resultado>()
            {
                public int compare(Resultado primero, Resultado segundo)
                {
                    int porExperiencia = Integer.compare(segundo.experiencia, primero.experiencia);
                    return porExperiencia != 0
                            ? porExperiencia
                            : primero.nombre.compareToIgnoreCase(segundo.nombre);
                }
            });

            try (BufferedWriter salida = Files.newBufferedWriter(
                    new File("ranking.html").toPath(), StandardCharsets.UTF_8))
            {
                salida.write("<!DOCTYPE html>\n<html lang=\"es\">\n<head>\n");
                salida.write("<meta charset=\"UTF-8\">\n");
                salida.write("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n");
                salida.write("<title>Ranking de Eldoria</title>\n");
                salida.write("<style>body{font-family:Arial,sans-serif;background:#101827;color:#f3f4f6;margin:0;padding:40px 16px}"
                        + "main{max-width:800px;margin:auto}h1{text-align:center;color:#f5cf82}"
                        + "table{width:100%;border-collapse:collapse;background:#1b2940}"
                        + "th,td{padding:14px;text-align:left;border-bottom:1px solid #34445a}"
                        + "th{color:#f5cf82;background:#293c56}.vacio{text-align:center;padding:24px}"
                        + "</style>\n</head>\n<body>\n<main>\n");
                salida.write("<h1>Ranking de Aventura en Eldoria</h1>\n");

                if (ranking.isEmpty())
                {
                    salida.write("<p class=\"vacio\">Todavía no hay partidas registradas.</p>\n");
                }
                else
                {
                    salida.write("<table>\n<thead><tr><th>Posición</th><th>Jugador</th><th>Experiencia</th></tr></thead>\n<tbody>\n");
                    for (int i = 0; i < ranking.size(); i++)
                    {
                        Resultado resultado = ranking.get(i);
                        salida.write("<tr><td>" + (i + 1) + "</td><td>"
                                + escaparHTML(resultado.nombre) + "</td><td>"
                                + resultado.experiencia + "</td></tr>\n");
                    }
                    salida.write("</tbody>\n</table>\n");
                }

                salida.write("<footer>Datos generados desde ranking.XML</footer>\n");
                salida.write("</main>\n</body>\n</html>\n");
            }
        }
        catch (Exception e)
        {
            System.out.println("Error al generar ranking.html: " + e.getMessage());
        }
    }

    private static String escaparHTML(String texto)
    {
        return texto.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}
