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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLParser {

    JSONArray bookdetail = new JSONArray();

    public XMLParser() {

    }

    public static void main(String args[]) {
        XMLParser parser = new XMLParser();
        String isbn = "978-82-489-2327-5";
        String newHei = "978-82-7900-843-9";
        parser.parseXML("47BIBSYS_NTNU_UB", newHei);
    }

    private JSONArray parseXML(String librarySystem, String isbn) {
        try {
            JSONObject book = new JSONObject();
            String title = "";
            String author = "";
            String summary = "";
            String image = "";
            URL url = new URL("https://bibsys.alma.exlibrisgroup.com/view/sru/" + librarySystem + "?version=1.2&operation=searchRetrieve&recordSchema=marcxml&query=alma.isbn=" + isbn);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Accept", "application/xml");
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();
            Document doc = db.parse(url.openStream());
            doc.getDocumentElement().normalize();

            NodeList datafieldNL = doc.getElementsByTagName("datafield");
            for(int index = 0; index < datafieldNL.getLength(); index++) {
                Node datafieldnode = datafieldNL.item(index);
                Element element = (Element) datafieldnode;

                NodeList subfieldnl = element.getElementsByTagName("subfield");

                //Get Title and author
                if (datafieldnode.getAttributes().item(2).getNodeValue().equals("245")){
                    for(int i = 0; i < subfieldnl.getLength(); i++){
                        Node subfieldnode = subfieldnl.item(i);
                        Element subfieldelement = (Element) subfieldnode;
                        String subfieldCode = String.valueOf(subfieldnode.getAttributes().item(0).getNodeValue());
                        if (subfieldCode.equals("a") || subfieldCode.equals("b")){
                            title += subfieldelement.getTextContent();
                        }
                        if (subfieldCode.equals("c")){
                            author = subfieldelement.getTextContent();
                        }
                    }
                }
                //Get Book summary
                if (datafieldnode.getAttributes().item(2).getNodeValue().equals("520")){
                    for(int i = 0; i < subfieldnl.getLength(); i++){
                        Node subfieldnode = subfieldnl.item(i);
                        Element subfieldelement = (Element) subfieldnode;
                        String subfieldCode = String.valueOf(subfieldnode.getAttributes().item(0).getNodeValue());
                        if (subfieldCode.equals("a") || subfieldCode.equals("b")){
                            summary = subfieldelement.getTextContent();
                        }

                    }
                }
                //Get Image
                if (datafieldnode.getAttributes().item(2).getNodeValue().equals("856") && datafieldnode.getAttributes().item(1).getNodeValue().equals("1")){
                    for(int i = 0; i < subfieldnl.getLength(); i++){
                        Node subfieldnode = subfieldnl.item(i);
                        Element subfieldelement = (Element) subfieldnode;
                        String subfieldCode = String.valueOf(subfieldnode.getAttributes().item(0).getNodeValue());
                        if (subfieldCode.equals("u")){
                            if(image.isEmpty()){
                                image = subfieldelement.getTextContent();
                            }
                        }
                    }
                }


            }
            book.put("Title",title);
            book.put("Author",author);
            book.put("Summary",summary);
            book.put("Image",image);
            bookdetail.put(book);

        } catch (IOException | ParserConfigurationException | SAXException  | JSONException e) {
            System.out.println(e.getMessage());
        }

        return bookdetail;
    }

}