
package com.secusociale.portail.service.soap.cmdmt;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.secusociale.portail.service.soap.cmdmt package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CmDmtEmployesSalaireBrute_QNAME = new QName("http://oracle.com/CmDmt.xsd", "salaireBrute");
    private final static QName _CmDmtEmployesDateFinMouvement_QNAME = new QName("http://oracle.com/CmDmt.xsd", "dateFinMouvement");
    private final static QName _CmDmtEmployesEstCadre_QNAME = new QName("http://oracle.com/CmDmt.xsd", "estCadre");
    private final static QName _CmDmtEmployesDateNaissance_QNAME = new QName("http://oracle.com/CmDmt.xsd", "dateNaissance");
    private final static QName _CmDmtEmployesDateDebutMouvement_QNAME = new QName("http://oracle.com/CmDmt.xsd", "dateDebutMouvement");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.cmdmt
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CmDmt }
     * 
     */
    public CmDmt createCmDmt() {
        return new CmDmt();
    }

    /**
     * Create an instance of {@link Fault }
     * 
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CmDmt.Employes }
     * 
     */
    public CmDmt.Employes createCmDmtEmployes() {
        return new CmDmt.Employes();
    }

    /**
     * Create an instance of {@link Fault.ResponseData }
     * 
     */
    public Fault.ResponseData createFaultResponseData() {
        return new Fault.ResponseData();
    }

    /**
     * Create an instance of {@link Fault.ServerMessage }
     * 
     */
    public Fault.ServerMessage createFaultServerMessage() {
        return new Fault.ServerMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmDmt.xsd", name = "salaireBrute", scope = CmDmt.Employes.class)
    public JAXBElement<BigDecimal> createCmDmtEmployesSalaireBrute(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CmDmtEmployesSalaireBrute_QNAME, BigDecimal.class, CmDmt.Employes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmDmt.xsd", name = "dateFinMouvement", scope = CmDmt.Employes.class)
    public JAXBElement<XMLGregorianCalendar> createCmDmtEmployesDateFinMouvement(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CmDmtEmployesDateFinMouvement_QNAME, XMLGregorianCalendar.class, CmDmt.Employes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmDmt.xsd", name = "estCadre", scope = CmDmt.Employes.class)
    public JAXBElement<Boolean> createCmDmtEmployesEstCadre(Boolean value) {
        return new JAXBElement<Boolean>(_CmDmtEmployesEstCadre_QNAME, Boolean.class, CmDmt.Employes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmDmt.xsd", name = "dateNaissance", scope = CmDmt.Employes.class)
    public JAXBElement<XMLGregorianCalendar> createCmDmtEmployesDateNaissance(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CmDmtEmployesDateNaissance_QNAME, XMLGregorianCalendar.class, CmDmt.Employes.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CmDmt.xsd", name = "dateDebutMouvement", scope = CmDmt.Employes.class)
    public JAXBElement<XMLGregorianCalendar> createCmDmtEmployesDateDebutMouvement(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CmDmtEmployesDateDebutMouvement_QNAME, XMLGregorianCalendar.class, CmDmt.Employes.class, value);
    }

}
