//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.02.24 at 04:35:23 ���� CST 
//


package com.sun.java.xml.ns.j2ee;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 
 * 	The logical name of the filter is declare
 * 	by using filter-nameType. This name is used to map the
 * 	filter.  Each filter name is unique within the web
 * 	application.
 * 
 * 	Used in: filter, filter-mapping
 * 
 *       
 * 
 * <p>Java class for filter-nameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="filter-nameType">
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://java.sun.com/xml/ns/j2ee>nonEmptyStringType">
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "filter-nameType", namespace = "http://java.sun.com/xml/ns/j2ee")
public class FilterNameType
    extends NonEmptyStringType
{


}