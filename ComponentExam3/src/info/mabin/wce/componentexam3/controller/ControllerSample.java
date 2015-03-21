package info.mabin.wce.componentexam3.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import info.mabin.wce.componentexam1.icm.IcmExam1;
import info.mabin.wce.componentexam3.model.ModelConfiguration;
import info.mabin.wce.manager.exception.IcmNotRegisteredException;
import info.mabin.wce.manager.icm.exception.IcmException;
import info.mabin.wce.supportlibrary.ComponentModule;
import info.mabin.wce.supportlibrary.annotation.Autowired;
import info.mabin.wce.supportlibrary.annotation.Controller;
import info.mabin.wce.supportlibrary.annotation.EventRegisteredIcm;
import info.mabin.wce.supportlibrary.annotation.EventUnregisteredIcm;
import info.mabin.wce.supportlibrary.annotation.RequestMapping;
import info.mabin.wce.supportlibrary.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class ControllerSample extends ComponentModule{
	@Autowired
	ModelConfiguration modelConfiguration;
	
	private IcmExam1 icmExam1 = null;
	
	@RequestMapping(value = "", method = RequestMethod.GET)
	public void defaultUri(HttpServletRequest request, HttpServletResponse response){
		PrintWriter out = null;
		try {
			out = response.getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		out.println("<h1>Sample Servlet 3!!</h1>");
		out.println("TestString in Configuration is '" + modelConfiguration.getTestString() + "'<br>\n");
		
		if(icmExam1 != null){
			try {
				out.println("ComponentExam1's Version: " + icmExam1.getVersionName() + "<br>\n");
				out.println("ComponentExam1's SumResult (123+321): " + icmExam1.sum(123, 321) + "<br>\n");
			} catch (IcmException e) {
				e.printStackTrace();
			}
		} else {
			out.println("ComponentExam1 is not Registered");
		}
		
		logger.d("SampleServlet Requested");
	}
	
	@EventRegisteredIcm("info.mabin.wce.componentexam1.icm.IcmExam1")
	public void eventRegisteredIcmExam1Icm1(){
		logger.i("Exam1Icm1 is Registered!");
		
		try {
			icmExam1 = new IcmExam1();
			
		} catch (IcmNotRegisteredException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@EventUnregisteredIcm("info.mabin.wce.componentexam1.icm.IcmExam1")
	public void eventUnregisteredIcmExam1Icm1(){
		logger.i("Exam1Icm1 is Unregistered!");
		icmExam1 = null;
	}
}