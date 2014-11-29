import junit.framework.*;
import com.mockobjects.servlet.*;

public class TestConverterServlet extends TestCase {

  public void test_bad_parameter() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "test");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Bad Temperature</title>"
          + "</head><body><h2>Need to enter a valid temperature!"
            + "Got a NumberFormatException on " 
          + "test" 
          + "</h2></body></html>\n",response.getOutputStreamContents());
  }


public void test_null_parameter() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("FarenheitTemperature", "12");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>No Temperature</title>"
          + "</head><body><h2>Need to enter a temperature!"
          + "</h2></body></html>\n",response.getOutputStreamContents());
  }


public void test_format_parameter() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "12E1");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Bad Temperature</title>"
          + "</head><body><h2>Need to enter a valid temperature!"
            + "Got a NumberFormatException on " 
          + "12E1" 
          + "</h2></body></html>\n",response.getOutputStreamContents());
  }

  public void test_precision_inrange() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "12");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Temperature Converter Result</title>"
          + "</head><body><h2>" + "12" + " Farenheit = " + "-11.11" + " Celsius "
          + "</h2>\n"
          + "<p><h3>The temperature in Austin is " + "451" + " degrees Farenheit</h3>\n"
          + "</body></html>\n",response.getOutputStreamContents());
  }

  public void test_precision_boundary() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "212");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Temperature Converter Result</title>"
          + "</head><body><h2>" + "212" + " Farenheit = " + "100.00" + " Celsius "
          + "</h2>\n"
          + "<p><h3>The temperature in Austin is " + "451" + " degrees Farenheit</h3>\n"
          + "</body></html>\n",response.getOutputStreamContents());
  }

  public void test_precision_outrange() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "213");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Temperature Converter Result</title>"
          + "</head><body><h2>" + "213" + " Farenheit = " + "100.6" + " Celsius "
          + "</h2>\n"
          + "<p><h3>The temperature in Austin is " + "451" + " degrees Farenheit</h3>\n"
          + "</body></html>\n",response.getOutputStreamContents());
  }

public void test_precision_negative() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "-12");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("<html><head><title>Temperature Converter Result</title>"
          + "</head><body><h2>" + "-12" + " Farenheit = " + "-24.4" + " Celsius "
          + "</h2>\n"
          + "<p><h3>The temperature in Austin is " + "451" + " degrees Farenheit</h3>\n"
          + "</body></html>\n",response.getOutputStreamContents());
  }

public void test_request_url() throws Exception {
    TestingLabConverterServlet s = new TestingLabConverterServlet();
    MockHttpServletRequest request = 
      new MockHttpServletRequest();
    MockHttpServletResponse response = 
      new MockHttpServletResponse();
    
    request.setupAddParameter("farenheitTemperature", "12");
    response.setExpectedContentType("text/html");
    s.doGet(request,response);
    response.verify();
    assertEquals("12",request.getParameter("farenheitTemperature"));
  }
  
}

