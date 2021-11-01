package no.ntnu.iiir.mobapp.api.librarylocatorapi.xmlParsing;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.xml.crypto.dsig.TransformException;
import javax.xml.parsers.*;
import java.io.*;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

    public XMLParser() {

    }

    public static void main(String args[]) {
        XMLParser parser = new XMLParser();
        String isbn = "978-82-489-2327-5";
        parser.parseXML("47BIBSYS_NTNU_UB", isbn);
    }

    private List<String> parseXML(String librarySystem, String isbn) {
        List<String> list = new ArrayList<>();
        try {
            URL url = new URL("https://bibsys.alma.exlibrisgroup.com/view/sru/" + librarySystem + "?version=1.2&operation=searchRetrieve&recordSchema=marcxml&query=alma.isbn=" + isbn);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url.openStream());

            XPath xPath = XPathFactory.newInstance().newXPath();
            NodeList nl = (NodeList) xPath.evaluate("/searchRetrieveResponse/records/record/recordData/record/datafield/subfield", doc, XPathConstants.NODESET);
            for(int index = 0; index < nl.getLength(); index++) {
                Node node = nl.item(index);
                String a = node.getTextContent();
                //node.getTextContent().contains("Lysholmbiblioteket")
                if(node.getAttributes().item(0).equals("q")) {
                    list = Arrays.asList(node.getTextContent().split("\n"));
                    list.forEach(item -> {
                        System.out.println("Field: " + item);
                    });
                }
            }

        } catch (IOException | ParserConfigurationException | SAXException | XPathExpressionException e) {

        }

        return list;
    }



}