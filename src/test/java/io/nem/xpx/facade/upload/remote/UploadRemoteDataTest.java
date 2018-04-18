package io.nem.xpx.facade.upload.remote;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import org.junit.Assert;
import org.junit.Test;
import org.nem.core.model.MessageTypes;
import io.nem.ApiException;
import io.nem.xpx.AbstractApiTest;
import io.nem.xpx.builder.UploadDataParameterBuilder;
import io.nem.xpx.builder.UploadFileParameterBuilder;
import io.nem.xpx.facade.Upload;
import io.nem.xpx.facade.connection.RemotePeerConnection;
import io.nem.xpx.model.PeerConnectionNotFoundException;
import io.nem.xpx.model.UploadDataParameter;
import io.nem.xpx.model.UploadException;
import io.nem.xpx.model.UploadFileParameter;
//import io.nem.xpx.monitor.UploadTransactionMonitor;


/** 
 * The Class UploadTest.
 */
public class UploadRemoteDataTest extends AbstractApiTest {

	/**
	 * Upload plain data test.
	 */
	@Test
	public void uploadPlainDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Upload upload = new Upload(remotePeerConnection);

			UploadDataParameter parameter = UploadDataParameterBuilder
					.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey)
					.messageType(MessageTypes.PLAIN)
					.data(new String("NA3Y4G45LSCNMZZU6MDSAPSQQKPVQIW7JG4RP5PK".getBytes(),"UTF-8"))
					.contentType("text/plain")
					.metaData(null) // one level map to json
					.keywords("BTC-ADDRESS")
					.build();

			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
			
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			LOGGER.info(e.getCause().getMessage());
			assertTrue(false);
		}
	}

	/**
	 * Upload plain file test.
	 */
	@Test
	public void uploadPlainFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//ProximaX-Whitepaper-v1.4.pdf"))
					.contentType("application/pdf")
					.metaData(null)
					.keywords(null)
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	@Test
	public void uploadPlainPdfFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//ProximaX-Whitepaper-v1.4.pdf")).metaData(null).keywords(null)
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			Assert.assertNotNull(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}
	
	

	/**
	 * Upload plain large file test.
	 */
	@Test
	public void uploadPlainLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.PLAIN)
					.data(new File("src//test//resources//large_file.zip")).metaData(null).keywords(null)
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure data test.
	 */
	@Test
	public void uploadSecureDataTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Upload upload = new Upload(remotePeerConnection);
			UploadDataParameter parameter = UploadDataParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE)
					.data("This is a Secure Test Data").metaData(null).keywords(null).build();
			String nemhash = upload.uploadTextData(parameter).getNemHash();
			LOGGER.info(nemhash);
		} catch (ApiException | PeerConnectionNotFoundException | IOException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure file test.
	 */
	@Test
	public void uploadSecureFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE)
					.data(new File("src//test//resources//small_file.txt")).metaData(null).keywords(null)
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	/**
	 * Upload secure large file test.
	 */
	@Test
	public void uploadSecureLargeFileTest() {
		RemotePeerConnection remotePeerConnection = new RemotePeerConnection(localRemote);

		try {
			Upload upload = new Upload(remotePeerConnection);
			UploadFileParameter parameter = UploadFileParameterBuilder.senderPrivateKey(this.xPvkey)
					.recipientPublicKey(this.xPubkey).messageType(MessageTypes.SECURE)
					.data(new File("src//test//resources//large_file.zip")).metaData(null).keywords(null)
					.build();
			String nemhash = upload.uploadFile(parameter).getNemHash();
			LOGGER.info(nemhash);
			System.out.print(nemhash);
		} catch (ApiException | IOException | PeerConnectionNotFoundException | UploadException e) {
			e.printStackTrace();
			assertTrue(false);
		}
	}

	
}
