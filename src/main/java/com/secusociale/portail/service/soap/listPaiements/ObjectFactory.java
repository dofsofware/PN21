
package com.secusociale.portail.service.soap.listPaiements;

import java.math.BigDecimal;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.secusociale.portail.service.soap.listPaiements package. 
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

    private final static QName _CMGETPAYMENTInputDateTo_QNAME = new QName("http://oracle.com/CM_GET_PAYMENT.xsd", "dateTo");
    private final static QName _CMGETPAYMENTInputDateFrom_QNAME = new QName("http://oracle.com/CM_GET_PAYMENT.xsd", "dateFrom");
    private final static QName _CMGETPAYMENTOutputTenderAmount_QNAME = new QName("http://oracle.com/CM_GET_PAYMENT.xsd", "tenderAmount");
    private final static QName _CMGETPAYMENTOutputPayDate_QNAME = new QName("http://oracle.com/CM_GET_PAYMENT.xsd", "payDate");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.secusociale.portail.service.soap.listPaiements
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
     * Create an instance of {@link CMGETPAYMENT }
     * 
     */
    public CMGETPAYMENT createCMGETPAYMENT() {
        return new CMGETPAYMENT();
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
     * Create an instance of {@link CMGETPAYMENT.Input }
     * 
     */
    public CMGETPAYMENT.Input createCMGETPAYMENTInput() {
        return new CMGETPAYMENT.Input();
    }

    /**
     * Create an instance of {@link CMGETPAYMENT.Output }
     * 
     */
    public CMGETPAYMENT.Output createCMGETPAYMENTOutput() {
        return new CMGETPAYMENT.Output();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", name = "dateTo", scope = CMGETPAYMENT.Input.class)
    public JAXBElement<XMLGregorianCalendar> createCMGETPAYMENTInputDateTo(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMGETPAYMENTInputDateTo_QNAME, XMLGregorianCalendar.class, CMGETPAYMENT.Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", name = "dateFrom", scope = CMGETPAYMENT.Input.class)
    public JAXBElement<XMLGregorianCalendar> createCMGETPAYMENTInputDateFrom(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMGETPAYMENTInputDateFrom_QNAME, XMLGregorianCalendar.class, CMGETPAYMENT.Input.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BigDecimal }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", name = "tenderAmount", scope = CMGETPAYMENT.Output.class)
    public JAXBElement<BigDecimal> createCMGETPAYMENTOutputTenderAmount(BigDecimal value) {
        return new JAXBElement<BigDecimal>(_CMGETPAYMENTOutputTenderAmount_QNAME, BigDecimal.class, CMGETPAYMENT.Output.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://oracle.com/CM_GET_PAYMENT.xsd", name = "payDate", scope = CMGETPAYMENT.Output.class)
    public JAXBElement<XMLGregorianCalendar> createCMGETPAYMENTOutputPayDate(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_CMGETPAYMENTOutputPayDate_QNAME, XMLGregorianCalendar.class, CMGETPAYMENT.Output.class, value);
    }

}
