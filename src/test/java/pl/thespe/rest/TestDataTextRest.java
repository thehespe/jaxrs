package pl.thespe.rest;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

public class TestDataTextRest {

	@Test
	public void get_testResposeOKStatus() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/jaxrs/data_text/1");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
	}

	@Test
	public void get_testResposeNotFoundStatus() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost:8080/jaxrs/data_text/-1");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(HttpStatus.SC_NOT_FOUND, httpResponse.getStatusLine().getStatusCode());
	}

	@Test
	public void getAll_testResposeOKStatus() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpGet("http://localhost	:8080/jaxrs/data_text");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
	}

	@Test
	public void save_testResposeOKStatus() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpPost("http://localhost:8080/jaxrs/data_text/jaxrs");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
	}

	@Test
	public void update_testResposeOKStatus() throws ClientProtocolException, IOException {
		HttpUriRequest request = new HttpPut("http://localhost:8080/jaxrs/data_text/1/jaxrs_test");
		HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);

		Assert.assertEquals(HttpStatus.SC_OK, httpResponse.getStatusLine().getStatusCode());
	}

}
