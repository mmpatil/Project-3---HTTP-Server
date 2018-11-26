package cs601;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.logging.Level;

import javax.net.ssl.HttpsURLConnection;

import com.google.gson.Gson;

public class ChatHandler implements Handler{

	
	/*
	 * (non-Javadoc)
	 * @see cs601.Handler#handle(cs601.HTTPRequest, cs601.HTTPResponse)
	 */
	@Override
	public void handle(HTTPRequest request,HTTPResponse response) {
		if(request.getMethod().equals(HttpConstants.GET)) {
			doGet(request,response);
		}
		else if(request.getMethod().equals(HttpConstants.POST)){
			doPost(request,response);
		}
		else {
			ErrorPages.methodNotFoundHtml(response);
		}
	}
	
	

	/**
	 * Builds the form if the HTTPMethod is GET
	 * 
	 * @param request Object containing information
	 * @param response {@link HTTPResponse} object to be updated
	 */
	public void doGet(HTTPRequest request,HTTPResponse response) {
		(MyLogger.getLogger()).log(Level.INFO, "This is get method");
		HtmlBuilder html = new HtmlBuilder();
		html.setAction(request.getPath());
		html.setMethod(HttpConstants.POST);
		html.setTitle("Slack Bot");
		html.setLabelName("Type a message");
		html.setId("message");
		StringBuilder formBuilder = html.head();
		formBuilder = html.startBody(formBuilder);
		formBuilder = html.createForm(formBuilder);
		formBuilder = html.closeHtml(formBuilder);		
		response.setHtmlPage(formBuilder.toString());
		response.setHeader(HttpConstants.OK_HEADER + "Content-Lenght: "+ ErrorPages.getContentLenght(formBuilder.toString())+"\n\r\n");
	}

	/**
	 * Builds the form containg the result of the message sent if the HTTPMethod is POST
	 * 
	 * @param request Object containing information
	 * @param response {@link HTTPResponse} object to be updated
	 */
	public void doPost(HTTPRequest request,HTTPResponse response) {
		(MyLogger.getLogger()).log(Level.INFO, "This is post method");
		HtmlBuilder html = new HtmlBuilder();
		html.setAction(request.getPath());
		html.setMethod(HttpConstants.POST);
		html.setTitle("Slack Bot");
		html.setLabelName("Type a message");
		html.setId("message");
		html.setTitle("Slack Bot Response");
		if(!request.getQuery().trim().equals("")) {
			String result = sendToSlack(request,response);
			StringBuilder formBuilder = html.head();
			if(response.getHeader().equals(HttpConstants.OK_HEADER)) {
				formBuilder = html.slackResponsePage(formBuilder);
				formBuilder = html.createForm(formBuilder);
				formBuilder = html.closeHtml(formBuilder);
				response.setHtmlPage(formBuilder.toString());
				response.setHeader(HttpConstants.OK_HEADER + "Content-Lenght: "+ ErrorPages.getContentLenght(formBuilder.toString())+"\n\r\n");
			}
			else {
				formBuilder = html.slackInvalidResponsePage(formBuilder);
				formBuilder = html.createForm(formBuilder);
				formBuilder = html.closeHtml(formBuilder);
				response.setHtmlPage(formBuilder.toString());
				response.setHeader(HttpConstants.OK_HEADER + "Content-Lenght: "+ ErrorPages.getContentLenght(formBuilder.toString())+"\n\r\n");
			}
		}
		else {
			ErrorPages.badRequestHtml(response);
		}
	}

	/**
	 * Sends the message to slack channel by preparing a valid request
	 * @param request Object having the request
	 * @param response Object to generate the response
	 * @return
	 */
	public String sendToSlack(HTTPRequest request,HTTPResponse response) {
		String code ="";
		String input = "";
		Gson gson = new Gson();
		JsonMessage message2 = new JsonMessage();
		message2.setToken(ChatApplication.getConfiguration().getToken());
		message2.setChannel(ChatApplication.getConfiguration().getChannel());
		try {
			message2.setText(URLDecoder.decode(request.getQuery(),"UTF-8"));
		} catch (UnsupportedEncodingException e1) {
			(MyLogger.getLogger()).log(Level.SEVERE, "Unable to decode the message :"+e1.getMessage());
		}
		String json = gson.toJson(message2,JsonMessage.class);
		(MyLogger.getLogger()).log(Level.INFO, "\nSlack request body (json):  "+json);
		System.out.println("JSON : "+json);

		try {
			byte[] postData = json.getBytes("UTF-8");

			URL url = new URL("https://slack.com/api/chat.postMessage");
			HttpsURLConnection connection = (HttpsURLConnection)url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod(request.getMethod());
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.setRequestProperty("Content-Type", "application/json");
			connection.setRequestProperty("Content-Length", Integer.toString(postData.length));
			connection.setRequestProperty("Authorization", ChatApplication.getConfiguration().getToken());
			connection.connect();

			try( DataOutputStream wr = new DataOutputStream( connection.getOutputStream())) {
				wr.write( postData );
			}

			(MyLogger.getLogger()).log(Level.INFO, "\nResponse Code:  "+connection.getResponseCode());
			(MyLogger.getLogger()).log(Level.INFO, "\nResponse Message: "+connection.getResponseMessage());

			code = connection.getResponseCode()+" "+connection.getResponseMessage();

			if(code.equals("200 OK")) {
				try {
					BufferedReader br = 
							new BufferedReader(
									new InputStreamReader(connection.getInputStream()));

					while ((input = br.readLine()) != null){
						(MyLogger.getLogger()).log(Level.INFO, "\nResponse from slack : "+input);
					}
					br.close();
					System.out.println("\n");
				} catch (IOException e) {
					(MyLogger.getLogger()).log(Level.SEVERE, "Error in inputstreamreader :"+e.getMessage());
				}
				response.setHeader(HttpConstants.OK_HEADER);
			}
		} catch (MalformedURLException e) {
			(MyLogger.getLogger()).log(Level.SEVERE, "In chat handler Incorrect url :"+e.getMessage());

		} catch (IOException e) {
			(MyLogger.getLogger()).log(Level.SEVERE, "Error in connection object of HttpsUrlConnection:"+e.getMessage());
		}
		return response.getHeader();
	}
}