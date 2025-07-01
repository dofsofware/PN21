
package com.secusociale.portail.service.soap.employeurInfos;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.employeurInfos package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _CMEMPLOYEURINFOSOutputAdditionalTradeRegisterDate_QNAME = new QName("http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", "tradeRegisterDate");
    private final static QName _CMEMPLOYEURINFOSOutputAdditionalDateOfFirstHire_QNAME = new QName("http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", "dateOfFirstHire");
    private final static QName _CMEMPLOYEURINFOSOutputAdditionalTaxIdDate_QNAME = new QName("http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", "taxIdDate");
    private final static QName _CMEMPLOYEURINFOSOutputAdditionalDateOfInspection_QNAME = new QName("http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", "dateOfInspection");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.employeurInfos
     *
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Fault }
     *
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CMEMPLOYEURINFOS }
     *
     */
    public CMEMPLOYEURINFOS createCMEMPLOYEURINFOS() {
        return new CMEMPLOYEURINFOS();
    }

    /**
     * Create an instance of {@link CMEMPLOYEURINFOS.Output }
     *
     */
    public CMEMPLOYEURINFOS.Output createCMEMPLOYEURINFOSOutput() {
        return new CMEMPLOYEURINFOS.Output();
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
     * Create an instance of {@link CMEMPLOYEURINFOS.Input }
     *
     */
    public CMEMPLOYEURINFOS.Input createCMEMPLOYEURINFOSInput() {
        return new CMEMPLOYEURINFOS.Input();
    }

    /**
     * Create an instance of {@link CMEMPLOYEURINFOS.Output.Identifiants }
     *
     */
    public CMEMPLOYEURINFOS.Output.Identifiants createCMEMPLOYEURINFOSOutputIdentifiants() {
        return new CMEMPLOYEURINFOS.Output.Identifiants();
    }

    /**
     * Create an instance of {@link CMEMPLOYEURINFOS.Output.Status }
     *
     */
    public CMEMPLOYEURINFOS.Output.Status createCMEMPLOYEURINFOSOutputStatus() {
        return new CMEMPLOYEURINFOS.Output.Status();
    }

    /**
     * Create an instance of {@link CMEMPLOYEURINFOS.Output.Additional }
     *
     */
    public CMEMPLOYEURINFOS.Output.Additional createCMEMPLOYEURINFOSOutputAdditional() {
        return new CMEMPLOYEURINFOS.Output.Additional();
    }

    /**
     * Create an instance of {@link CMEMPLOYEURINFOS.Output.RepresentantLegal }
     *
     */
    public CMEMPLOYEURINFOS.Output.RepresentantLegal createCMEMPLOYEURINFOSOutputRepresentantLegal() {
        return new CMEMPLOYEURINFOS.Output.RepresentantLegal();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", name = "tradeRegisterDate", scope = CMEMPLOYEURINFOS.Output.Additional.class)
    public JAXBElement<XMLGregorianCalendar> createCMEMPLOYEURINFOSOutputAdditionalTradeRegisterDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMEMPLOYEURINFOSOutputAdditionalTradeRegisterDate_QNAME, XMLGregorianCalendar.class, CMEMPLOYEURINFOS.Output.Additional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", name = "dateOfFirstHire", scope = CMEMPLOYEURINFOS.Output.Additional.class)
    public JAXBElement<XMLGregorianCalendar> createCMEMPLOYEURINFOSOutputAdditionalDateOfFirstHire(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMEMPLOYEURINFOSOutputAdditionalDateOfFirstHire_QNAME, XMLGregorianCalendar.class, CMEMPLOYEURINFOS.Output.Additional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", name = "taxIdDate", scope = CMEMPLOYEURINFOS.Output.Additional.class)
    public JAXBElement<XMLGregorianCalendar> createCMEMPLOYEURINFOSOutputAdditionalTaxIdDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMEMPLOYEURINFOSOutputAdditionalTaxIdDate_QNAME, XMLGregorianCalendar.class, CMEMPLOYEURINFOS.Output.Additional.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_EMPLOYEUR_INFOS.xsd", name = "dateOfInspection", scope = CMEMPLOYEURINFOS.Output.Additional.class)
    public JAXBElement<XMLGregorianCalendar> createCMEMPLOYEURINFOSOutputAdditionalDateOfInspection(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMEMPLOYEURINFOSOutputAdditionalDateOfInspection_QNAME, XMLGregorianCalendar.class, CMEMPLOYEURINFOS.Output.Additional.class, value);
    }

}
