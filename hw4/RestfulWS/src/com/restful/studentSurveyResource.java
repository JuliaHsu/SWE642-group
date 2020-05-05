package com.restful;

import com.google.gson.Gson;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.*;

import javax.persistence.*;
import com.entity.studentSurvey;
import java.util.List;
import javax.ejb.Stateless;
 
import java.util.Date;
// Plain old Java Object it does not extend as class or implements
// an interface
 
// The class registers its methods for the HTTP GET request using the @GET annotation.
// Using the @Produces annotation, it defines that it can deliver several MIME types,
// text, XML and HTML.
 
// The browser requests per default the HTML MIME type.
 
//Sets the path to base URL + /hello
@Stateless
//survey is a resource
@Path("studentSurvey")
public class studentSurveyResource {
	@PersistenceContext(unitName = "surveyDB")
	private EntityManager em = Persistence.createEntityManagerFactory("surveyDB").createEntityManager();
	public studentSurveyResource(){}
	
	@POST 
	@Path("create")
	@Consumes({ "*/*" })
//	@Produces(MediaType.APPLICATION_JSON)
//	@Consumes("application/x-www-form-urlencoded")
	public studentSurvey createSurvey( studentSurvey inputSurvey){
		studentSurvey stuSurvey = new studentSurvey();
		em.getTransaction().begin();
		em.persist(inputSurvey);
		em.getTransaction().commit();
		em.close();
		return stuSurvey;
		
	}
	@SuppressWarnings("unchecked")
	@GET
	@Produces({ "application/json"})
	@Consumes({"*/*"})
	public String findAll(){
		List<studentSurveyResource> surveyList = null;
		surveyList = em.createQuery("SELECT s FROM studentSurvey s").getResultList();

		em.close();
		System.out.println(new Gson().toJson(surveyList));
		return new Gson().toJson(surveyList);
	}
	
	@GET
	@Produces({ "application/json"})
	@Path("{studentId}")
	public String find(@PathParam("studentId") String studentId){
		List<studentSurveyResource> surveyDetails = null;
		surveyDetails = em.createQuery("SELECT s FROM studentSurvey s WHERE s.studentId LIKE :studentId").setParameter("studentId", studentId).getResultList();
		em.close();
		return new Gson().toJson(surveyDetails);
	}
  // This method is called if TEXT_PLAIN is request
//  @GET
//  @Produces(MediaType.TEXT_PLAIN)
//  public String sayPlainTextHello() {
//    return "Hello Jersey";
//  }
 
  // This method is called if XML is request
//  @GET
//  @Produces(MediaType.TEXT_XML)
//  public String sayXMLHello() {
//    return "<?xml version=\"1.0\"?>" + "<hello> Hello Jersey" + "</hello>";
//  }
 
  // This method is called if HTML is request
//  @GET
//  @Produces(MediaType.TEXT_HTML)
//  public String sayHtmlHello() {
//    return "<html> " + "<title>" + "Hello Jersey" + "</title>"
//        + "<body><h1>" + "Hello Jersey" + "</body></h1>" + "</html> ";
//  }
 
}