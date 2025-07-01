
package com.secusociale.portail.service.soap.infosEmployeur;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.math.BigDecimal;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.infosEmployeur package.
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

    private final static QName _CMINFOSEMPLOYEUROutputTauxRG_QNAME = new QName("http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", "tauxRG");
    private final static QName _CMINFOSEMPLOYEUROutputTauxPF_QNAME = new QName("http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", "tauxPF");
    private final static QName _CMINFOSEMPLOYEUROutputNinea_QNAME = new QName("http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", "ninea");
    private final static QName _CMINFOSEMPLOYEUROutputTauxRC_QNAME = new QName("http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", "tauxRC");
    private final static QName _CMINFOSEMPLOYEUROutputTauxAt_QNAME = new QName("http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", "tauxAt");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.infosEmployeur
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
     * Create an instance of {@link CMINFOSEMPLOYEUR }
     *
     */
    public CMINFOSEMPLOYEUR createCMINFOSEMPLOYEUR() {
        return new CMINFOSEMPLOYEUR();
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
     * Create an instance of {@link CMINFOSEMPLOYEUR.Input }
     *
     */
    public CMINFOSEMPLOYEUR.Input createCMINFOSEMPLOYEURInput() {
        return new CMINFOSEMPLOYEUR.Input();
    }

    /**
     * Create an instance of {@link CMINFOSEMPLOYEUR.Output }
     *
     */
    public CMINFOSEMPLOYEUR.Output createCMINFOSEMPLOYEUROutput() {
        return new CMINFOSEMPLOYEUR.Output();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", name = "tauxRG", scope = CMINFOSEMPLOYEUR.Output.class)
    public JAXBElement<BigDecimal> createCMINFOSEMPLOYEUROutputTauxRG(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMINFOSEMPLOYEUROutputTauxRG_QNAME, BigDecimal.class, CMINFOSEMPLOYEUR.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", name = "tauxPF", scope = CMINFOSEMPLOYEUR.Output.class)
    public JAXBElement<BigDecimal> createCMINFOSEMPLOYEUROutputTauxPF(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMINFOSEMPLOYEUROutputTauxPF_QNAME, BigDecimal.class, CMINFOSEMPLOYEUR.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", name = "ninea", scope = CMINFOSEMPLOYEUR.Output.class)
    public JAXBElement<BigDecimal> createCMINFOSEMPLOYEUROutputNinea(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMINFOSEMPLOYEUROutputNinea_QNAME, BigDecimal.class, CMINFOSEMPLOYEUR.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", name = "tauxRC", scope = CMINFOSEMPLOYEUR.Output.class)
    public JAXBElement<BigDecimal> createCMINFOSEMPLOYEUROutputTauxRC(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMINFOSEMPLOYEUROutputTauxRC_QNAME, BigDecimal.class, CMINFOSEMPLOYEUR.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     *
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_INFOS_EMPLOYEUR.xsd", name = "tauxAt", scope = CMINFOSEMPLOYEUR.Output.class)
    public JAXBElement<BigDecimal> createCMINFOSEMPLOYEUROutputTauxAt(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMINFOSEMPLOYEUROutputTauxAt_QNAME, BigDecimal.class, CMINFOSEMPLOYEUR.Output.class, value);
    }

}
