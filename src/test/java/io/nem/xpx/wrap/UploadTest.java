package io.nem.xpx.wrap;

import java.io.File;
import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.MessageTypes;

import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;

public class UploadTest extends AbstractApiTest {

	@Test
	public void uploadPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadData(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, "This is a test data1", null, null, null);
			Assert.assertNotNull(nemhash);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void uploadPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, new File("src//test//resources//small_file_test.txt"), null, null);
			//34fb9bc969ae6c7dd7951db5a61797b8b5488b58a1207f6d98d69a3ec6b30851
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void uploadPlainLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.PLAIN, this.xPvkey, this.xPubkey, new File("src//test//resources//large_file.zip"), null, null);
			//359f9808b2b535f4816377a0adfef7adc6637dabc2131e7fcb329bbccf437be8
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void uploadSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadData(MessageTypes.SECURE, this.xPvkey, this.xPubkey, "This is a Secure Test Data", null, null, null);
			//d9adcb20cba5db12a9d01ed8b21913ba52280057ca9b69730dbfbc25f9bf70ce
			System.out.print(nemhash);
		} catch (ApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void uploadSecureFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey, new File("src//test//resources//small_file_test.txt"), null, null);
			//d505b4f15acb2316072e30ca7bf0db8bee5af4451296ab19403867613221da26
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void uploadSecureLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection("http://localhost:8881/areyes1");
		Upload upload = new Upload(remotePeerConnection);
		try {
			String nemhash = upload.uploadFile(MessageTypes.SECURE, this.xPvkey, this.xPubkey, new File("src//test//resources//large_file.zip"), null, null);
			//d505b4f15acb2316072e30ca7bf0db8bee5af4451296ab19403867613221da26
			System.out.print(nemhash);
		} catch (ApiException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
