package com.secusociale.portail.service.soap.detailsFactureDNS;

import javax.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {

})
@XmlRootElement(name = "Fault", namespace = "http://ouaf.oracle.com/")
public class Fault {
    @XmlElement(name = "ResponseStatus", required = true)
    protected String responseStatus;
    @XmlElement(name = "ResponseCode")
    protected int responseCode;
    @XmlElement(name = "ResponseText", required = true)
    protected String responseText;
    @XmlElement(name = "ResponseData")
    protected ResponseData responseData;
    @XmlElement(name = "ServerMessage")
    protected ServerMessage serverMessage;

    public String getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

    public ServerMessage getServerMessage() {
        return serverMessage;
    }

    public void setServerMessage(ServerMessage serverMessage) {
        this.serverMessage = serverMessage;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {

    })
    public static class ServerMessage {

        @XmlElement(name = "Category", required = true)
        protected String category;
        @XmlElement(name = "Number", required = true)
        protected String number;
        @XmlElement(name = "CallSequence", required = true)
        protected String callSequence;
        @XmlElement(name = "ProgramName", required = true)
        protected String programName;
        @XmlElement(name = "Text", required = true)
        protected String text;
        @XmlElement(name = "Description", required = true)
        protected String description;
        @XmlElement(name = "Table", required = true)
        protected String table;
        @XmlElement(name = "Field", required = true)
        protected String field;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getCallSequence() {
            return callSequence;
        }

        public void setCallSequence(String callSequence) {
            this.callSequence = callSequence;
        }

        public String getProgramName() {
            return programName;
        }

        public void setProgramName(String programName) {
            this.programName = programName;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getTable() {
            return table;
        }

        public void setTable(String table) {
            this.table = table;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "")
    public static class ResponseData {

        @XmlAttribute(name = "parm1")
        protected String parm1;
        @XmlAttribute(name = "parm2")
        protected String parm2;
        @XmlAttribute(name = "parm3")
        protected String parm3;
        @XmlAttribute(name = "parm4")
        protected String parm4;
        @XmlAttribute(name = "parm5")
        protected String parm5;
        @XmlAttribute(name = "parm6")
        protected String parm6;
        @XmlAttribute(name = "parm7")
        protected String parm7;
        @XmlAttribute(name = "parm8")
        protected String parm8;
        @XmlAttribute(name = "parm9")
        protected String parm9;
        @XmlAttribute(name = "text")
        protected String text;
        @XmlAttribute(name = "category")
        protected String category;
        @XmlAttribute(name = "numParm")
        protected Integer numParm;
        @XmlAttribute(name = "number")
        protected Integer number;

        public String getParm1() {
            return parm1;
        }

        public void setParm1(String parm1) {
            this.parm1 = parm1;
        }

        public String getParm2() {
            return parm2;
        }

        public void setParm2(String parm2) {
            this.parm2 = parm2;
        }

        public String getParm3() {
            return parm3;
        }

        public void setParm3(String parm3) {
            this.parm3 = parm3;
        }

        public String getParm4() {
            return parm4;
        }

        public void setParm4(String parm4) {
            this.parm4 = parm4;
        }

        public String getParm5() {
            return parm5;
        }

        public void setParm5(String parm5) {
            this.parm5 = parm5;
        }

        public String getParm6() {
            return parm6;
        }

        public void setParm6(String parm6) {
            this.parm6 = parm6;
        }

        public String getParm7() {
            return parm7;
        }

        public void setParm7(String parm7) {
            this.parm7 = parm7;
        }

        public String getParm8() {
            return parm8;
        }

        public void setParm8(String parm8) {
            this.parm8 = parm8;
        }

        public String getParm9() {
            return parm9;
        }

        public void setParm9(String parm9) {
            this.parm9 = parm9;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public Integer getNumParm() {
            return numParm;
        }

        public void setNumParm(Integer numParm) {
            this.numParm = numParm;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }
    }
}
