
package com.secusociale.portail.service.soap.getTauxPlafonds;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import java.math.BigDecimal;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.secusociale.portail.service.soap.getTauxPlafonds package.
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

    private final static QName _CMGETCONSTANTSOutputSmig_QNAME = new QName("http://oracle.com/CM_GET_CONSTANTS.xsd", "smig");
    private final static QName _CMGETCONSTANTSOutputPlafondRCC_QNAME = new QName("http://oracle.com/CM_GET_CONSTANTS.xsd", "plafondRCC");
    private final static QName _CMGETCONSTANTSOutputTauxRG_QNAME = new QName("http://oracle.com/CM_GET_CONSTANTS.xsd", "tauxRG");
    private final static QName _CMGETCONSTANTSOutputTauxPF_QNAME = new QName("http://oracle.com/CM_GET_CONSTANTS.xsd", "tauxPF");
    private final static QName _CMGETCONSTANTSOutputPlafondRG_QNAME = new QName("http://oracle.com/CM_GET_CONSTANTS.xsd", "plafondRG");
    private final static QName _CMGETCONSTANTSOutputPlafondPF_QNAME = new QName("http://oracle.com/CM_GET_CONSTANTS.xsd", "plafondPF");
    private final static QName _CMGETCONSTANTSOutputTauxRCC_QNAME = new QName("http://oracle.com/CM_GET_CONSTANTS.xsd", "tauxRCC");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.getTauxPlafonds
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CMGETCONSTANTS }
     */
    public CMGETCONSTANTS createCMGETCONSTANTS() {
        return new CMGETCONSTANTS();
    }

    /**
     * Create an instance of {@link Fault }
     */
    public Fault createFault() {
        return new Fault();
    }

    /**
     * Create an instance of {@link CMGETCONSTANTS.Output }
     */
    public CMGETCONSTANTS.Output createCMGETCONSTANTSOutput() {
        return new CMGETCONSTANTS.Output();
    }

    /**
     * Create an instance of {@link Fault.ResponseData }
     */
    public Fault.ResponseData createFaultResponseData() {
        return new Fault.ResponseData();
    }

    /**
     * Create an instance of {@link Fault.ServerMessage }
     */
    public Fault.ServerMessage createFaultServerMessage() {
        return new Fault.ServerMessage();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", name = "smig", scope = CMGETCONSTANTS.Output.class)
    public JAXBElement<BigDecimal> createCMGETCONSTANTSOutputSmig(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETCONSTANTSOutputSmig_QNAME, BigDecimal.class, CMGETCONSTANTS.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", name = "plafondRCC", scope = CMGETCONSTANTS.Output.class)
    public JAXBElement<BigDecimal> createCMGETCONSTANTSOutputPlafondRCC(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETCONSTANTSOutputPlafondRCC_QNAME, BigDecimal.class, CMGETCONSTANTS.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", name = "tauxRG", scope = CMGETCONSTANTS.Output.class)
    public JAXBElement<BigDecimal> createCMGETCONSTANTSOutputTauxRG(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETCONSTANTSOutputTauxRG_QNAME, BigDecimal.class, CMGETCONSTANTS.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", name = "tauxPF", scope = CMGETCONSTANTS.Output.class)
    public JAXBElement<BigDecimal> createCMGETCONSTANTSOutputTauxPF(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETCONSTANTSOutputTauxPF_QNAME, BigDecimal.class, CMGETCONSTANTS.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", name = "plafondRG", scope = CMGETCONSTANTS.Output.class)
    public JAXBElement<BigDecimal> createCMGETCONSTANTSOutputPlafondRG(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETCONSTANTSOutputPlafondRG_QNAME, BigDecimal.class, CMGETCONSTANTS.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", name = "plafondPF", scope = CMGETCONSTANTS.Output.class)
    public JAXBElement<BigDecimal> createCMGETCONSTANTSOutputPlafondPF(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETCONSTANTSOutputPlafondPF_QNAME, BigDecimal.class, CMGETCONSTANTS.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_CONSTANTS.xsd", name = "tauxRCC", scope = CMGETCONSTANTS.Output.class)
    public JAXBElement<BigDecimal> createCMGETCONSTANTSOutputTauxRCC(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETCONSTANTSOutputTauxRCC_QNAME, BigDecimal.class, CMGETCONSTANTS.Output.class, value);
    }

}
