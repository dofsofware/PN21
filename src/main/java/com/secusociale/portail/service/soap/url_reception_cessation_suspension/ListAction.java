
package com.secusociale.portail.service.soap.url_reception_cessation_suspension;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour listAction.
 *
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * <p>
 * <pre>
 * &lt;simpleType name="listAction">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}token">
 *     &lt;enumeration value="delete"/>
 *     &lt;enumeration value="add"/>
 *     &lt;enumeration value="update"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "listAction", namespace = "http://ouaf.oracle.com/")
@XmlEnum
public enum ListAction {

    @XmlEnumValue("delete")
    DELETE("delete"),
    @XmlEnumValue("add")
    ADD("add"),
    @XmlEnumValue("update")
    UPDATE("update");
    private final String value;

    ListAction(String v) {
        value = v;
    }

    public static ListAction fromValue(String v) {
        for (ListAction c : ListAction.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
