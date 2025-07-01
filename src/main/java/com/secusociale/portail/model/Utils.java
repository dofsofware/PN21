package com.secusociale.portail.model;

import com.google.protobuf.TextFormat;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Utils {

    public static XMLGregorianCalendar formatToGregorianCalendar(String strDate) throws  DatatypeConfigurationException, java.text.ParseException {
        XMLGregorianCalendar xmlGregorianCalendar = null;
        if (strDate != null && !strDate.isEmpty()) {
            Date format = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);
            GregorianCalendar gCalendar = new GregorianCalendar();
            gCalendar.setTime(format);
            xmlGregorianCalendar = DatatypeFactory.newInstance().newXMLGregorianCalendarDate(gCalendar.get(Calendar.YEAR), gCalendar.get(Calendar.MONTH) + 1, gCalendar.get(Calendar.DAY_OF_MONTH), DatatypeConstants.FIELD_UNDEFINED);
            return xmlGregorianCalendar;
        } else {
            return null;
        }

    }

    public static String formaToString(Date date) {
        SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
        return date != null ? formatDate.format(date) : null;
    }
}
