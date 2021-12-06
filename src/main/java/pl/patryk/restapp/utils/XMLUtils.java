package pl.patryk.restapp.utils;

import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class XMLUtils {

    private static final String MAIN_NODE = "epaperRequest";

    private XMLUtils() {
    }

    public static boolean validateXMLSchema(MultipartFile multipartFile, MultipartFile xsd) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new StreamSource(xsd.getInputStream()));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(multipartFile.getInputStream()));
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static String readSimpleAttribute(String attributeName, MultipartFile multipartFile) {
        Element element = getNodeElement(multipartFile);
        if (element != null) {
            return element.getElementsByTagName(attributeName).item(0).getTextContent();
        } else return "";
    }

    public static String readNestedAttribute(String nodeName, String attributeName, MultipartFile multipartFile) {
        Element element = getNodeElement(multipartFile);
        if (element != null) {
            NodeList nodeList = element.getElementsByTagName(nodeName);
            return nodeList.item(0).getAttributes().getNamedItem(attributeName).getTextContent();
        } else return "";
    }

    private static Element getNodeElement(MultipartFile multipartFile) {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilderFactory.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(multipartFile.getInputStream());
            document.getDocumentElement().normalize();

            NodeList list = document.getElementsByTagName(MAIN_NODE);

            for (int temp = 0; temp < list.getLength(); temp++) {
                Node node = list.item(temp);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    return (Element) node;
                }
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
