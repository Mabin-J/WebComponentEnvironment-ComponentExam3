package info.mabin.wce.componentexam3.model;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import info.mabin.wce.supportlibrary.ComponentModule;
import info.mabin.wce.supportlibrary.annotation.EventChangedConfiguration;
import info.mabin.wce.supportlibrary.annotation.Model;
import info.mabin.wce.supportlibrary.exception.ComponentModuleException;

@Model
public class ModelConfiguration extends ComponentModule{
	private String testString;
	
	@Override
	public void init() throws ComponentModuleException {
		super.init();
		
		NodeList config = context.getConfiguration();
		
		parseTestString(config);
	}
	
	public String getTestString(){
		return testString;
	}
	
	@EventChangedConfiguration
	public void changedConfiguration(NodeList config){
		logger.i("Configuration is Changed!");
		parseTestString(config);
	}
	
	private void parseTestString(NodeList config){
		for(int i = 0; i < config.getLength(); i++){
			Node tmpNode = config.item(i);
			
			if(tmpNode.getNodeName().equals("TestString")){
				testString = tmpNode.getTextContent();
			}
		}
	}
}
