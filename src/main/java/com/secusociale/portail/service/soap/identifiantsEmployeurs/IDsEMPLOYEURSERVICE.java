
package com.secusociale.portail.service.soap.identifiantsEmployeurs;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "input",
    "output"
})
@XmlRootElement(name = "IDs_EMPLOYEUR_SERVICE", namespace = "http://oracle.com/IDs_EMPLOYEUR_SERVICE.xsd")
public class IDsEMPLOYEURSERVICE {

    @XmlElement(namespace = "http://oracle.com/IDs_EMPLOYEUR_SERVICE.xsd")
    protected Input input;
    @XmlElement(namespace = "http://oracle.com/IDs_EMPLOYEUR_SERVICE.xsd")
    protected List<Output> output;
    @XmlAttribute(name = "dateTimeTagFormat", required = true)
    protected String dateTimeTagFormat;


    public Input getInput() {
        return input;
    }

    public void setInput(Input value) {
        this.input = value;
    }


    public List<Output> getOutput() {
        if (output == null) {
            output = new ArrayList<Output>();
        }
        return this.output;
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
        "personId"
    })
    public static class Input {

        @XmlElement(namespace = "http://oracle.com/IDs_EMPLOYEUR_SERVICE.xsd")
        protected String personId;


        public String getPersonId() {
            return personId;
        }


        public void setPersonId(String value) {
            this.personId = value;
        }

    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "typeIdentifiant",
        "descIdentifiant",
        "numeroIdentifiant"
    })
    public static class Output {

        @XmlElement(namespace = "http://oracle.com/IDs_EMPLOYEUR_SERVICE.xsd")
        protected String typeIdentifiant;
        @XmlElement(namespace = "http://oracle.com/IDs_EMPLOYEUR_SERVICE.xsd")
        protected String descIdentifiant;
        @XmlElement(namespace = "http://oracle.com/IDs_EMPLOYEUR_SERVICE.xsd")
        protected String numeroIdentifiant;

        public String getTypeIdentifiant() {
            return typeIdentifiant;
        }

        public void setTypeIdentifiant(String value) {
            this.typeIdentifiant = value;
        }

        public String getDescIdentifiant() {
            return descIdentifiant;
        }

        public void setDescIdentifiant(String value) {
            this.descIdentifiant = value;
        }

        public String getNumeroIdentifiant() {
            return numeroIdentifiant;
        }

        public void setNumeroIdentifiant(String value) {
            this.numeroIdentifiant = value;
        }

    }

}
