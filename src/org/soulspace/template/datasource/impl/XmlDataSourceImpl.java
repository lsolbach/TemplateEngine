package org.soulspace.template.datasource.impl;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

import org.soulspace.template.datasource.DataSource;
import org.soulspace.template.value.ListValue;
import org.soulspace.template.value.MapValue;
import org.soulspace.template.value.StringValue;
import org.soulspace.template.value.SymbolTable;
import org.soulspace.template.value.impl.ListValueImpl;
import org.soulspace.template.value.impl.StringValueImpl;
import org.soulspace.template.value.impl.SymbolTableImpl;
import org.soulspace.template.value.impl.ValueFactoryImpl;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

public class XmlDataSourceImpl extends DefaultHandler implements DataSource {

	private XMLReader xmlReader;
	private SymbolTable symbolTable = new SymbolTableImpl();
	private Stack<MapValue> elementStack;
	MapValue current = null;
	ValueFactoryImpl valueFactory = new ValueFactoryImpl();	
	
	private XmlDataSourceImpl() throws SAXException {
		elementStack = new Stack<MapValue>();
		xmlReader = XMLReaderFactory.createXMLReader();
		xmlReader.setContentHandler(this);
		xmlReader.setErrorHandler(this);
	}
	
	public XmlDataSourceImpl(String xmlString) throws SAXException, IOException {
		this();
		xmlReader.parse(xmlString);
	}

	public XmlDataSourceImpl(File xmlFile) throws SAXException, IOException {
		this();
		xmlReader.parse(new InputSource(new FileReader(xmlFile)));
	}

	public SymbolTable getSymbolTable() {
		return symbolTable;
	}
	
	@Override
	public void startDocument() throws SAXException {
		super.startDocument();
	}

	@Override
	public void endDocument() throws SAXException {
		if(current != null) {
			String key = ((StringValue) current.getValue("type")).getData();
			symbolTable.addSymbol(key, current);
		}
		super.endDocument();
	}

	@Override
	public void startElement(String uri, String name, String qName, Attributes attrs) throws SAXException {
		String elementName = "";
		if ("".equals (uri)) {
	    	elementName = qName;
		} else {
	    	elementName = name;
		}
		MapValue element = valueFactory.createMapValue();
		MapValue attrMap = valueFactory.createMapValue();
		int attrCount = attrs.getLength();
		for(int i = 0; i < attrCount; i++) {
			String key = attrs.getLocalName(i);
			String value = attrs.getValue(key);
			attrMap.addValue(key, new StringValueImpl(value));
		}
		element.addValue("attrs", attrMap);
		element.addValue("type", new StringValueImpl(elementName));
		element.addValue("content", new StringValueImpl(""));
		element.addValue("children", new ListValueImpl());
		elementStack.push(element);
		current = element;
	}

	@Override
	public void endElement(String arg0, String arg1, String arg2)
			throws SAXException {
		current = elementStack.pop();
		if(!elementStack.isEmpty()) {
			MapValue parent = elementStack.peek();
			ListValue children = (ListValue) parent.getValue("children");
			children.addValue(current);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = charsToString(ch, start, length);
		if(value.trim().equals("")) {
			return;
		}
		if(!elementStack.isEmpty()) {
			MapValue parent = elementStack.peek();
			StringValue oldContent = (StringValue) parent.getValue("content");
			parent.addValue("content", new StringValueImpl(oldContent.getData() + value));
		}
	}

	protected String stripTrailingLine(String input) {
		if(input.endsWith("\r\n")) {
			return input.substring(0, input.length() - 2);
		} else if(input.endsWith("\n")) {
			return input.substring(0, input.length() - 1);
		} else if(input.endsWith("\r")) {
			return input.substring(0, input.length() - 1);
		} else {
			return input;
		}
	}

	protected String charsToString(char[] ch, int start, int length) {
		StringBuilder sb = new StringBuilder();
		for(int i = start; i < start + length; i++) {
			sb.append(ch[i]);
		}
		String data = sb.toString();
		if(data.startsWith("\n") && data.endsWith("\n")) {
			return data.substring(1, data.length());
		} else if(data.startsWith("\n")) {
			return data.substring(1);
		} else if(data.endsWith("\n")) {
			return data;
		} else {
			return data + "\n";
		}
	}	

}
