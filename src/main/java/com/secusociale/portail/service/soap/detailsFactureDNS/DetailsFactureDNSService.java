package com.secusociale.portail.service.soap.detailsFactureDNS;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CM_GET_FACTURE")
public class DetailsFactureDNSService {
    @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE.xsd")
    protected DetailsFactureDNSService.Input input;
    @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE.xsd")
    protected DetailsFactureDNSService.Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    /**
     * Obtient la valeur de la propriete dateTimeTagFormat.
     *
     * @return possible object is
     * {@link String }
     */
    public String getDateTimeTagFormat() {
        if (dateTimeTagFormat == null) {
            return "xsd:strict";
        } else {
            return dateTimeTagFormat;
        }
    }

    /**
     * Definit la valeur de la propriete dateTimeTagFormat.
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDateTimeTagFormat(String value) {
        this.dateTimeTagFormat = value;
    }

    public Input getInput() {
        return input;
    }

    public void setInput(Input input) {
        this.input = input;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output output) {
        this.output = output;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "formId"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE.xsd")
        protected String formId;

        public String getFormId() {
            return formId;
        }

        public void setFormId(String formId) {
            this.formId = formId;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "url"
    })
    public static class Output {
        @XmlElement(namespace = "http://oracle.com/CM_GET_FACTURE.xsd")
        protected String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
