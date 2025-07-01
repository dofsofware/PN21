
package com.secusociale.portail.service.soap.checkExistenceEmployeur;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.*;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "CmCheckExistenceEmployeur")
public class CmCheckExistenceEmployeur {

    @XmlElement(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd")
    protected Output output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;

    public Input getInput() {
        return input;
    }

    public void setInput(Input value) {
        this.input = value;
    }

    public Output getOutput() {
        return output;
    }

    public void setOutput(Output value) {
        this.output = value;
    }

    public String getDateTimeTagFormat() {
        if (dateTimeTagFormat == null) {
            return "xsd:strict";
        } else {
            return dateTimeTagFormat;
        }
    }

    public void setDateTimeTagFormat(String value) {
        this.dateTimeTagFormat = value;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "taxpayerIdentifierType",
        "taxpayerIdentifierValue",
        "taxpayerName",
        "checkUsingTaxpayerIDOnly",
        "fuzzySearchUsage"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd")
        protected String taxpayerIdentifierType;
        @XmlElement(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd")
        protected String taxpayerIdentifierValue;
        @XmlElement(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd")
        protected String taxpayerName;
        @XmlElementRef(name = "checkUsingTaxpayerIDOnly", namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> checkUsingTaxpayerIDOnly;
        @XmlElement(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd")
        protected String fuzzySearchUsage;


        public String getTaxpayerIdentifierType() {
            return taxpayerIdentifierType;
        }

        public void setTaxpayerIdentifierType(String value) {
            this.taxpayerIdentifierType = value;
        }

        public String getTaxpayerIdentifierValue() {
            return taxpayerIdentifierValue;
        }

        public void setTaxpayerIdentifierValue(String value) {
            this.taxpayerIdentifierValue = value;
        }

        public String getTaxpayerName() {
            return taxpayerName;
        }

        public void setTaxpayerName(String value) {
            this.taxpayerName = value;
        }

        public JAXBElement<Boolean> getCheckUsingTaxpayerIDOnly() {
            return checkUsingTaxpayerIDOnly;
        }

        public void setCheckUsingTaxpayerIDOnly(JAXBElement<Boolean> value) {
            this.checkUsingTaxpayerIDOnly = value;
        }

        public String getFuzzySearchUsage() {
            return fuzzySearchUsage;
        }

        public void setFuzzySearchUsage(String value) {
            this.fuzzySearchUsage = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "isTaxpayerIdentifierExist",
        "isTaxpayerNameExist",
        "personId"
    })
    public static class Output {

        @XmlElementRef(name = "isTaxpayerIdentifierExist", namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> isTaxpayerIdentifierExist;
        @XmlElementRef(name = "isTaxpayerNameExist", namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd", type = JAXBElement.class, required = false)
        protected JAXBElement<Boolean> isTaxpayerNameExist;
        @XmlElement(namespace = "http://oracle.com/CmCheckExistenceEmployeur.xsd")
        protected String personId;

        public JAXBElement<Boolean> getIsTaxpayerIdentifierExist() {
            return isTaxpayerIdentifierExist;
        }

        public void setIsTaxpayerIdentifierExist(JAXBElement<Boolean> value) {
            this.isTaxpayerIdentifierExist = value;
        }

        public JAXBElement<Boolean> getIsTaxpayerNameExist() {
            return isTaxpayerNameExist;
        }

        public void setIsTaxpayerNameExist(JAXBElement<Boolean> value) {
            this.isTaxpayerNameExist = value;
        }

        public String getPersonId() {
            return personId;
        }

        public void setPersonId(String value) {
            this.personId = value;
        }

    }

}
